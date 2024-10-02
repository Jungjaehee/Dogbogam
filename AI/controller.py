from fastapi import APIRouter, File, UploadFile
from service import dianosis_eye, dianosis_skin, dianosis_obesity
from response.success_response import SuccessResponse


# uvicorn main:app --reload(이 옵션은 개발 시에만)
# /docs -> swagger
router = APIRouter()

@router.post("/eye")
async def predict_eye(file: UploadFile = File(...)):
    result = await dianosis_eye(file)
    return SuccessResponse.ok(result)


@router.post("/skin")
async def predict_skin(file: UploadFile = File(...)):
    result = await dianosis_skin(file)
    return SuccessResponse.ok(result)


@router.post("/obesity")
async def predict_obesity(file: UploadFile = File(...)):
    result = await dianosis_obesity(file)
    return SuccessResponse.ok(result)

    
# @router.post("/breed")
# async def predict_eye(file: UploadFile = File(...)):
#     result = await dianosis_eye(file)
#     return SuccessResponse.ok(result)