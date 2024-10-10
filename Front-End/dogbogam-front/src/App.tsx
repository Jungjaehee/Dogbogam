import { Route, Routes, useLocation } from "react-router-dom";
import HomePages from "./routes/HomePages";
import { Splash } from "./pages/Startup/Splash";
import { Start } from "./pages/Startup/Start";
import { Login } from "./pages/Login";
import SignupPages from "./routes/SignupPages";
import AIpredictionPages from "./routes/AIpredictionPages";
import MyPages from "./routes/MyPages";
import { Navbar } from "./components/Navbar";
import BreedPages from "./routes/BreedPages";
import PrivateRoute from "./components/Routing/PrivateRoute";
import RegistPages from "./routes/RegistPages";

function App() {
  const location = useLocation();
  return (
    <>
      {/* 너비 360px, 높이 780px로 고정 */}
      <div className="h-[100vh] relative">
        <Routes>
          {/* 토큰 필요 없는 페이지 */}
          <Route path="/" element={<Splash />} />
          <Route path="/start" element={<Start />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup/*" element={<SignupPages />} />

          {/* 토큰 필요한 페이지 */}
          <Route
            path="/home/*"
            element={
              <PrivateRoute>
                <HomePages />
              </PrivateRoute>
            }
          />

          <Route
            path="/mypage/*"
            element={
              <PrivateRoute>
                <MyPages />
              </PrivateRoute>
            }
          />

          <Route
            path="/AI/*"
            element={
              <PrivateRoute>
                <AIpredictionPages />
              </PrivateRoute>
            }
          />

          <Route
            path="/breed/*"
            element={
              <PrivateRoute>
                <BreedPages />
              </PrivateRoute>
            }
          />
          <Route
            path="/regist/*"
            element={
              <PrivateRoute>
                <RegistPages />
              </PrivateRoute>
            }
          />
        </Routes>
        {location.pathname == "/home" || location.pathname == "/mypage" ? (
          <Navbar />
        ) : null}
      </div>
    </>
  );
}

export default App;
