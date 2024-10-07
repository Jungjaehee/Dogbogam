import os
import shutil
import random
from pathlib import Path


def select_and_copy_images(src_dir, dest_dir, percentage=0.2):
    # src_dir: train이나 valid 폴더 경로
    # dest_dir: 선택된 파일을 복사할 목적지 경로
    # percentage: 선택할 파일의 비율 (기본값 20%)

    # 질병 폴더 목록을 가져옴
    disease_folders = [f for f in os.listdir(src_dir) if os.path.isdir(os.path.join(src_dir, f))]

    for disease in disease_folders:
        disease_path = os.path.join(src_dir, disease)
        images = [f for f in os.listdir(disease_path) if os.path.isfile(os.path.join(disease_path, f))]

        # 선택할 이미지 수 계산
        num_images_to_copy = int(len(images) * percentage)

        # 이미지를 무작위로 선택
        selected_images = random.sample(images, num_images_to_copy)

        # 목적지 폴더 경로 생성
        dest_disease_folder = os.path.join(dest_dir, disease)
        Path(dest_disease_folder).mkdir(parents=True, exist_ok=True)

        # 선택된 이미지를 목적지 폴더로 복사
        for image in selected_images:
            src_image_path = os.path.join(disease_path, image)
            dest_image_path = os.path.join(dest_disease_folder, image)
            shutil.copy(src_image_path, dest_image_path)
            print(f"Copied {src_image_path} to {dest_image_path}")


if __name__ == "__main__":
    # train과 valid에 대해 각각 수행
    src_train_dir = "/path/to/train"
    src_valid_dir = "/path/to/valid"

    dest_train_dir = "/path/to/destination/train"
    dest_valid_dir = "/path/to/destination/valid"

    # train 폴더 처리
    select_and_copy_images(src_train_dir, dest_train_dir, percentage=0.2)

    # valid 폴더 처리
    select_and_copy_images(src_valid_dir, dest_valid_dir, percentage=0.2)
