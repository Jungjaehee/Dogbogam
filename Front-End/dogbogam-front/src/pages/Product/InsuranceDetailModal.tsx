// import React from 'react';
import { Insurance } from '../../models/insurance.model';

const InsuranceDetailModal = (props: { insuranceDetail: Insurance, onClose: () => void }) => {
  if (!props.insuranceDetail) return null;

  return (
    <div className="fixed inset-0 bg-gray-800 bg-opacity-75 flex items-center justify-center overflow-auto z-50">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-80 mx-auto relative">
        <button onClick={props.onClose} className="absolute top-2 right-2 text-xl">&times;</button>
        <div className="space-y-4">
            <p className="text-lg font-bold"></p>
        </div>
        <div className="flex flex-col items-center mb-3">
          <img src={props.insuranceDetail.s3ImageUrl} alt="보험 이미지" className="w-20 h-20 mb-5 rounded-full shadow-md object-cover" />
          <h2 className="text-xl font-bold text-center text-gray-900"> 
            {props.insuranceDetail.company} {props.insuranceDetail.name}
          </h2>
        </div>
        <div className="space-y-4">
          <p className="text-lg text-gray-700">
            <span className="font-semibold">연령 제한:</span> {props.insuranceDetail.minAge} ~ {props.insuranceDetail.maxAge}
          </p>
          <p className="text-lg text-gray-700">
            <span className="font-semibold">보험료:</span> {props.insuranceDetail.fee}
          </p>
          <p className="text-lg text-gray-700">
            <span className="font-semibold">보장 한도:</span> {props.insuranceDetail.limit}
          </p>
          <p className="text-lg text-gray-700">
            <span className="font-semibold">설명:</span> {props.insuranceDetail.description}
          </p>
          {/* <p className="text-lg text-gray-700">
            <span className="font-semibold">보장 비율:</span> {props.insuranceDetail.coverageRatio}
          </p> */}
        </div>
      </div>
    </div>
  );
};

export default InsuranceDetailModal;
