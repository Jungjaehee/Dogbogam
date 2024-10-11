import React, { useState } from "react";
import Search from "../../assets/icons/search.png";

interface SearchBarProps {
  onSearch: (query: string) => void; // 검색 이벤트를 처리할 함수
}

const SearchBar: React.FC<SearchBarProps> = ({ onSearch }) => {
  const [searchQuery, setSearchQuery] = useState("");

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchQuery(e.target.value);
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      onSearch(searchQuery); // 검색어를 부모 컴포넌트로 전달
    }
  };

  return (
    <div className="flex items-center space-x-1 p-2 bg-gray-100 rounded-lg shadow-md w-full h-15">
      <img src={Search} className="w-3.5 h-3.5 ml-3" />
      <input
        type="text"
        value={searchQuery}
        onChange={handleInputChange}
        onKeyDown={handleKeyDown} 
        placeholder="검색어를 입력하세요"
        className="flex-grow p-2 rounded-lg bg-gray-100 text-gray-600 w-full h-15"
      />
    </div>
  );
};

export default SearchBar;
