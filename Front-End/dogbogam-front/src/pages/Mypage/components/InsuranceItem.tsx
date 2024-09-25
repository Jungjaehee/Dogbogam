interface Insurance {
  insurance: {
    insuranceId: number; 
    name: string; 
    minEntryAge?: string | null; 
    maxEntryAge?: string | null; 
    premium: number; 
    coveragePeriod: string; 
    description: string; 
    coverageLimit?: string | null; 
    compensationRate: string; 
    insuranceCompany: string; 
    imageName?: string | null;
    imageUrl?: string | null; 
    createdAt: Date; 
    modifiedAt: Date;
  };
}
