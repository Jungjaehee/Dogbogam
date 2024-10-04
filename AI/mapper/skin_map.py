class SkinLabelMapper:
    label_mapping = {
            1: "구진플라그",
            2: "비듬 각질 상피성잔고리",
            3: "태선화 과다색소 침착",
            4: "농포 여드름",
            5: "미란 궤양",
            6: "결절 종괴",
            7: "정상"
        }

    @staticmethod
    def get_label(label_id):
        return SkinLabelMapper.label_mapping.get(label_id, "알 수 없는 레이블")