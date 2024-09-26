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
}

