import { Route, Routes } from "react-router-dom";
import { FindBreed } from "../pages/Breed/FindBreed";
import { TakePhoto } from "../pages/Breed/TakePhoto";
import { CheckPhoto } from "../pages/Breed/CheckPhoto";
import { BreedResult } from "../pages/Breed/BreedResult";


const BreedPages = () => {
  return (
    <div className="h-[100vh] relative">
      <Routes>
        <Route path="/" element={<FindBreed />} />
        <Route path="/photo" element={<TakePhoto />} />
        <Route path="/check" element={<CheckPhoto />} />
        <Route path="/result" element={<BreedResult />} />
      </Routes>
    </div>
  );
};

export default BreedPages;
