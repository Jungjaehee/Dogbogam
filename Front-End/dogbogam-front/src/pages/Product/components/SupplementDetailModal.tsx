import React from 'react';
import { supplement } from '../../../api/supplementAPI';


interface SupplementDetailModalProps {
  supplementDetail: supplement | null;
  onClose: () => void;
}

const SupplementDetailModal: React.FC<SupplementDetailModalProps> = ({ supplementDetail, onClose }) => {
    if (!supplementDetail) return null;
  
    return  (
      <div className="fixed inset-0 bg-gray-800 bg-opacity-75 flex items-center justify-center overflow-auto z-50">
        <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-80 mx-auto relative">
          <button onClick={onClose} className="absolute top-2 right-2 text-xl">&times;</button>
          <div className="space-y-4">
              <p className="text-lg font-bold">
              
              </p>
          </div>
          <div className="flex flex-col items-center mb-3">
            <img src={supplementDetail.imageUrl} className="w-20 h-20 mb-3 rounded-full shadow-md object-cover" />
            <h2 className="text-xl font-bold text-center text-gray-900"> {supplementDetail.brandName} {supplementDetail.productName}</h2>
          </div>
          <div className="space-y-4">
            <p className="text-lg text-gray-700"><span className="font-semibold">가격:</span> {supplementDetail.price}</p>
            <p className="text-lg text-gray-700"><span className="font-semibold">먹는 방법:</span> {supplementDetail.how}</p>
            <p className="text-lg text-gray-700"><span className="font-semibold">나이:</span> {supplementDetail.target}</p>
            <p className="text-lg text-gray-700"><span className="font-semibold">효과:</span> {supplementDetail.offer}</p>
            <p className="text-lg text-gray-700"><span className="font-semibold">재료:</span> {supplementDetail.basis}</p>
            <p className="text-lg text-gray-700"><span className="font-semibold">특징:</span> {supplementDetail.feature}</p>
          </div>
        </div>
      </div>
    );
  };

export default SupplementDetailModal;