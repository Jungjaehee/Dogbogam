// 시중 보험 타입
export interface Insurance {
  insuranceId: number;
  name: string;
  minAge?: string | null;
  maxAge?: string | null;
  fee: number;
  period: string;
  description: string;
  limit?: string | null;
  coverageRatio: string;
  company: string;
  s3ImageName: string;
  s3ImageUrl: string;
}

// 내가 입력 하는 보험
export interface myInsurance {
  insuranceId: number; // 보험 ID
  insuranceName: string;
  dogId: number; // 강아지 ID
  registDate: Date; // 등록 날짜 (YYYY-MM-DD 형식)
  monthlyPayment: number; // 월 납입금액
  expirationDate: Date; // 만료 날짜 (YYYY-MM-DD 형식)
}

// 추천 보험
export interface recommendInsurance {
  insuranceId: number;
  name: string;
  fee: string;
  company: string;
  image: string;
}
