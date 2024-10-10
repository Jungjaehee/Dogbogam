import { useState } from "react";

const DiagnosisFilter = ({
  onFilterChange,
}: {
  onFilterChange: (filter: string) => void;
}) => {
  const [CurrentFilter, setCurrentFilter] = useState("");

  // 필터 바꾸는 함수
  const FilterChange = (filter: string) => {

    if (CurrentFilter === filter) {
      setCurrentFilter("");
      onFilterChange("");
    } 
    else {
      setCurrentFilter(filter);
      onFilterChange(filter);
    }
  };

  return (
    <div className="flex items-center justify-start border-gray-300 w-full my-3">
      {["눈", "피부", "비만"].map((filter) => (
        <button
          key={filter}
          onClick={() => FilterChange(filter)}
          className={`px-4 py-2 text-sm relative flex-1 text-center ${
            CurrentFilter === filter
              ? "text-black font-semibold border-b-2 border-gray-700"
              : "text-gray-500 border-b border-gray-500"
          }`}
        >
          {filter}
        </button>
      ))}
    </div>
  );
};

export default DiagnosisFilter;
