import { Route, Routes } from "react-router-dom";
import { SignupSuccess } from "../pages/Signup/SignupSuccess";

const SignupPages = () => {
  return (
    <div className="w-[360px] h-[780px] relative">
      <Routes>
        <Route path="/success" element={<SignupSuccess />} />
      </Routes>
    </div>
  );
};

export default SignupPages;
