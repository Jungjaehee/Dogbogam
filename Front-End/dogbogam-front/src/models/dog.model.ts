// Dog 인터페이스 정의
export interface Dog {
  dogId: number;
  name: string;
  breed: string;
  gender: string;
  birth?: Date | null;
  weight?: number | null;
  isNeutered?: boolean | null;
  imageUrl?: string | null;
  createdAt: Date;
  healthProblems: string[];
}

export interface inputDogInfo {
  image: File | null;
  name: string;
  gender: string; // 여아 남아
  breed: string;
  birthDate: string; // 강아지 고양이
  weight: number;
  isNeutered: boolean;
}

export interface DogList {
  dogId: number;
  dogName: string;
  imageUrl: string;
}

export interface updateDogInfo {
  image: File | null;
  dogId: number;
  name: string;
  breed: string;
  birthDate: string; // 강아지 고양이
  weight: number;
  isNeutered: boolean;
}