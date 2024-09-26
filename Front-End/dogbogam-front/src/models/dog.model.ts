// Dog 인터페이스 정의
export interface Dog {
  dogId: number;
  memberId: number;
  isDeleted: boolean;
  name: string;
  breed: string;
  gender: string;
  birthDate?: Date | null;
  weight?: number | null;
  isNeutered?: boolean | null;
  imageName?: string | null;
  imageUrl?: string | null;
  createdTime: Date;
  modifiedTime: Date | null;
}
