import maleIcon from "../../../assets/MyPage/MaleIcon.png";
import femaleIcon from "../../../assets/MyPage/FemaleIcon.png";

// 성별에 따른 아이콘 컴포넌트
const GenderIcon = ({ gender, className }: { gender: string; className?: string }) => {
  const iconSrc = gender === "male" ? maleIcon : femaleIcon;

  return (
    <img
      src={iconSrc}
      alt={gender === "male" ? "Male icon" : "Female icon"}
      className={`w-4 h-4 ${className}`}
    />
  );
};

export default GenderIcon;
