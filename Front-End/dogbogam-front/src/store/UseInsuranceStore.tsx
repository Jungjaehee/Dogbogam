import { create } from "zustand";
import { persist } from "zustand/middleware";
import type { myInsurance } from "../models/insurance.model";

interface StoreState {
  insuranceList: myInsurance[]; // AI 진단 리스트
  setInsuranceList: (newList: myInsurance[]) => void; // AI 진단 리스트 전체
  insuranceDetail: myInsurance; // 현재 진단 상세정보
  setInsurance: (newInfo: Partial<myInsurance>) => void; // 현재 진단 상세정보
}

const useInsuranceStore = create(
  persist<StoreState>(
    (set) => ({
      insuranceList: [],
      setInsuranceList: (newList: myInsurance[]) =>
        set(() => ({
          insuranceList: newList, // 배열 전체를 업데이트
        })),
      insuranceDetail: {
        insuranceName: "",
        insuranceId: 0,
        dogId: 0,
        registDate: "" as unknown as Date, // 등록 날짜 (YYYY-MM-DD 형식)
        monthlyPayment: 0, // 월 납입금액
        expirationDate: "" as unknown as Date, // 만료 날짜 (YYYY-MM-DD 형식)
      },
      setInsurance: (newInfo: Partial<myInsurance>) =>
        set((state) => ({
          insuranceDetail: { ...state.insuranceDetail, ...newInfo },
        })),
    }),
    {
      name: "insuranceStorage", // 저장소 이름
    }
  )
);

export default useInsuranceStore;
