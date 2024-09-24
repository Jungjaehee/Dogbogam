import { TopBar } from "../../components/Topbar";
import dog from "../../assets/AIprediction/loading.gif";
import { DogInfo } from "../../utils/dogInfo";

export const Loading = () => {
  const Info: { title: string; content: string }[] = DogInfo;
  const randomIndex = Math.floor(Math.random() * Info.length);
  const randomInfo = Info[randomIndex];

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <TopBar pre={""} title={""} skip={""} />
      <div className="space-y-3">
        <p className="text-xl font-semibold">
          <span>AI 결과 분석</span>중이에요
        </p>
        <p className="text-gray-400 font-medium text-sm">
          앱을 종료하거나 이전으로 돌아갈 경우 진단이 종료돼요
        </p>
      </div>
      <div className="flex flex-col flex-grow justify-center place-items-center place-content-center space-y-8 ">
        <img src={dog} alt="" className="w-[200px] h-[200px]" />
        <div className="space-y-5 border border-gray-500 rounded-lg py-5 px-3 text-center bg-[#FFF1CB] shadow-lg">
          <p className="font-medium">{randomInfo.title}</p>
          <hr className="border-[0.5px] border-gray-500 w-full" />
          <p className="text-sm text-gray-500">{randomInfo.content}</p>
        </div>
      </div>
    </div>
  );
};
