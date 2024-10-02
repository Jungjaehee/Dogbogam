from ultralytics import YOLO
import os

async def dianosis_eye(image):
    model_path = "./model/eye/yolov8.pt"
    outputs = await ai_inference(model_path, image)
    # label mapping

    return outputs


async def dianosis_skin(image):
    return


async def dianosis_obesity(image):
    return


async def ai_inference(model_path, image):
    try:
        image_path = f"./data/{image.filename}"
        contents = await image.read()
        with open(image_path, "wb") as f:
            f.write(contents)

    except Exception as e:
        raise HTTPException(status_code=400, detail="이미지 처리가 되지 않았습니다.")

    try:
        model = YOLO(model_path, task='classify').cuda()
        result = model.predict(image_path, imgsz=224)
    
        outputs = []
        for cls_idx, conf in zip(result[0].probs.top5[:3], result[0].probs.top5conf[:3]):
            outputs.append({
                "class": cls_idx.item(),         
                "confidence": conf.item() * 100 
            })
    
        os.remove(image_path)

    except Exception as e:
        os.remove(image_path)
        raise HTTPException(status_code=500, detail="모델 예측이 불가합니다.")
    
    return outputs