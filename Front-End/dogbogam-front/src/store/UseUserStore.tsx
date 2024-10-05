import { create } from "zustand";
import { persist } from "zustand/middleware";
import { Dog, DogList } from "../models/dog.model";

interface StoreState {
  dogList: DogList[]; // 강아지 리스트
  setDogList: (newList: DogList[]) => void; // 배열 전체
  dogInfo: Dog; // 현재 강아지 정보
  setDogInfo: (newInfo: Partial<Dog>) => void; // 현재 강아지 정보

  token: string;
  setToken: (newInfo: string) => void;
}

const useUserStore = create(
  persist<StoreState>(
    (set) => ({
      dogList: [],
      setDogList: (newList: DogList[]) =>
        set(() => ({
          dogList: newList, // 배열 전체를 업데이트
        })),
      dogInfo: {
        dogId: 0,
        name: "",
        breed: "",
        gender: "",
        birth: null,
        weight: null,
        isNeutered: null,
        imageUrl: "",
        createdAt: "" as unknown as Date,
        healthProblems: [],
      },
      setDogInfo: (newInfo: Partial<Dog>) =>
        set((state) => ({
          dogInfo: { ...state.dogInfo, ...newInfo },
        })),

      token: "",
      setToken: (newInfo: string) => set(() => ({ token: newInfo })),
    }),
    {
      name: "userStorage",
    }
  )
);

export default useUserStore;
