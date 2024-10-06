class ObesityBreedMapper:
    breed_mapping = {
        "그레이하운드": "GRE", "달마시안": "DAL", "닥스훈트": "DAS", "도베르만": "DOB", "골든 리트리버": "GOL", 
        "래브라도 리트리버": "LAB", "말티즈": "MAL", "불독": "BUL", "비글": "BEA", "비숑 프리제": "BIC", 
        "셰퍼드": "SHE", "슈나우저": "SCH", "드리": "DRI", "웰시코기": "WEL", "저먼 셰퍼드": "GER", 
        "진도견": "JIN", "차우차우": "CHL", "차우차우 숏": "CHS", "코카 스파니엘": "COC", "테리어": "TER", 
        "포메라니안": "POM", "푸들": "POO", "하운드": "HOU", "허스키": "HUS", "믹스견": "MUT", 
        "밀란견": "MIL", "미스틱견": "MIS", "코리아견": "KOR", "러시안 블루": "RUS", "페르시안": "PER", 
        "샴": "SIA", "터키시 앙고라": "TUR", "스코티쉬 폴드": "SCO", "믹스": "MIX", "기타": "ETC"
    }

    # 한글 품종을 영문 코드로 변환하는 함수
    def get_breed_code(korean_breed):
        breed_code = breed_mapping.get(korean_breed, "ETC")  # 매핑이 없는 품종은 "ETC"로 처리
        return breed_code
