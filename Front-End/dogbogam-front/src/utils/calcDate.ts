// 나이 계산 함수
export const calAge = (birthDate: Date | null): number | null => {
  if (!birthDate) return null;
  const today = new Date();
  const age = today.getFullYear() - birthDate.getFullYear();
  return age-1;
};

// 가입일 계산하는 함수
export const calSignUpDate = (createdTime: Date | null): number | null => {
  if (!createdTime) return null; 

  const today = new Date(); 
  const diffTime = today.getTime() - createdTime.getTime(); 
  const diffDay = Math.floor(diffTime / (1000 * 60 * 60 * 24));

  return diffDay;
};

// 진료 경과 시간 확인하는 함수
export const calRelativeTime = (date: Date): string | null => {
  const now = new Date();
  const diffInMs = now.getTime() - date.getTime();
  const diffInDays = Math.floor(diffInMs / (1000 * 60 * 60 * 24)); 
  const diffInWeeks = Math.floor(diffInDays / 7); 
  const diffInMonths = Math.floor(diffInWeeks / 4); 

  if (diffInMonths >= 12) {
    return "1년 전"
  } else if (diffInMonths >= 1  && 12 > diffInMonths) {
    return `${diffInMonths}달 전`;
  } else if (diffInWeeks >= 1) {
    return `${diffInWeeks}주 전`;
  } else {
    return `${diffInDays}일 전`;
  }
};

// 보험 납입 횟수 계산하는 함수
export const calPaymentsTime = (startDate: Date): number => {
  const now = new Date();
  // 년도 걔산
  const yearDiff = now.getFullYear() - startDate.getFullYear();
  // 월 계산
  const monthDiff = now.getMonth() - startDate.getMonth();
  // 차이 계산
  const totalMonths = yearDiff * 12 + monthDiff;
  // 납입 횟수 반환
  return Math.max(totalMonths + 1, 1);
};
