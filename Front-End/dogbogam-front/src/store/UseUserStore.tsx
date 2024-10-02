import { create } from "zustand";
import { persist } from "zustand/middleware";
import { UserInfo } from "../models/user.model";
import { Dog, DogList } from "../models/dog.model";

interface StoreState {
  userInfo: UserInfo;
  setUserInfo: (newInfo: Partial<UserInfo>) => void; // 부분적 업데이트를 허용
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
      userInfo: {
        memberId: 0,
        email: "",
        nickname: "",
      },
      setUserInfo: (newInfo) =>
        set((state) => ({
          userInfo: { ...state.userInfo, ...newInfo },
        })),
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
        createdAt: null,
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
