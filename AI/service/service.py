from fastapi import HTTPException
from ultralytics import YOLO
from mapper.eye_map import EyeLabelMapper
from mapper.skin_map import SkinLabelMapper
import os
import logging
import traceback

async def diagnosis_eye(image):
    model_path = "./model/eye/yolov8.pt"
    outputs = await ai_inference(model_path, image, EyeLabelMapper)
    return outputs


async def diagnosis_skin(image):
    model_path = "./model/skin/last.pt"
    outputs = await ai_inference(model_path, image, SkinLabelMapper)
    return outputs


async def diagnosis_obesity(image, json_file):
    try:
        os.makedirs("./data", exist_ok=True)

        # 이미지 저장
        image_path = f"./data/{image.filename}"
        contents = await image.read()
        with open(image_path, "wb") as f:
            f.write(contents)

        # JSON 파일 저장
        json_path = f"./data/{json_file.filename}"
        json_content = await json_file.read()
        with open(json_path, "wb") as f:
            f.write(json_content)

        # breed와 weight 추출
        breed_code, weight = await get_breed_and_weight(json_path)

        # 모델 로드
        model = MultiModalMobileNet()
        model.load_state_dict(torch.load('./model/obesity/best.pt'))
        model.eval()

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

        return {"prediction": result}

    except Exception as e:
        logging.error(f"Error during inference: {str(e)}")
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="비만 진단 중 오류가 발생했습니다.")
    
    finally:
        # 사용 후 파일 삭제
        os.remove(image_path)
        os.remove(json_path)

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
async def get_breed_and_weight(json_path):
    with open(json_path, 'r', encoding='utf-8') as f:
        data = json.load(f)
    
    korean_breed = data.get('breed')  # JSON에서 한글 품종 추출
    weight = data.get('weight')  # JSON에서 weight 추출

    # 한글 품종을 영문 코드로 변환
    breed_code = ObesityBreedMapper.get_breed_code(korean_breed)
    
    if breed_code == "ETC":
        raise HTTPException(status_code=400, detail="유효하지 않은 품종입니다.")
    
    return breed_code, weight