import { create } from "zustand";
import type { VaccinationRecord } from "../models/record.model";

interface StoreState {
  vaccinationList: VaccinationRecord[];
  setVaccinationList: (newList: VaccinationRecord[]) => void;
  vaccinationDetail: VaccinationRecord;
  setVaccination: (newInfo: Partial<VaccinationRecord>) => void;
}

const useVaccinationStore = create<StoreState>((set) => ({
  vaccinationList: [],
  setVaccinationList: (newList: VaccinationRecord[]) =>
    set(() => ({
      vaccinationList: newList,
    })),
  vaccinationDetail: {
    vaccinationRecordId: 0,
    dogId: 0,
    content: "",
    hospital: "",
    imageName: "",
    imageUrl: "",
    recordTime: "" as unknown as Date,
    vaccinationRound: 0,
    createdAt: "" as unknown as Date,
    modifiedAt: "" as unknown as Date,
    cost: 0,
  },
  setVaccination: (newInfo: Partial<VaccinationRecord>) =>
    set((state) => ({
      vaccinationDetail: { ...state.vaccinationDetail, ...newInfo },
    })),
}));

export default useVaccinationStore;
