import { create } from "zustand";
import { persist } from "zustand/middleware";
import type { MedicalRecord } from "../models/record.model";

interface StoreState {
  medicalRecordList: MedicalRecord[]; 
  setMedicalRecordList: (newList: MedicalRecord[]) => void; 
  medicalRecordDetail: MedicalRecord; 
  setMedicalRecord: (newInfo: Partial<MedicalRecord>) => void; 
}

const useMedicalRecordStore = create(
  persist<StoreState>(
    (set) => ({
      medicalRecordList: [],
      setMedicalRecordList: (newList: MedicalRecord[]) =>
        set(() => ({
          medicalRecordList: newList, 
        })),
      medicalRecordDetail: {
        medicalRecordId: 0,
        dogId: 0,
        content: "", 
        hospital: "",
        imageName: "",
        imageUrl: "",
        recordTime: "" as unknown as Date,
        createdAt: "" as unknown as Date,
        modifiedAt: "" as unknown as Date,
        cost: 0,
      },

      setMedicalRecord: (newInfo: Partial<MedicalRecord>) =>
        set((state) => ({
          medicalRecordDetail: { ...state.medicalRecordDetail, ...newInfo },
        })),
    }),
    {
      name: "medicalRecordStorage", 
    }
  )
);

export default useMedicalRecordStore;
