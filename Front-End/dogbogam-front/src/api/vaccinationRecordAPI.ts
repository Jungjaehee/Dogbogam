import type { myVaccinationRecord } from "../models/record.model";
import axiosInstance from "./axiosinstance";

// 예방접종 기록 등록 API 요청 함수
export const registVaccination = async (vaccinationRecord: myVaccinationRecord) => {
  try {

    const response = await axiosInstance.post("/vaccination-records", vaccinationRecord);
    console.log("예방접종 등록 성공함 : ", response.data);

    return response.data;

  } catch (error) {

    console.error("예방접종 등록 실패함 :", error);

  }
};

// 예방접종 기록 수정 API 호풀 함수
export const updateVaccination = async (patchedInsurance: myVaccinationRecord) => {
  try {

    const response = await axiosInstance.patch("/vaccination-records", patchedInsurance)
    console.log("예방접종 업데이트 성공함 : ", response.data);

    return response.data;

  } catch (error) {

    console.error("예방접종 업데이트 실패함 :" , error);

  }
};

// 예방접종 기록 삭제 API 요청 함수
export const deleteVaccination = async (vaccinationRecordId: number) => {
  try {

    const response = await axiosInstance.delete(`/vaccination-records/${vaccinationRecordId}`);
    console.log("예방접종 기록 삭제 성공함 : ", response.data);

    return response.data;

  } catch (error) {

    console.error("예방접종 기록 삭제 실패함 :", error);

  }
};

// 예방접종 기록 디테일 조회 API 요청 함수
export const getVaccinationDetail = async (vaccinationRecordId: number) => {
  try {

    const response = await axiosInstance.get(`/vaccination-records/${vaccinationRecordId}`);
    console.log("예방접종 기록 상세 정보 ", response.data);

    return response.data;

  } catch (error) {

    console.error("접종 기록 상세 정보 가져오기 실패 :", error);

  }
};

// 예방접종 목록 조회 API 요청 함수
export const getMyVaccination = async (dogId: number) => {
  try {

    const response = await axiosInstance.get(`/vaccination-records/dog/${dogId}`);
    console.log("예방접종 목록 불러오기 성공 :", response.data);

    return response.data;

  } catch (error) {

    console.error("예방접종 목록 불러오기 실패 :", error);

  }
};
