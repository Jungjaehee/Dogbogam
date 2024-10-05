import axiosInstance from "./axiosinstance";
import { Insurance } from "../models/insurance.model";

interface Response<T> {
  status: number;
  message: string;
  data: T;
}

interface InsuranceListResponse {
  [key: string]: {
    insurance: Insurance;
    benefit: string[];
  };
}

//보험 전체 리스트 조회
export const getInsuranceList = async (size: number = 4, page: number = 1) => {
  try {
    const response: Response<InsuranceListResponse> = await axiosInstance.get(`/insurances`, {
      params: {
        size,
        page,
      }
    });
    console.log(response.data.data);
    return response.data.data;
  } catch (error) {
    console.log("보험 리스트 가져오기 실패", error);
    throw error;
  }
}



//보험 보장혜택 리스트 조회
export const geInsuranceBenefit = async() => {
  try {
    const response: Response<string[]> = await axiosInstance.get(`/insurances/benefit`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log("보험 보장혜택 가져오기 실패", error);
  }
}

// //보험 검색 조회
// export const getInsuranceSearch = async() => {
//   try {
//     const response: Response<InsuranceListResponse> = await axiosInstance.get(`/insurances/search`);
//     console.log(response.data.data);
//     return response.data.data;
//   } catch ()
// }


// //보험 상세 조회
// export const getinsurance = async() => {
//   try {
//     const response: 
//   }
// }