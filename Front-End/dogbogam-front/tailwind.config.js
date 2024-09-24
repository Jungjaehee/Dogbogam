/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        "main-color": "#FFCF50",
        "sub-color": "#402E32",
        "gray-0": "#ffffff",
        "gray-50": "#f8f8f8",
        "gray-100": "#f4f4f4",
        "gray-200": "#e3e5e7",
        "gray-300": "#cacdd2",
        "gray-400": "#9fa4a9",
        "gray-500": "#73787e",
        "gray-600": "#464c52",
        "gray-700": "#26282b",
        "gray-800": "#1b1d1f",
        "blue-000": "#dbeaff",
        "blue-100": "#0e6eff",
        "blue-200": "#005eed",
        "yellow-200": "#FCB200",
        "ai-button": "#FFF1CB",
        "insu-button": "#CEF1DA",
        "good-text": "#60BF81",
        "bad-text": "#FF0000",
        "warning-text": "#FF8A00",
        "blue-200": "#7660FF",
      },
    },
  },
  plugins: [],
};
