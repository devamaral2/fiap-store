import Image from "next/image";
import Link from "next/link";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

export default function Login() {
  return (
    <div className="w-full lg:grid lg:min-h-[600px] lg:grid-cols-2 xl:min-h-[800px] bg-white">
      <div className="hidden bg-muted lg:block">
        <Image
          src="/images/home/services.jpg"
          alt="Image"
          width="1920"
          height="1080"
          className="h-full w-full object-cover dark:brightness-[0.2] dark:grayscale"
        />
      </div>
      <div className="flex items-center justify-center py-12">
        <div className="mx-auto grid w-[350px] gap-6">
          <div className="grid gap-2 text-center">
            <h1 className="text-3xl font-bold text-gray-700">Login</h1>
            <p className="text-balance text-muted-foreground">
              Entre com seu email e senha para acessar sua conta.
            </p>
          </div>
          <div className="grid gap-4">
            <div className="grid gap-2">
              <Label htmlFor="email" className="flex-1 text-left text-gray-700">
                Email
              </Label>
              <Input
                id="email"
                type="email"
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
                <Link
                  href="/forgot-password"
                  className="ml-auto inline-block text-sm underline text-gray-700"
                >
                  Esqueceu sua Senha?
                </Link>
              </div>
              <Input
                id="password"
                type="password"
                required
                className="w-full border-gray-700"
              />
            </div>
            <Button type="submit" className="w-full">
              Login
            </Button>
          </div>
          <div className="mt-4 text-center text-sm text-gray-700">
            Não tem uma conta?{" "}
            <Link href="/signup" className="underline text-gray-600">
              Faça seu Cadastro
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
