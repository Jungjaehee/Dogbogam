import torch
from ultralytics import YOLO

from model.obesity.MultiModalMobileNet import MultiModalMobileNet

eye_model = None
skin_model = None
obesity_model = None
breed_model = None

def load_eye_model():
    global eye_model
    if eye_model is None:
        eye_model = YOLO("./model/eye/yolov8.pt", task='classify').cuda()
    return eye_model


def load_skin_model():
    global skin_model
    if skin_model is None:
        skin_model = YOLO("./model/skin/last.pt", task='classify').cuda()
    return skin_model


def load_obesity_model():
    global obesity_model
    if obesity_model is None:
        obesity_model = MultiModalMobileNet()
        obesity_model.load_state_dict(torch.load('./model/obesity/best.pt'))
        obesity_model.cuda().eval()

    return obesity_model


def load_breed_model():
    global breed_model
    if breed_model is None:
        breed_model = YOLO("./model/breed/best.pt", task='classify').cuda()
    return breed_model


def get_eye_model():
    return load_eye_model()

def get_skin_model():
    return load_skin_model()

def get_obesity_model():
    return load_obesity_model()

def get_breed_model():
    return load_breed_model()