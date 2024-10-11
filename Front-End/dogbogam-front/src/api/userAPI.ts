import axios from "axios";
import { BASE_URL } from "./APIconfig";
import { inputUserInfo } from "../models/user.model";

// 로그인
export const userLogin = async (user: { email: string; password: string }) => {
  try {
    const response = await axios.post(`${BASE_URL}members/login`, {
      email: user.email,
      password: user.password,
    });
    return response?.data?.data;
  } catch (error) {
    console.log("로그인 실패: ", error);
    throw error;
  }
};

// 회원 가입 및 정보 등록
export const checkEmail = async (email: string) => {
  try {
    const response = await axios.post(`${BASE_URL}members/check`, {
      email,
    });
    return response?.data?.data;
  } catch (error) {
    console.log("이메일 중복 체크 실패: ", error);
    throw error;
  }
};

export const userSignup = async (user: inputUserInfo) => {
  try {
    const response = await axios.post(`${BASE_URL}members`, {
      nickname: user.nickname,
      email: user.email,
      password: user.password,
    });
    return response?.data?.data;
  } catch (error) {
    console.log("유저 회원 가입 실패: ", error);
    throw error;
  }
};
