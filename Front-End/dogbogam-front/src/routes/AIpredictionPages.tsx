import { Route, Routes } from "react-router-dom";
import { SelectPart } from "../pages/AIprediction/SelectPart";
import { TakePhoto } from "../pages/AIprediction/TakePhoto";
import { CheckPhoto } from "../pages/AIprediction/CheckPhoto";
import { AIResult } from "../pages/AIprediction/AIResult";

const AIpredictionPages = () => {
  return (
    <div className="w-[360px] h-[780px] relative">
      <Routes>
        <Route path="/" element={<SelectPart />} />
        <Route path="/photo" element={<TakePhoto />} />
        <Route path="/check" element={<CheckPhoto />} />
        <Route path="/result" element={<AIResult />} />
      </Routes>
    </div>
  );
};

export default AIpredictionPages;
