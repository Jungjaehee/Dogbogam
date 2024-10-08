import { create } from "zustand";
import type { myInsurance, insuranceItem } from "../models/insurance.model";

interface StoreState {
  insuranceList: myInsurance[]; // 보험 리스트
  setInsuranceList: (newList: myInsurance[]) => void; // 보험 리스트 전체
  insuranceDetail: myInsurance; // 현재 보험 상세정보
  setInsurance: (newInfo: Partial<myInsurance>) => void; // 현재 보험 상세정보
  insuranceItemList: insuranceItem[];
  setInsuranceItemList: (newList: insuranceItem[]) => void;
  insuranceItemDetail: insuranceItem;
  setInsuranceItem: (newInfo: Partial<insuranceItem>) => void;
}

const useInsuranceStore = create<StoreState>((set) => ({
  insuranceList: [],
  setInsuranceList: (newList: myInsurance[]) => {
    console.log("Updating insuranceList with:", newList);
    set(() => ({
      insuranceList: newList, // 배열 전체를 업데이트
    }));
  },

  insuranceDetail: {
    insuranceName: "",
    insuranceId: 0,
    dogId: 0,
    registDate: "" as unknown as Date, // 등록 날짜 (YYYY-MM-DD 형식)
    monthlyPayment: 0, // 월 납입금액
    expirationDate: "" as unknown as Date, // 만료 날짜 (YYYY-MM-DD 형식)
  },
  setInsurance: (newInfo: Partial<myInsurance>) => {
    console.log("Updating insuranceDetail with:", newInfo);
    set((state) => ({
      insuranceDetail: { ...state.insuranceDetail, ...newInfo },
    }));
  },

  insuranceItemList: [],
  setInsuranceItemList: (newList: insuranceItem[]) => {
    set(() => ({
      insuranceItemList: newList, // 배열 전체를 업데이트
    }));
  },

  insuranceItemDetail: {
    dogId: 0,
    dogName: "",
    expirationDate: "" as unknown as Date,
    insuranceCompany: "",
    insuranceId: 0,
    insuranceImage: "" as unknown as File,
    insuranceName: "",
    insuranceRecordId: 0,
    monthlyPayment: 0,
    registDate: "" as unknown as Date,
  },
  setInsuranceItem: (newInfo: Partial<insuranceItem>) => {
    console.log("Updating insuranceItemDetail with:", newInfo);
    set((state) => ({
      insuranceItemDetail: { ...state.insuranceItemDetail, ...newInfo },
    }));
  },
}));

export default useInsuranceStore;
