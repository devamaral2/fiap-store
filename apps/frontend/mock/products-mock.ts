interface ProductsDTO {
  id: string;
  name: string;
  description: string;
  price: number;
  imageUrl: string;
  category: "services" | "products";
  quantity: number;
}

interface CartDTO {
  id?: number;
  name: string;
  productId: string;
  description?: string;
  price: number;
  imageUrl: string;
  category?: "services" | "products";
  quantity: number;
}

export type { CartDTO, ProductsDTO };
