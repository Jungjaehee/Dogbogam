import type { myMedicalRecord } from "../models/record.model";
import axiosInstance from "./axiosinstance";

// 진료 기록 등록 API 요청 함수
export const registMedicalRecord = async (medicalRecord: myMedicalRecord) => {
  try {

    const response = await axiosInstance.post("/medical-records", medicalRecord);
    console.log("진료 기록 등록 성공함 : ", response.data);

    return response.data;

  } catch (error) {

    console.error("진료 기록 등록 실패함 :", error);

  }
};

// 진료 기록 수정 API 호풀 함수
export const updateMedicalRecord = async (patchedRecord: myMedicalRecord) => {
  try {

    const response = await axiosInstance.patch("/medical-records", patchedRecord)
    console.log("진료 기록 업데이트 성공함 : ", response.data);

    return response.data;

  } catch (error) {

    console.error("진료 기록 업데이트 실패함 :" , error);

  }
};

// 진료 기록 삭제 API 요청 함수
export const deleteVaccination = async (medicalRecordId: number) => {
  try {

    const response = await axiosInstance.delete(`/medical-records/${medicalRecordId}`);
    console.log("진료 기록 삭제 성공함 : ", response.data);

    return response.data;

  } catch (error) {

    console.error("진료 기록 삭제 실패함 :", error);

  }
};

// 진료 기록 디테일 조회 API 요청 함수
export const getMedicalRecordDetail = async (medicalRecordId: number) => {
  try {

    const response = await axiosInstance.get(`/medical-records/${medicalRecordId}`);
    console.log("진료 기록 상세 정보 ", response.data);

    return response.data;

  } catch (error) {

    console.error("진료 기록 상세 정보 가져오기 실패 :", error);

  }
};

// 진료 목록 조회 API 요청 함수
export const getMyMedicalRecord = async (dogId: number) => {
  try {

    const response = await axiosInstance.get(`/medical-records/dog/${dogId}`);
    console.log("진료 목록 불러오기 성공 :", response.data);
    
    return response.data;

  } catch (error) {

    console.error("진료 목록 불러오기 실패 :", error);

  }
};
