import axiosInstance from "./axiosinstance"
import type { myInsurance } from "../models/insurance.model";


// 보험 기록 등록 API 요청 함수
export const registeInsurance = async (insuranceRecord: myInsurance) => {
  try {

    const response = await axiosInstance.post("/insurance-records", insuranceRecord);
    console.log("보험 등록 성공함 : ", response.data);

    return response.data;

  } catch (error) {

    console.error("보험 등록 실패함 :", error);

  }
};

// 보험 기록 수정 API 호풀 함수
export const updateInsurance = async (patchedInsurance: myInsurance) => {
  try {

    const response = await axiosInstance.patch("/insurance-records", patchedInsurance)
    console.log("보험 등록 성공함 : ", response.data);

    return response.data;

  } catch (error) {

    console.error("보험 업데이트 실패함 :" , error);

  }
};

// 보험 삭제 API 요청 함수
export const deleteInsurance = async (insuranceRecordId: number) => {
  try {

    const response = await axiosInstance.delete(`insurance-records/${insuranceRecordId}`);
    

    return response.data;

  } catch (error) {

    console.error("보험 삭제 실패함 :", error);

  }
};

// 특정 보험 디테일 조회 API 요청 함수
export const getMyInsuranceDetail = async (insuranceRecordId: number) => {
  try {
    const response = await axiosInstance.get(`/insurance-records/${insuranceRecordId}`);
    console.log("보험 상세 정보 ", response.data);

    return response.data.data;

  } catch (error) {

    console.error("보험 상세 정보 가져오기 실패 :", error);

  }
}

// 내 보험 목록 조회 API 요청 함수
export const getMyInsurances = async (dogId: number) => {
  try {

    const response = await axiosInstance.get(`/insurance-records/all/${dogId}`);

    return response?.data.data;

  } catch (error) {

    console.error("보험 목록 불러오기 실패 :", error);

  }
};
