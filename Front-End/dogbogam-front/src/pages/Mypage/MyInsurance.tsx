import { useEffect } from "react";
import { TopBar } from "../../components/Topbar"
import InsuranceList from "./components/InsuranceList";
import useUserStore from "../../store/UseUserStore";
import useInsuranceStore from "../../store/UseInsuranceStore";
import { getMyInsurances } from "../../api/myPetInsuranceAPI";


const MyInsurance = () => {

  const { dogInfo } = useUserStore();
  const { setInsuranceItemList, insuranceItemList } = useInsuranceStore();

  const getInsurance = async () => {
    const listResponse = await getMyInsurances(dogInfo.dogId);
    setInsuranceItemList(listResponse.insuranceRecords);
  };
  
  const fetchInsurance = () => {
    getInsurance();
  };
  
  useEffect(() => {
    fetchInsurance();
  }, []); // 최초 1회 렌더링 될 때 데이터 호출

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0 overflow-y-scroll">
      <TopBar pre={""} title={""} skip={""} />
      {/* 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">마이 펫 보험</h1>
      <p className="text-gray-500 text-sm mb-2.5">
        반려견에게 가입된 보험을 확인할 수 있어요
      </p>

      {/* 보험 목록 */}
      <InsuranceList insuranceItemList={insuranceItemList} />
    </div>
  );
};

export default MyInsurance;
