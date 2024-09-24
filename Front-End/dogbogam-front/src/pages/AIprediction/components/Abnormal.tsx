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

export const Abnormal = (props: { result: Result }) => {
  return (
    <div className="bg-[#FFF1CB] p-5 rounded-lg place-items-center space-y-3">
      <p className="font-semibold">
        진단 결과{" "}
        <span className="text-[#FB5A64]">{props.result.diseases[0].name}</span>
        일 가능성이 높아요
      </p>
      <div className="flex justify-between place-items-center">
        <img
          src={props.result.imageUrl}
          alt=""
          className="bg-[#F4F4F4] rounded-lg place-contents-center w-[120px] h-[120px]"
        />
        <table className="text-sm text-left border-separate border-spacing-y-2">
          <tbody>
            <tr>
              <th className="text-[#9FA4A9] font-medium">
                {props.result.diseases[0].name}
              </th>
              <td className="px-5">{props.result.diseases[0].percentage}</td>
            </tr>
            <tr>
              <th className="text-[#9FA4A9] font-medium">
                {props.result.diseases[1].name}
              </th>
              <td className="px-5">{props.result.diseases[1].percentage}</td>
            </tr>
            <tr>
              <th className="text-[#9FA4A9] font-medium">
                {props.result.diseases[2].name}
              </th>
              <td className="px-5">{props.result.diseases[2].percentage}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};
