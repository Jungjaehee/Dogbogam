// 병원 진료 기록 인터페이스
export interface MedicalRecord {
  medicalRecordId?: number;
  dogId: number;
  content: string | null;
  hospital: string;
  imageName?: string | null;
  imageUrl?: string | null;
  recordTime: Date;
  createdAt: Date;
  modifiedAt: Date | null;
  cost: number;
}

// 예방 접종 기록 인터페이스
export interface VaccinationRecord {
  vaccinationRecordId?: number;
  dogId: number;
  content: string | null;
  hospital: string;
  imageName?: string | null;
  imageUrl?: string | null;
  recordTime: Date;
  vaccinationRound: number;
  createdAt: Date;
  modifiedAt: Date | null;
  cost: number;
}
// ai 진단 결과에 따라오는 인터페이스
export interface Disease {
  disease: string;
  percentage: string;
}
// ai 진단 결과 인터페이스
export interface AiDiagnosis {
  aiDiagnosisId: number;
  dogId: number;
  createdAt: Date;
  imageUrl: string;
  normal: boolean;
  diagnosisItem: string;
  diseases: Disease[];
}

// 등록 하는 예방 접종 기록
export interface myVaccinationRecord {
  dogId: number;
  recordTime: Date;
  content: string;
  hospital: string;
  cost: number;
  vaccinationRound: number;
  // 사진 필드 추가해야됨
}

// 등록 하는 진료 기록
export interface myMedicalRecord {
  dogId: number;
  recordTime: Date;
  content: string;
  hospital: string;
  cost: number;
  // 사진 필드 추가해야됨
}
