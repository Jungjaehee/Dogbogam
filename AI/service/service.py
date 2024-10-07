from fastapi import HTTPException
from ultralytics import YOLO
from mapper.eye_map import EyeLabelMapper
from mapper.skin_map import SkinLabelMapper
from mapper.obesity_breed_map import ObesityBreedMapper
from model.obesity.MultiModalMobileNet import MultiModalMobileNet
from PIL import Image
import torchvision.transforms as transforms
import os
import logging
import traceback
import torch

async def diagnosis_eye(image):
    model_path = "./model/eye/yolov8.pt"
    outputs = await ai_inference(model_path, image, EyeLabelMapper)
    return outputs


async def diagnosis_skin(image):
    model_path = "./model/skin/last.pt"
    outputs = await ai_inference(model_path, image, SkinLabelMapper)
    return outputs


async def diagnosis_obesity(image, breed, weight):
    try:
        os.makedirs("./data", exist_ok=True)

        # 이미지 저장
        image_path = f"./data/{image.filename}"
        contents = await image.read()
        with open(image_path, "wb") as f:
            f.write(contents)

        # 한글 breed를 breed 코드화
        breed_code= await get_breed(breed)

        # 모델 로드
        model = MultiModalMobileNet()
        model.load_state_dict(torch.load('./model/obesity/best.pt'))
        model.eval()

        # 이미지 전처리 변환 설정 (MobileNet에 맞게 설정)
        transform = transforms.Compose([
            transforms.Resize((224, 224)),  # MobileNet 입력 크기
            transforms.ToTensor(),  # 이미지를 Tensor로 변환
            transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])  # ImageNet 데이터셋에서 사용된 정규화 값
        ])

        # 이미지 전처리
        image_pil = Image.open(image_path)
        image_tensor = transform(image_pil).unsqueeze(0)  # 배치 차원 추가

        # breed와 weight를 텐서로 변환
        breed_weight = torch.tensor([[breed_code, weight]], dtype=torch.float32)

        # 추론
        with torch.no_grad():
            output = model(image_tensor, breed_weight)
            _, predicted = torch.max(output, 1)
            class_names = ['정상', '과체중', '고도비만']
            result = class_names[predicted.item()]

        # print(result);

        return {"prediction": result}

    except Exception as e:
        logging.error(f"Error during inference: {str(e)}")
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="비만 진단 중 오류가 발생했습니다.")
    
    finally:
        # 사용 후 파일 삭제
        os.remove(image_path)

async def diagnosis_breed(image):
    return


async def ai_inference(model_path, image, label_mapper):
    try:
        os.makedirs("./data", exist_ok=True)
        image_path = f"./data/{image.filename}"
        contents = await image.read()
        with open(image_path, "wb") as f:
            f.write(contents)

    except Exception as e:
        raise HTTPException(status_code=500, detail="이미지 처리가 되지 않았습니다.")

    outputs = []
    try:
        model = YOLO(model_path, task='classify').cuda()
        result = model.predict(image_path, imgsz=224)

        for cls_idx, conf in zip(result[0].probs.top5[:3], result[0].probs.top5conf[:3]):
            outputs.append({
                "label": label_mapper.get_label(cls_idx),
                "conf": round(conf.item() * 100, 2)
            })
        logging.log(1, outputs)
        os.remove(image_path)

    except Exception as e:
        os.remove(image_path)
        logging.error("Exception occurred", exc_info=True)  # 로그에 예외 정보를 출력
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="모델 예측이 불가합니다.")
    
    return outputs

# JSON에서 weight와 한글로 된 breed 처리 함수
async def get_breed(breed):
    # 한글 품종을 영문 코드로 변환
    breed_code = ObesityBreedMapper.get_breed_code(breed)
    
    if breed_code == "-1":
        raise HTTPException(status_code=400, detail="유효하지 않은 품종입니다.")
    
    return breed_code