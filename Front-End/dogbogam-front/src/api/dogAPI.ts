import axios from "axios";
import { BASE_URL } from "./APIconfig";
import { inputDogInfo } from "../models/dog.model";
import defaultImage from "../assets/MyPage/DefaultDogIcon.png";

export const registDogInfo = async (token: string, dog: inputDogInfo) => {
  try {
    const formData = new FormData();

    // 이미지가 있으면 첨부, 없으면 디폴트 이미지
    if (dog.image) {
      formData.append("image", dog.image);
    } else {
      const defaultDogImage = await fetch(defaultImage).then((res) =>
        res.blob()
      );
      formData.append("image", defaultDogImage, "default-dog.png"); // 디폴트 이미지
    }
    formData.append("name", dog.name);
    formData.append("gender", dog.gender);
    formData.append("breed", dog.breed);
    formData.append("birthDate", dog.birthDate);
    formData.append("weight", dog.weight.toString());
    formData.append("isNeutered", dog.isNeutered.toString());

    const response = await axios.post(`${BASE_URL}dogs`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        Authorization: `Bearer ${token}`,
      },
    });
    return response?.data?.data;
  } catch (error) {
    console.log("강아지 정보 등록 실패: ", error);
    throw error;
  }
};

export const registDogHealth = async (
  token: string,
  dogId: number,
  problem: string[]
) => {
  try {
    const response = await axios.post(
      `${BASE_URL}health-problems`,
      {
        dogId,
        problem,
      },
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    return response?.data?.data;
  } catch (error) {
    console.log("건강 고민 등록 실패: ", error);
    throw error;
  }
};

export const getDogList = async (token: string) => {
  try {
    const response = await axios.get(`${BASE_URL}dogs/list`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response?.data?.data;
  } catch (error) {
    console.log("강아지 리스트 조회 실패: ", error);
    throw error;
  }
};

export const getDogInfo = async (token: string, dogId: number) => {
  try {
    const response = await axios.get(`${BASE_URL}dogs/${dogId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response?.data?.data;
  } catch (error) {
    console.log("강아지 정보 조회: ", error);
    throw error;
  }
};
