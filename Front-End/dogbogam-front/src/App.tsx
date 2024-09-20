import { Route, Routes, useLocation } from "react-router-dom";

function App() {
  return (
    <>
      {/* 너비 360px, 높이 780px로 고정 */}
      {/* pt-6 px-4 bg-white // status bar 크기 24px (pt-6), 양쪽 패딩 16px (px-4)으로 고정  */}
      <div className="w-[360px] h-[780px] relative">
        <Routes>{/* <Route path="/" element={} /> */}</Routes>
      </div>
    </>
  );
}

export default App;
