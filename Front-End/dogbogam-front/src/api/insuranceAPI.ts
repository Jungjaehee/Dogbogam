import axiosInstance from "./axiosinstance";
import { Insurance } from "../models/insurance.model";

interface Response<T> {
  status: number;
  message: string;
  data: T;
}

export interface InsuranceData {
  insurance: Insurance;
  benefit: string[];
}

export interface InsuranceListResponse {
  [key: string]: InsuranceData;
}


//보험 전체 리스트 조회
export const getInsuranceList = async () => {
  try {
    const response = await axiosInstance.get(`/insurances`);
    console.log(response?.data?.data);
    return response?.data.data;
  } catch (error) {
    console.log("보험 리스트 가져오기 실패", error);
    throw error;
  }
}

// //보험 상세 조회
export const getInsurance = async (insuranceId: number) => {
  try {
    const response: Response<InsuranceListResponse> = await axiosInstance.get(`/insurances/${insuranceId}`);
    console.log("보험상세", response.data.data);
    
    const insuranceDetail = Object.values(response.data.data)[0]; 
    console.log("insuranceDetail", insuranceDetail);

    return insuranceDetail;
  } catch (error) {
    console.log("보험 가져오기 실패", error);
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

//보험 검색 조회
export const getInsuranceSearch = async(benefit: string[]) => {
  try {
    const response: Response<InsuranceListResponse> = await axiosInstance.get(`/insurances/search`, {
      params: {
        benefit
      },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log("보험 검색 실패", error);
  }
}
