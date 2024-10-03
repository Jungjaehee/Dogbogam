class EyeLabelMapper:
    label_mapping = {
            0: "결막염",
            1: "궤양성각막질환",
            2: "백내장",
            3: "비궤양성각막질환",
            4: "색소침착성각막염",
            5: "안검내반증",
            6: "안검염",
            7: "안검종양",
            8: "유루증",
            9: "정상",
            10: "핵경화"
        }

    @staticmethod
    def get_label(label_id):
        return EyeLabelMapper.label_mapping.get(label_id, "알 수 없는 레이블")