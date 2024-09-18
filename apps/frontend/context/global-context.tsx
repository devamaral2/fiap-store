"use client";
import { ProductsDTO } from "@/mock/products-mock";
import axios from "axios";
import { parseCookies } from "nookies";
import { createContext, useContext, useEffect, useMemo, useState } from "react";

const GlobalContext = createContext({});

interface GlobalProviderDTO {
  cart: ProductsDTO[];
  products: ProductsDTO[];
  addToCart: (product: any) => void;
  removeFromCart: (product: any) => void;
  clearCart: () => void;
  totalPrice: number;
}

const GlobalProvider = ({ children }: any) => {
  const [cart, setCart] = useState<ProductsDTO[]>([]);
  const [products, setProducts] = useState<ProductsDTO[]>([]);
  const [totalPrice, setTotalPrice] = useState<number>(0);

  const addToCart = (product: ProductsDTO) => {
    const alreadyInCart = cart.some(
      (item: ProductsDTO) => item.id === product.id
    );

    if (alreadyInCart) {
      const newCart = cart.map((item: ProductsDTO) =>
        item.id === product.id
          ? { ...item, quantity: item.quantity + product.quantity }
          : item
      );
      setCart(newCart);
    } else {
      setCart([...cart, { ...product }]);
    }
  };

  const removeFromCart = (productId: number) => {
    const newCart = cart.filter((item) => item.id !== productId);
    setCart(newCart);
  };

  const clearCart = () => {
    setCart([]);
  };

  const priceCalculator = cart.reduce(
    (acc, item) => acc + item.price * item.quantity,
    0
  );

  async function getProducts() {
    const cookies = parseCookies();
    const { data } = await axios.get("http://localhost:8080/products/list", {
      headers: {
        Authorization: `Bearer ${cookies.token}`,
      },
    });
    console.log(data);
    setProducts(data);
  }

  useEffect(() => {
    setTotalPrice(priceCalculator);
  }, [cart]);

  useEffect(() => {
    getProducts();
  }, []);

  const memoizedContext = (obj: any) => useMemo(() => obj, [obj]);
  const context: GlobalProviderDTO = memoizedContext({
    cart,
    addToCart,
    removeFromCart,
    clearCart,
    totalPrice,
    products,
  });

  const { Provider } = GlobalContext;

  return <Provider value={context}>{children}</Provider>;
};

const useGlobalContext = () => useContext(GlobalContext) as GlobalProviderDTO;

export { GlobalProvider, useGlobalContext };
export type { GlobalProviderDTO };
