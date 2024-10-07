import axiosInstance from "./axiosinstance";

interface Data {
  dogId: number;
  image: File;
}

export const eyePrediction = async (data: Data) => {
  const formData = new FormData();

  formData.append("dogId", data.dogId.toString());
  formData.append("image", data.image);
  try {
    const response = await axiosInstance.post("ai-diagnosis/eye", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return response?.data?.data;
  } catch (error) {
    console.log("눈 진단 실패: ", error);
    throw error;
  }
};

export const obesityPrediction = async (data: Data) => {
  const formData = new FormData();

  formData.append("dogId", data.dogId.toString());
  formData.append("image", data.image);
  try {
    const response = await axiosInstance.post(
      "ai-diagnosis/obesity",
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    return response?.data?.data;
  } catch (error) {
    console.log("비만 진단 실패: ", error);
    throw error;
  }
};

export const skinPrediction = async (data: Data) => {
  const formData = new FormData();

  formData.append("dogId", data.dogId.toString());
  formData.append("image", data.image);
  try {
    const response = await axiosInstance.post("ai-diagnosis/skin", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return response?.data?.data;
  } catch (error) {
    console.log("피부 진단 실패: ", error);
    throw error;
  }
};

export const getAIresult = async (AIDiagnosisId: number) => {
  try {
    const response = await axiosInstance.get(
      `/ai-diagnosis/report/${AIDiagnosisId}`
    );
    return response?.data?.data;
  } catch (error) {
    console.log("리포트 조회 실패: ", error);
    throw error;
  }
};

export const getInsurance = async (diagnosisItem: string) => {
  try {
    const response = await axiosInstance.get("/insurances/recommend", {
      params: { diagnosisItem },
    });
    return response?.data?.data;
  } catch (error) {
    console.log("보험 추천 실패: ", error);
    throw error;
  }
};
