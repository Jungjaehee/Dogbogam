from fastapi import FastAPI, HTTPException, Request
from controller.controller import router as image_router
from fastapi.responses import JSONResponse
import os

os.environ["CUDA_VISIBLE_DEVICES"]="7"

# uvicorn main:app --reload
app = FastAPI()

app.include_router(image_router, prefix="/api/ai-dignosis")


@app.on_event("startup")
async def startup_event():
    from model.model_loader import load_eye_model, load_skin_model, load_obesity_model, load_breed_model
    load_eye_model()
    load_skin_model()
    load_obesity_model()
    load_breed_model()


@app.exception_handler(HTTPException)
async def http_exception_handler(request: Request, exc: HTTPException):
    return JSONResponse(
        status_code=exc.status_code,
        content={"detail": exc.detail},
    )