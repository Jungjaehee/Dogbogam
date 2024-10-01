
// 시중 보험 타입
export interface Insurance {
  insurance_id: bigint; 
  name: string; 
  min_age?: string | null; 
  max_age?: string | null; 
  fee: number; 
  period: string; 
  description: string; 
  limitFee?: string | null;
  coverage_ratio: string; 
  company: string; 
  image_name: string; 
  image_url: string; 
}

// 내가 입력 하는 보험
export interface myInsurance {
  insuranceId: number; // 보험 ID
  dogId: number; // 강아지 ID
  registDate: string; // 등록 날짜 (YYYY-MM-DD 형식)
  monthlyPayment: number; // 월 납입금액
  expirationDate: string; // 만료 날짜 (YYYY-MM-DD 형식)
}