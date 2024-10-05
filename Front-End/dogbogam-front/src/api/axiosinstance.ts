import axios from "axios";
import { BASE_URL } from "./APIconfig";

const axiosInstance = axios.create({
  baseURL: `${BASE_URL}`, // 배포 URL
  timeout: 50000, // 요청 타임아웃
});

// 이거 임포트 받아서 요청 보내면 됨

axiosInstance.interceptors.request.use(
  (config) => {
     const userStorage = localStorage.getItem("userStorage");
     const parseData = JSON.parse(userStorage);
     const token = parseData.state.token;

    // 헤더에 토큰 넣어주기
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosInstance;