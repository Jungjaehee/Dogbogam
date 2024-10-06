from fastapi import HTTPException
from ultralytics import YOLO
from mapper.eye_map import EyeLabelMapper
from mapper.skin_map import SkinLabelMapper
from obesity_breed_map import ObesityBreedMapper  # breed 매핑 import
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


async def diagnosis_obesity(image):
    model_path = "./model/obesity/best.pt"
    return 

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