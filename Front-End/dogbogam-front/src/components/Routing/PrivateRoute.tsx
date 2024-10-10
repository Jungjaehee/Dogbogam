import { Navigate } from "react-router-dom";
import useUserStore from "../../store/UseUserStore";
import { ReactNode } from "react";

interface PrivateRouteProps {
  children: ReactNode;
}

const PrivateRoute = ({ children }: PrivateRouteProps) => {
  
    const { token } = useUserStore();


  return token ? children : <Navigate to={"/login"} />;
};

export default PrivateRoute;
