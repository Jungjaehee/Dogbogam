import { Route, Routes } from "react-router-dom";
import HomePages from "./routes/HomePages";
import { Splash } from "./pages/Startup/Splash";
import { Start } from "./pages/Startup/Start";
import { Login } from "./pages/Login";
import SignupPages from "./routes/SignupPages";
import AIpredictionPages from "./routes/AIpredictionPages";

function App() {
  return (
    <>
      {/* 너비 360px, 높이 780px로 고정 */}
      <div className="w-[360px] h-[780px] relative">
        <Routes>
          <Route path="/home/*" element={<HomePages />} />
          <Route path="/" element={<Splash />} />
          <Route path="/start" element={<Start />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup/*" element={<SignupPages />} />
          <Route path="/AI/*" element={<AIpredictionPages />} />
        </Routes>
      </div>
    </>
  );
}

export default App;
