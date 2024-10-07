export interface Supplements {
  supplementId: number;
  productName: string;
  imageUrl: string;
  offer: string;
  price: number;
}

export interface supplement {
  supplementId: number;
  productName: string;
  brandName: string;
  target: string;
  how: string;
  offer: string;
  type: string;
  basis: string;
  protein: string;
  fat: string;
  feature: string;
  price: number;
  imageName?: string;
  imageUrl: string;
}

export interface recommendSupplement {
  supplementId: number;
  productName: string;
  offer: string;
  price: number;
  imageUrl: string;
}
