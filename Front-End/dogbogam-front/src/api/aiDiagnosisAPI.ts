// import type { AiDiagnosis } from "../models/record.model";
import axiosInstance from "./axiosinstance";

// ai 진단 삭제 API 요청 함수
export const deleteDiagnosis = async (diagnosisId: number) => {
  try {
    const response = await axiosInstance.delete(
      `/ai-diagnosis/report/${diagnosisId}`
    );
    console.log("ai 진단 기록 삭제 성공함 : ", response.data);

    return response.data;
  } catch (error) {
    console.error("ai 진단 기록 삭제 성공함 : ", error);
  }
};

// ai 진단 디테일 조회 API 요청 함수
export const getDiagnosisDetail = async (diagnosisId: number) => {
  try {
    const response = await axiosInstance.get(
      `/ai-diagnosis/report/${diagnosisId}`
    );
    
    return response.data.data;
  } catch (error) {
    console.error("ai 진단 기록 상세 정보 가져오기 실패 :", error);
  }
};

// ai 진단 목록 조회 API 요청 함수
export const getDiagnosisRecords = async (dogId: number) => {
  try {
    const response = await axiosInstance.get(`/ai-diagnosis/report/list/${dogId}`);
    return response.data.data;
  } catch (error) {
    console.error("ai 진단 목록 불러오기 실패 :", error);
  }
};
