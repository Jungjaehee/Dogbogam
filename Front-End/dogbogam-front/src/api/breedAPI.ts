import axiosInstance from "./axiosinstance";

interface Data {
    dogId: number;
    image: File;
}

// [POST]  {baseURI}/ai-diagnosis/breed
// {
// 	"dogId":1,
// 	"image":""
// }

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

    ///ai-diagnosis/report/{aiDiagnosisId}
    //{
//     "status": 200,
//     "message": "요청이 성공했습니다.",
//     "data": {
// 		    "aiDiagnosisId":4,
// 		    "dogId":3,
// 				"imageUrl":"이미지url",
// 				"nomal":false,
// 				"diagnosisItem":"진단 항목",
// 				"createdAt":"2024-09-04T00:00:00",
// 				"diseases": {
// 						{
// 								"disease":"결막염",
// 								"percentage":72.2
// 						},
// 						{
// 								"disease":"녹내장",
// 								"percentage":11.1
// 						},
// 						{
// 								"disease":"안구건조증",
// 								"percentage":8.5
// 						}
// 				}
// 		}
// }

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