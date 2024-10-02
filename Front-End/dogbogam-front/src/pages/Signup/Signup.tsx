import { useState } from "react";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useNavigate } from "react-router-dom";
import { checkEmail } from "../../api/userAPI";
import { inputUserInfo } from "../../models/user.model";

export const Signup = () => {
  const navigate = useNavigate();
  const [message, setMessage] = useState("");
  const [color, setColor] = useState("");
  const [pwd, setPwd] = useState("");
  const [valid, setValid] = useState(true);
  const [inputUserInfo, setInputUserInfo] = useState<inputUserInfo>({
    nickname: "",
    email: "",
    password: "",
  });

  // 비밀번호 검증
  const checkPwd = (input: string) => {
    if (pwd !== input) setValid(false);
    else setValid(true);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setInputUserInfo((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault(); // 기본 제출 동작 방지
    if (valid) {
      navigate("/signup/dog", {
        state: {
          inputUserInfo,
        },
      });
    }
  };

  const check = async (input: string) => {
    const checkResponse = await checkEmail(input);
    setMessage(checkResponse.responseMessage);
    if (!checkResponse.isDuplicate) {
      setColor("text-green-500");
    } else {
      setColor("text-red-500");
      setValid(false);
    }
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col">
      <TopBar pre={"/login"} title={""} skip={""} />
      <p className="text-xl font-semibold mb-5">회원 정보를 입력해주세요</p>
      <form
        className="flex flex-col justify-between flex-grow"
        onSubmit={handleSubmit}
      >
        <div className="space-y-5">
          {/* 이름 */}
          <div className="space-y-2">
            <label htmlFor="name" className="block font-medium text-sm">
              이름 <span className="text-main-color">*</span>
            </label>
            <input
              type="text"
              name="nickname"
              value={inputUserInfo.nickname}
              placeholder="이름 입력"
              required
              maxLength={20}
              className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
              onChange={handleChange} // 상태 업데이트
            />
          </div>
          {/* 이메일 */}
          <div className="space-y-2">
            <label htmlFor="email" className="block font-medium text-sm">
              이메일 <span className="text-main-color">*</span>
            </label>
            <input
              type="email"
              name="email"
              value={inputUserInfo.email}
              placeholder="이메일 입력"
              required
              maxLength={30}
              className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
              onChange={handleChange} // 상태 업데이트
              onBlur={() => check(inputUserInfo.email)} // 포커스 아웃 시 check 함수 호출
            />
            {message && <p className={`${color} text-sm`}>{message}</p>}
          </div>
          {/* 비밀번호 */}
          <div className="space-y-2">
            <label htmlFor="password" className="block font-medium text-sm">
              비밀번호 <span className="text-main-color">*</span>
            </label>
            <input
              type="password"
              name="password"
              placeholder="비밀번호 입력"
              required
              minLength={4}
              maxLength={20}
              className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
              onChange={(e) => {
                handleChange(e); // 상태 업데이트
                setPwd(e.target.value);
                setValid(true); // 비밀번호 입력 시 유효성 초기화
              }}
            />
            <input
              type="password"
              placeholder="비밀번호 재확인"
              required
              minLength={4}
              maxLength={20}
              className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
              onChange={(e) => {
                checkPwd(e.target.value);
              }}
            />
            {valid ? (
              ""
            ) : (
              <p className="text-sm text-red-500">비밀번호를 확인해주세요</p>
            )}
          </div>
        </div>
        <Button
          text={"다음"}
          onClick={() => {
            // signup();
          }}
          bgColor={"bg-main-color"}
        />
      </form>
    </div>
  );
};
