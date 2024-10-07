import axiosInstance from "./axiosinstance";

interface Response<T> {
    status: number;
    message: string;
    data: T;
}

export interface AnimalHospital {
    animalHospitalId: number;
    name: string;
    class: string;
    latitude: string;
    longitude: string;
    phoneNumber: string;
    address: string;
    businessHours: string;
}

// 동물병원 리스트 조회
export const getHospitalList = async (latitude: string, longitude: string) => {
    try {
        const response: Response<AnimalHospital[]> = await axiosInstance.get(`/animal-hospitals`, {
            params: {
                latitude,
                longitude
            }
        });
        return response.data;
    } catch (error) {
        console.log("병원 리스트 가져오기 실패", error);
        throw error;
    }
}
