import { AiDiagnosis } from "../../../models/record.model";

export const Breed = (props: { result: AiDiagnosis }) => {
  return (
    <div className="bg- p-5 rounded-lg place-items-center space-y-3">
      <div className="flex flex-col items-center mb-6">
        <img
          src={props.result.imageUrl}
          alt="Dog"
          className="w-40 h-40 mb-5 rounded-full shadow-md object-cover"
        />
      </div>
      <div className="space-y-6">
        {props.result.diseases.map((disease, index) => (
          <div key={index} className="flex flex-col  items-start space-y-1">
            <p className="text-sm font-semibold text-gray-600 mb-1">{disease.disease}</p>
            <div className="flex items-center w-full">
              <div className="flex-1 bg-gray-200 rounded-full h-5 overflow-hidden">
                <div
                  className="bg-main-color h-full text-white flex items-center justify-end pr-2 rounded-full"
                  style={{ width: `${disease.percentage}%` }}
                >
                </div>
              </div>
              <div className="ml-2 text-sm text-gray-600">
                {`${disease.percentage}%`}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
