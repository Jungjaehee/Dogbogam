import type { myMedicalRecord } from "../models/record.model";
import { BASE_URL } from "./APIconfig";
import axiosInstance from "./axiosinstance";

// 진료 기록 등록 API 요청 함수
export const registMedicalRecord = async (record: myMedicalRecord) => {
  try {
    const formData = new FormData();

    // 이미지가 있으면 첨부, 없으면 디폴트 이미지
    if (record.image) {
      formData.append("image", record.image);
    }

    // DateTime 형식 변환 (예: 2024-10-07T19:38:00)
    const formattedRecordTime = new Date(record.recordTime)
      .toISOString()
      .slice(0, 19);

    formData.append("dogId", record.dogId.toString());
    formData.append("recordTime", formattedRecordTime); // 여기서 변환된 값을 사용
    formData.append("content", record.content);
    formData.append("hospital", record.hospital);
    formData.append("cost", record.cost.toString());

    const response = await axiosInstance.post(
      `${BASE_URL}/medical-records`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    return response?.data?.data;
  } catch (error) {
    console.log("진료 기록 등록 실패: ", error);
    throw error;
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

    return response.data.data;

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
