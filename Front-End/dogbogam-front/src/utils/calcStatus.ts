import type { Disease } from "../models/record.model";

// 진단 결과 상태 체크하는 함수
export const calcAIstatus = (diseases: Disease[]): string => {
  
  const status = diseases.some((disease) => {
    return parseFloat(disease.percentage) > 50;
  });

  return status ? "위험" : "주의";
};


// 진단 결과 상태에 따른 코멘트를 리턴하는 함수
export const getComment = (status: string): string => {
  if (status === "좋음") {
    return "이대로 유지해주세요";
  } else if (status === "위험") {
    return "가까운 병원 내원을 추천해요";
  } else {
    return "지속적으로 관리가 필요해요";
  }
};

export const getStatusColor = (status: string): string => {
  if (status === "주의") {
    return "text-warning-text";
  } else if (status === "위험") {
    return "text-bad-text";
  } else {
    return "text-good-text";
  }
}

// 예방 접종 차수 확인하는 함수
export const checkRound = (round: number): string => {
  if (round == 6) {
    return "기타 예방 접종"
  } else {
    return `${round}차 예방 접종`
  }
}

