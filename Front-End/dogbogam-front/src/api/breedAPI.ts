import axiosInstance from "./axiosinstance";

interface Data {
    dogId: number;
    image: File;
}

//견종 예측
export const breedPrediction = async (data: Data) => {
    const formData = new FormData();

    formData.append("dogId", data.dogId.toString());
    formData.append("image", data.image);

    try {
        const response = await axiosInstance.post("ai-diagnosis/breed", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
        return response?.data?.data;
      } catch (error) {
        console.log("견종 예측 실패: ", error);
        throw error;
      }
    };

export const getBreedResult = async(AiDiagnosisId: number) => {
    try {
        const response = await axiosInstance.get(
          `/ai-diagnosis/report/${AiDiagnosisId}`
        );
        return response?.data?.data;
      } catch (error) {
        console.log("견종 결과 조회 실패: ", error);
        throw error;
      }
    };