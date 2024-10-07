from fastapi import HTTPException
from mapper.eye_map import EyeLabelMapper
from mapper.skin_map import SkinLabelMapper
from mapper.breed_map import BreedLabelMapper
from service.image_processing import save_image
from model.model_loader import get_eye_model, get_skin_model, get_breed_model
import os
import logging
import traceback

async def diagnosis_eye(image):
    model = get_eye_model()
    outputs = await ai_inference(model, image, EyeLabelMapper)
    return outputs


async def diagnosis_skin(image):
    model = get_skin_model()
    outputs = await ai_inference(model, image, SkinLabelMapper)
    return outputs


async def diagnosis_breed(image):
    model = get_breed_model()
    outputs = await ai_inference(model, image, BreedLabelMapper)
    return outputs


async def ai_inference(model, image, label_mapper):
    image_path = await save_image(image)

    outputs = []
    try:
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