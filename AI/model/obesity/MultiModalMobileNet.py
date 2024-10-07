import torch
import torch.nn as nn
from torchvision import models

class MultiModalMobileNet(nn.Module):
    def __init__(self):
        super(MultiModalMobileNet, self).__init__()
        
        # MobileNet을 기반으로 이미지 처리
        self.mobilenet = models.mobilenet_v2(pretrained=True)
        self.mobilenet.classifier = nn.Sequential()  # 기존의 분류 레이어 제거
        
        # breed와 weight 정보를 처리하는 레이어
        self.breed_weight_layer = nn.Sequential(
            nn.Linear(2, 32),  # breed와 weight 두 개의 정보를 입력받음
            nn.ReLU(),
            nn.Linear(32, 128),
            nn.ReLU()
        )
        
        # 이미지와 breed, weight 결합 후 최종 분류 레이어
        self.final_classifier = nn.Sequential(
            nn.Linear(1280 + 128, 512),  # MobileNet 출력(1280) + breed_weight 레이어 출력(128)
            nn.ReLU(),
            nn.Linear(512, 3)  # 3개의 클래스 (정상, 과체중, 고도비만)
        )
    
    def forward(self, image, breed_weight):
        # 이미지 처리
        image_features = self.mobilenet.features(image)
        image_features = image_features.mean([2, 3])  # Global Average Pooling
        
        # breed와 weight 처리
        breed_weight_features = self.breed_weight_layer(breed_weight)
        
        # 두 입력을 결합
        combined_features = torch.cat((image_features, breed_weight_features), dim=1)
        
        # 최종 분류
        output = self.final_classifier(combined_features)
        return output
