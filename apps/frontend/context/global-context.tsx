"use client";
import { CartDTO, ProductsDTO } from "@/mock/products-mock";
import axios from "axios";
import { parseCookies } from "nookies";
import { createContext, useContext, useEffect, useMemo, useState } from "react";

const GlobalContext = createContext({});

interface GlobalProviderDTO {
  cart: ProductsDTO[];
  products: ProductsDTO[];
  addToCart: (product: any) => Promise<void>;
  removeFromCart: (product: any) => Promise<void>;
  clearCart: () => Promise<void>;
  totalPrice: number;
}

const GlobalProvider = ({ children }: any) => {
  const [cart, setCart] = useState<ProductsDTO[]>([]);
  const [products, setProducts] = useState<ProductsDTO[]>([]);
  const [totalPrice, setTotalPrice] = useState<number>(0);

  const persistanceCart = async (items: ProductsDTO[]) => {
    const cookies = parseCookies();
    const cartItems: CartDTO[] = items.map((item) => ({
      productId: item.id,
      name: item.name,
      imageUrl: item.imageUrl,
      quantity: item.quantity,
      price: item.price,
    }));
    await axios.patch(
      "http://localhost:8001/carts/update-cart",
      { cartItems },
      {
        headers: {
          Authorization: `Bearer ${cookies.token}`,
        },
      }
    );
  };

  const addToCart = async (product: ProductsDTO) => {
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
      await persistanceCart(newCart);
    } else {
      setCart([...cart, { ...product }]);
      await persistanceCart([...cart, { ...product }]);
    }
  };

  const removeFromCart = async (productId: string) => {
    const newCart = cart.filter((item) => item.id !== productId);

    setCart(newCart);
    await persistanceCart(newCart);
  };

  const clearCart = async () => {
    setCart([]);
    await persistanceCart([]);
  };

  const priceCalculator = cart.reduce(
    (acc, item) => acc + item.price * item.quantity,
    0
  );

  const fetchProducts = async () => {
    const { data } = await axios.get("http://localhost:8001/products/list");
    setProducts(data);
  };

  const fetchCart = async () => {
    const cookies = parseCookies();
    const { data } = await axios.get("http://localhost:8001/carts/find", {
      headers: {
        Authorization: `Bearer ${cookies.token}`,
      },
    });

    const cartItems = data.map((item: CartDTO) => ({
      id: item.productId,
      name: item.name,
      price: item.price,
      imageUrl: item.imageUrl,
      quantity: item.quantity,
    }));

    setCart(cartItems);
  };

  useEffect(() => {
    setTotalPrice(priceCalculator);
  }, [cart]);

  useEffect(() => {
    fetchProducts();
    fetchCart();
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
