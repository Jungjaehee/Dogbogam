import { Route, Routes } from "react-router-dom";
import { SignupSuccess } from "../pages/Signup/SignupSuccess";
import { Signup } from "../pages/Signup/Signup";
import { DogInfo } from "../pages/Signup/DogInfo";
import { DogHealth } from "../pages/Signup/DogHealth";

const SignupPages = () => {
  return (
    <div className="h-[100vh] relative">
      <Routes>
        <Route path="/" element={<Signup />} />
        <Route path="/dog" element={<DogInfo />} />
        <Route path="/health" element={<DogHealth />} />
        <Route path="/success" element={<SignupSuccess />} />
      </Routes>
    </div>
  );
};

export default SignupPages;
