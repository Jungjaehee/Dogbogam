import { create } from "zustand";
import { persist } from "zustand/middleware";

export interface DogInfo {
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
  imageUrl: string;
  createdTime: Date;
  modifiedTime: Date | null;
}

interface StoreState {
  dogInfo: DogInfo;
  setDogInfo: (newInfo: Partial<DogInfo>) => void;
  token: string;
  setToken: (newToken: string) => void;
}

const useDogStore = create(
  persist<StoreState>(
    (set) => ({
      dogInfo: {
        dogId: 0,
        memberId: 0,
        isDeleted: false,
        name: "",
        breed: "",
        gender: "",
        birthDate: null,
        weight: null,
        isNeutered: null,
        imageName: null,
        imageUrl: "",
        createdTime: new Date(),
        modifiedTime: null,
      },
      setDogInfo: (newInfo: Partial<DogInfo>) =>
        set((state) => ({
          dogInfo: { ...state.dogInfo, ...newInfo },
        })),
      token: "",
      setToken: (newToken: string) => set(() => ({ token: newToken })),
    }),
    {
      name: "dogStorage",
    }
  )
);

export default useDogStore;
