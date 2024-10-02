import axiosInstance from "./axiosinstance";

interface Response<T> {
  status: number;
  message: string;
  data: T;
}

interface ResponseData {
  [key: string]: {
    data: string,
  };
}

//보험 전체 리스트 조회
export const getInsurancelist = async () => {
  try {
    const response: Response<ResponseData> = await axiosInstance.get(`/insurances`);
    return response.data.data;
  } catch (error) {
    console.log("보험 리스트 가져오기 실패", error);
    throw error;
  }
}

//보험 보장혜택 리스트 조회
export const getBenefit = async() => {
  try {
    const response: Response<ResponseData> = await axiosInstance.get(`/insurances/benefit`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log("보험 보장혜택 가져오기 실패", error);
  }
}

// //보험 상세 조회
// export const getinsurance = async() => {
//   try {
//     const response: 
//   }
// }