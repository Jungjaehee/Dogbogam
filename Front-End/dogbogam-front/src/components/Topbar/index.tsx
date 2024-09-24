import { Link, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleLeft } from "@fortawesome/free-solid-svg-icons/faAngleLeft";

interface TopBarProps {
  pre: String;
  title: String;
  skip: String;
}

export const TopBar = ({ pre, title, skip }: TopBarProps) => {
  const navigate = useNavigate();
  const place = skip ? "justify-between" : "";
  const center = title ? "place-content-center" : "";
  const position = title ? "absolute" : "";

  const handleClick = () => {
    if (pre) {
      navigate(`${pre}`); // pre가 있을 경우 해당 경로로 이동
    } else {
      navigate(-1); // pre가 없을 경우 이전 페이지로 이동
    }
  };

  return (
    <div className={`relative flex py-3 mb-3 ${place} ${center}`}>
      <FontAwesomeIcon
        icon={faAngleLeft}
        className={`w-6 left-0 ${position}`}
        onClick={() => {
          handleClick();
        }}
      />
      <p className="font-semibold">{title}</p>
      {skip ? (
        <Link to={`${skip}`} className="text-main-color mt-1">
          건너뛰기
        </Link>
      ) : null}
    </div>
  );
};
