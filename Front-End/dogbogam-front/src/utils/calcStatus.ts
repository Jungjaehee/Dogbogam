
// 진단 결과 상태 체크하는 함수
export const calcAIstatus = (normal : boolean): string => {
    if (normal) {

        return "좋음"

    } else {

        return "위험"

    }
};

// 진단 결과 상태에 따른 코멘트를 리턴하는 함수
export const getComment = (status: string): string => {
  if (status === "좋음") {

    return "이대로 유지해주세요";

  } else if (status === "위험") {

    return "가까운 병원 내원을 추천해요";

  } else {

    // 주의에 대한 조건문 추가 예정
    return "지속적으로 관리가 필요해요";
  }
};