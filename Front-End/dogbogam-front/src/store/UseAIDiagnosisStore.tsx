import { create } from "zustand";
import { persist } from "zustand/middleware";
import type { AiDiagnosis, Disease } from "../models/record.model";

interface StoreState {
  diagnosisList: AiDiagnosis[]; // AI 진단 리스트
  setDiagnosisList: (newList: AiDiagnosis[]) => void; // AI 진단 리스트 전체
  diagnosisDetail: AiDiagnosis; // 현재 진단 상세정보
  setDiagnosis: (newInfo: Partial<AiDiagnosis>) => void; // 현재 진단 상세정보
}

const useAIDiagnosisStore = create(
  persist<StoreState>(
    (set) => ({
      diagnosisList: [],
      setDiagnosisList: (newList: AiDiagnosis[]) =>
        set(() => ({
          diagnosisList: newList, // 배열 전체를 업데이트
        })),
      diagnosisDetail: {
        aiDiagnosisId: 0,
        dogId: 0,
        createdAt: "" as unknown as Date, // 만약 Date 객체로 관리하려면 new Date()로 설정 가능
        imageName: "",
        imageUrl: "",
        normal: false,
        diagnosisItem: "",
        diseases: [] as Disease[], // diseases 배열 초기화
      },
      setDiagnosis: (newInfo: Partial<AiDiagnosis>) =>
        set((state) => ({
          diagnosisDetail: { ...state.diagnosisDetail, ...newInfo },
        })),
    }),
    {
      name: "diagnosisStorage", // 저장소 이름
    }
  )
);

export default useAIDiagnosisStore;
