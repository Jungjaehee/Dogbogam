import { Route, Routes } from "react-router-dom";
import HomePages from "./routes/HomePages";

function App() {
  return (
    <>
      {/* 너비 360px, 높이 780px로 고정 */}
      <div className="w-[360px] h-[780px] relative">
        <Routes>
          <Route path="/home/*" element={<HomePages />} />
        </Routes>
      </div>
    </>
  );
}

export default App;
