import { Link, useNavigate } from "react-router-dom";
import smallLogo from "../../assets/Startup/smallLogo.png";
import dogDoctor from "../../assets/Startup/dogDoctor.png";
import { Button } from "../../components/Button";

export const Start = () => {
  const navigate = useNavigate();

  return (
    <div className="h-full w-full flex flex-col justify-center px-4 text-center bg-main-color">
      <div className="space-y-10 mb-5">
        <div className="flex place-content-center">
          <img src={smallLogo} alt="" className="w-1/3" />
        </div>
        <div className="flex place-content-center">
          <img src={dogDoctor} alt="" className="w-5/6" />
        </div>
      </div>
      <Button
        text="로그인하기"
        bgColor="bg-sub-color"
        onClick={() => navigate("/login")}
      />
      <p className="text-sub-color">
        아직 가입을 안하셨다면?{" "}
        <Link className="font-semibold underline" to={"/signup"}>
          회원가입 하기
        </Link>
      </p>
    </div>
  );
};
