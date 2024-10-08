import type { myVaccinationRecord } from "../models/record.model";
import axiosInstance from "./axiosinstance";
import { BASE_URL } from "./APIconfig";
// 예방접종 기록 등록 API 요청 함수
// 진료 기록 등록 API 요청 함수
export const registVaccination = async (record: myVaccinationRecord) => {
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
    formData.append("recordTime", formattedRecordTime);
    formData.append("content", record.content);
    formData.append("hospital", record.hospital);
    formData.append("cost", record.cost.toString());
    formData.append("vaccinationRound", record.vaccinationRound.toString());

    const response = await axiosInstance.post(
      `${BASE_URL}/vaccination-records`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    return response?.data?.data;
  } catch (error) {
    console.log("예방 접종 기록 등록 실패: ", error);
    throw error;
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
    // console.log("예방접종 기록 상세 정보 ", response.data);

    return response.data.data;

  } catch (error) {

    console.error("접종 기록 상세 정보 가져오기 실패 :", error);

  }
};

// 예방접종 목록 조회 API 요청 함수
export const getMyVaccination = async (dogId: number) => {
  try {

    const response = await axiosInstance.get(`/vaccination-records/list/${dogId}`);
    // console.log("예방접종 목록 불러오기 성공 :", response.data);

    return response.data;

  } catch (error) {

    console.error("예방접종 목록 불러오기 실패 :", error);

  }
};
