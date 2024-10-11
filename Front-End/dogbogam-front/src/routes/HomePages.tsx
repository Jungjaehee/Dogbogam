import { Route, Routes } from "react-router-dom";
import { Home } from "../pages/Home/Home";
import { InsurancePage } from "../pages/Product/InsurancePage";
import { SupplementPage } from "../pages/Product/SupplementPage";
import { FindBreed } from "../pages/Breed/FindBreed";
import { Map } from "../pages/Map/Map";

const HomePages = () => {
  return (
    <div className="h-[100vh] relative">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/insurance" element={<InsurancePage />} />
        <Route path="/supplement" element={<SupplementPage />} />
        <Route path="/breed" element={<FindBreed />} />
        <Route path="/map" element={<Map />} />
      </Routes>
    </div>
  );
};

export default HomePages;
