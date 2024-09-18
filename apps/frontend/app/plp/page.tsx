"use client";
import VerticalCard from "@/components/Cards/VerticalCard/VerticalCard";
import WrapperWithTitle from "@/components/WrapperWithTitle/WrapperWithTitle";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { useAuthContext } from "@/context/auth-context";
import { useGlobalContext } from "@/context/global-context";
import ProductsMock from "@/mock/products-mock";
import { useRouter, useSearchParams } from "next/navigation";
import { Suspense, useEffect, useState } from "react";

function PLPScreen() {
  const [type, setType] = useState<"products" | "services">("products");
  const searchParams = useSearchParams();
  const { products } = useGlobalContext();
  const router = useRouter();
  const param = searchParams.get("type");
  useAuthContext();

  useEffect(() => {
    if (param === "products") {
      setType("products");
    }
    if (param === "services") {
      setType("services");
    } else {
      setType("products");
    }
  }, [param]);

  if (!products) {
    return <div>Loading...</div>;
  }

  return (
    <div className="items-center justify-items-center min-h-[40dvw] p-8 pb-20 gap-16 sm:p-20 bg-white">
      <WrapperWithTitle
        title="Conheça nossos Produtos e Serviços"
        subtitle="Aqui você encontra tudo o que precisa!"
      >
        <Tabs
          defaultValue={param === "services" ? "services" : "products"}
          value={type}
          onChange={(value) => {
            setType(value as unknown as "products" | "services");
          }}
          className="w-full flex flex-col items-center justify-center gap-2"
        >
          <TabsList className="grid grid-cols-2 w-[200px] gap-2">
            <TabsTrigger
              value="products"
              onClick={() => {
                router.replace("/plp?type=products");
                setType("products");
              }}
            >
              Produtos
            </TabsTrigger>
            <TabsTrigger
              value="services"
              onClick={() => {
                router.replace("/plp?type=services");
                setType("services");
              }}
            >
              Serviços
            </TabsTrigger>
          </TabsList>
          <TabsContent
            value="products"
            className="w-full flex flex-row items-start justify-center gap-6 flex-wrap"
          >
            {ProductsMock.filter(
              (product) => product.category === "product"
            ).map((product) => (
              <VerticalCard key={product.id} {...product} />
            ))}
          </TabsContent>
          <TabsContent
            value="services"
            className="w-full flex flex-row items-start justify-center gap-6 flex-wrap"
          >
            {ProductsMock.filter(
              (product) => product.category === "service"
            ).map((service) => (
              <VerticalCard key={service.id} {...service} />
            ))}
          </TabsContent>
        </Tabs>
      </WrapperWithTitle>
    </div>
  );
}

export default function PLP() {
  return (
    <Suspense fallback={<div>Carregando...</div>}>
      <PLPScreen />
    </Suspense>
  );
}
