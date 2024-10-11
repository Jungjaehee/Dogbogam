import axiosInstance from "./axiosinstance";

interface Response<T> {
    status: number;
    message: string;
    data: T;
}

export interface Supplements {
    supplementId: number;  
    productName: string;
    imageUrl: string;
    offer: string;
    price: number;
}

export interface supplement {
  supplementId: number;
  productName: string;
  brandName: string;
  target: string;
  how: string;
  offer: string;
  type: string;
  basis: string;
  protein: string;
  fat: string;
  feature: string;
  price: number;
  imageName?: string;
  imageUrl: string;
}

// {
//   "status": 200,
//   "message": "요청을 성공했습니다.",
//   "data": {
//       "supplementId": 2,
//       "productName": "안티놀 래피드 240캡슐 강아지 고양이 관절 슬개골 영양제",
//       "brandName": "안티놀",
//       "target": "전연령",
//       "how": "사료,간식에 섞어서, 바로 급여\n",
//       "offer": "관절강화, 피모관리, 면역력강화, 신장/요로, 심장건강\t",
//       "type": "젤겔",
//       "basis": "초록잎홍합, 크릴오일",
//       "protein": "0%",
//       "fat": "50%",
//       "feature": "무인공첨가물, 무합성착향료, 글루텐프리, 무합성착색료, 무방부제, non_GMO, 휴먼그레이드, 그레인프리",
//       "price": 149000,
//       "imageName": null,
//       "imageUrl": null
//   }
// }


// 영양제 전체 리스트 조회
export const getSupplementList = async (size: number = 5, page: number = 0) => {
  try {
    const response: Response<{ data: Supplements[] }> = await axiosInstance.get(`/supplements/all`, {
      params: {
        size,
        page,
      },
    });
    console.log(response.data.data); // 응답에서 data 필드를 추출
    return response.data.data; // 실제 영양제 리스트를 반환
  } catch (error) {
    console.log("영양제 리스트 가져오기 실패", error);
    throw error;
  }
};

//영양제 상세 조회
export const getSupplement = async (supplimentId: number) => {
  try{
    const response: Response<{ data: supplement }> = await axiosInstance.get(`/supplements/${supplimentId}`);
    console.log(response);
    return response.data.data; // response.data.data로 접근
  } catch (error) {
    console.log("영양제 가져오기 실패", error);
    throw error;
  }
}
