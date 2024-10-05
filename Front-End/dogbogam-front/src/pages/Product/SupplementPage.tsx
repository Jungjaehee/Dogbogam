// import { useEffect } from "react";
import { TopBar } from "../../components/Topbar/index";
import SearchBar from "../../components/SearchBar/index";
// import useDogStore from "../../store/UseDogStore";
// import { BASE_URL } from "../../api/APIconfig";


// interface Insurance {
//   insuranceId: number;
//   name: string;
//   company: string;
//   minAge: string;
//   maxAge: string;
//   fee: string;
//   limit: string;
//   period: string;
//   description: string;
//   coverageRatio: string;
//   s3ImageName: string;
//   s3ImageUrl: string;
// }

export const SupplementPage = () => {
  // const { dogInfo } = useDogStore();
  
  // useEffect(() => {
  //   const fetchInsurances = async() => {
  //     try {
  //       const insurance: Insurance = await get
  //     }
  //   };
  //   fetchInsurances();
  // })

  const handleSearch = (query: string) => {
    console.log("검색어", query);
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        <TopBar pre={"/home"} title={""} skip={""} />
        <div className="mb-6">
          <p className="text-gray-800 text-xl font-bold">
            {/* <span className="text-main-color">{`${dogInfo.name}`}</span> */}
            를 위한, <br /> 펫보험
          </p>
        </div>
        <div className="mb-6">
          <SearchBar onSearch={handleSearch} />
        </div>
        <label htmlFor="disease" className="block font-medium text-sm mb-2">
          질병유무 <span className="text-main-color">*</span>
        </label>
        <div className=" flex gap-2 justify-center mb-6 space-x-2 ">
          <div className=" bg-main-color rounded-lg shadow-lg p-2 w-[160px] h-[43px]">
            <p className="font-medium text-center text-gray-0">없음</p>
          </div>
          <div className=" border-gray-100 rounded-lg shadow-lg p-2 w-[160px] h-[43px]">
            <p className="font-medium text-center">있음</p>
          </div>
        </div>
        <div className="grid grid-rows-4 gap-4">
          <div className="border border-gray-200 rounded-lg shadow-lg flex justify-between p-5">
            <div className="space-y-5">
              <div className="space-y-1">
                {/* <p className="font-semibold">{insurance.name}</p> */}
                {/* <p className="text-gray-500 text-sm">{`${insurance.fee}`}원</p> */}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
