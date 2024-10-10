// 나이 계산 함수
export const calAge = (birthDate: string | null): number | null => {
  if (!birthDate || birthDate.length !== 6) return null; // 유효성 검사

  const today = new Date();
  const year = parseInt(birthDate.slice(0, 2), 10); // 연도 (2자리)
  const month = parseInt(birthDate.slice(2, 4), 10) - 1; // 월 (0부터 시작)
  const day = parseInt(birthDate.slice(4, 6), 10); // 일

  // 2000년대 출생인지 1900년대 출생인지 결정 (기본적으로 2000년대로 설정)
  const fullYear =
    year <= today.getFullYear() % 100 ? 2000 + year : 1900 + year;

  const birthDay = new Date(fullYear, month, day);

  let age = today.getFullYear() - birthDay.getFullYear();

  // 생일이 아직 안 지났다면 나이에서 1을 뺌
  const isBeforeBirthday =
    today.getMonth() < birthDay.getMonth() ||
    (today.getMonth() === birthDay.getMonth() &&
      today.getDate() < birthDay.getDate());

  if (isBeforeBirthday) {
    age--;
  }

  return age;
};

// 가입일 계산하는 함수
export const calSignUpDate = (createdTime: Date): number | null => {
  
  const time = new Date(createdTime);

  const today = new Date(); 
  const diffTime = today.getTime() - time.getTime(); 
  const diffDay = Math.floor(diffTime / (1000 * 60 * 60 * 24));

  if (diffDay == 0) {
    return 1;
  } else {
    return diffDay;
  }
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
    if (diffInDays == 0) {
      return "오늘";
    } else {
      return `${diffInDays}일 전`;
    }
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

// 의료 정보 디테일 페이지에서 날짜 수정 시킬 함수
export const formatDate = (date: Date) => {
  const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: '2-digit', day: '2-digit', weekday: 'long' };
  return new Date(date).toLocaleDateString('ko-KR', options);
};


// utils/formatDate.ts

export const getAiDiagnosisDay = (dateString: string) => {
  const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
  const date = new Date(dateString);
  const dayOfWeek = daysOfWeek[date.getDay()]; // 요일 변환

  const formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1)
    .toString()
    .padStart(2, "0")}-${date.getDate().toString().padStart(2, "0")} ${dayOfWeek}`;

  return formattedDate;
};
