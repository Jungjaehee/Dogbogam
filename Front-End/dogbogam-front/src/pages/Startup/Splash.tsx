import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Logo from "../../assets/Startup/logo.png";

export const Splash = () => {
  const navigate = useNavigate();
  useEffect(() => {
    setTimeout(() => {
      navigate("/start", { replace: true });
    }, 2500);
  }, []);

  return (
    <div className="h-full flex flex-col justify-center px-4 bg-main-color text-center">
      <div className="flex place-content-center">
        <img src={Logo} alt="" className="w-2/5" />
      </div>
    </div>
  );
};
