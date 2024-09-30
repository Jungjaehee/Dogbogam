import Kakao from "../../components/kakao";
import { TopBar } from "../../components/Topbar";

interface Dog {
  dogId: number;
  dogName: string;
}

export const Map = () => {
  const dog: Dog = {
    dogId: 1,
    dogName: "새우",
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        <TopBar pre={"/home"} title={""} skip={""} />
        <div className="mb-6">
          <p className="text-gray-800 text-xl font-bold">
            <span className="text-main-color">{`${dog.dogName}`} </span>
            근처 <br /> 동물병원이에요
          </p>
        </div>
        <Kakao />
      </div>
    </div>
  );
};
