interface ButtonProps {
  text: string;
  bgColor: string;
  onClick?: () => void;
}

export const Button = ({ text, bgColor, onClick }: ButtonProps) => {
  return (
    <button
      onClick={onClick}
      className={`py-4 my-5 ${bgColor} text-center text-white font-medium block rounded-lg`}
    >
      {text}
    </button>
  );
};
