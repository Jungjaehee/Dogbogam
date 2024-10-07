from fastapi import APIRouter, File, UploadFile, Form
from service.yolo_service import diagnosis_eye, diagnosis_skin, diagnosis_breed
from service.mobile_net_service import diagnosis_obesity
from response.success_response import SuccessResponse
import logging

# uvicorn main:app --reload(이 옵션은 개발 시에만)
# /docs -> swagger
router = APIRouter()
logging.basicConfig(level=logging.INFO)

@router.post("/eye")
async def predict_eye(image: UploadFile = File(...)):
    result = await diagnosis_eye(image)
    # logging.info(f"Result: {SuccessResponse.ok(result).dict()}")
    return SuccessResponse.ok(result).dict()


@router.post("/skin")
async def predict_skin(image: UploadFile = File(...)):
    result = await diagnosis_skin(image)
    return SuccessResponse.ok(result).dict()


@router.post("/obesity")
async def predict_obesity(image: UploadFile = File(...),
                          breed: str = Form(...),  # breed 필드 추가
                          weight: float = Form(...)  # weight 필드 추가
                         ):
    result = await diagnosis_obesity(image, breed, weight)
    return SuccessResponse.ok(result).dict()

    
@router.post("/breed")
async def predict_eye(image: UploadFile = File(...)):
    result = await diagnosis_breed(image)
    return SuccessResponse.ok(result).dict()