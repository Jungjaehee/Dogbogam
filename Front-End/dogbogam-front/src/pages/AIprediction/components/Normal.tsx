interface Disease {
  name: string;
  percentage: string;
}

interface Result {
  reportId: number;
  dogId: number;
  diagnosisDate: string;
  imageName: string;
  imageUrl: string;
  normal: boolean;
  diagnosisItem: string;
  diseases: Disease[];
}

export const Normal = (props: { result: Result }) => {
  return (
    <div className="bg-[#D0ECDA] p-5 rounded-lg place-items-center space-y-3">
      <p className="font-semibold">
        진단 결과 <span className="text-[#60BF81]">정상 소견</span>입니다
      </p>
      <div className="flex justify-between place-items-center">
        <img
          src={props.result.imageUrl}
          alt=""
          className="bg-[#F4F4F4] rounded-lg place-contents-center w-[120px] h-[120px]"
        />
        <p className="text-sm">
          AI 질병 예측 결과 <br /> 모든 질병의 가능성이 <br />
          10% 이하로 나타났어요
        </p>
      </div>
    </div>
  );
};
