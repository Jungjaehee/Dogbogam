import { Route, Routes } from "react-router-dom";
import { Home } from "../pages/Home/Home";
import { Insurance } from "../pages/Product/Insurance";
import { Supplement } from "../pages/Product/Supplement";
import { FindBreed } from "../pages/Breed/FindBreed";
import { Map } from "../pages/Map/Map";

const HomePages = () => {
  return (
    <div className="w-[360px] h-[780px] relative">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/insurance" element={<Insurance />} />
        <Route path="/supplement" element={<Supplement/>} />
        <Route path="/breed" element={<FindBreed/>} />
        <Route path="/map" element={<Map/>} />
      </Routes>
    </div>
  );
};

export default HomePages;
