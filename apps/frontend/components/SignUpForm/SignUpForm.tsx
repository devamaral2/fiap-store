"use client";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useAuthContext } from "@/context/auth-context";
import Image from "next/image";
import Link from "next/link";
export function SignUpForm() {
  const { register } = useAuthContext();
  return (
    <form onSubmit={register}>
      <div className="w-full lg:grid lg:min-h-[600px] lg:grid-cols-2 xl:min-h-[800px] bg-white">
        <div className="flex items-center justify-center py-12">
          <div className="mx-auto grid w-[350px] gap-6">
            <div className="grid gap-2 text-center">
              <h1 className="text-3xl font-bold text-gray-700">Cadastro</h1>
              <p className="text-balance text-muted-foreground">
                Entre com seu email e senha para cadastrar sua conta.
              </p>
            </div>
            <div className="grid gap-4">
              <div className="grid gap-2">
                <Label
                  htmlFor="name"
                  className="flex-1 text-left text-gray-700"
                >
                  Nome de Usuário
                </Label>
                <Input
                  id="name"
                  name="name"
                  type="text"
                  placeholder="@zeninguem"
                  className="w-full border-gray-700"
                  required
                />
              </div>
              <div className="grid gap-2">
                <Label
                  htmlFor="email"
                  className="flex-1 text-left text-gray-700"
                >
                  Email
                </Label>
                <Input
                  id="email"
                  type="text"
                  name="email"
                  placeholder="zeninguem@exemplo.com"
                  className="w-full border-gray-700"
                  required
                />
              </div>
              <div className="grid gap-2">
                <div className="flex items-center">
                  <Label
                    htmlFor="password"
                    className="flex-1 text-left text-gray-700"
                  >
                    Senha
                  </Label>
                </div>
                <Input
                  id="password"
                  type="password"
                  name="password"
                  required
                  className="w-full border-gray-700"
                />
              </div>
              <div className="grid gap-2">
                <div className="flex items-center">
                  <Label
                    htmlFor="password"
                    className="flex-1 text-left text-gray-700"
                  >
                    Confirme sua Senha
                  </Label>
                </div>
                <Input
                  id="password-confirm"
                  type="password"
                  name="password2"
                  required
                  className="w-full border-gray-700"
                />
              </div>
              <Button type="submit" className="w-full">
                Cadastre-se
              </Button>
            </div>
            <div className="mt-4 text-center text-sm text-gray-700">
              Já tem uma conta?{" "}
              <Link href="/" className="underline text-gray-600">
                Faça Login
              </Link>
            </div>
          </div>
        </div>
        <div className="hidden bg-muted lg:block">
          <Image
            src="/images/itens/services-3.jpg"
            alt="Image"
            width="1920"
            height="1080"
            className="h-full w-full object-cover dark:brightness-[0.2] dark:grayscale"
          />
        </div>
      </div>
    </form>
  );
}
