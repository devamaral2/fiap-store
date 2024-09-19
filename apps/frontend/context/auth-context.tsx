"use client";
import axios from "axios";
import { usePathname, useRouter } from "next/navigation";
import { parseCookies, setCookie } from "nookies";
import { createContext, useContext, useEffect, useMemo } from "react";

const AuthContext = createContext({});

interface AuthContext {
  register: (event: any) => void;
  login: (event: any) => void;
}

const AuthProvider = ({ children }: any) => {
  const router = useRouter();
  const pathname = usePathname();
  async function register(event: any) {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    if (formData.get("password") !== formData.get("password2")) {
      return;
    }
    await axios.post("http://localhost:8001/auth/register", {
      email: formData.get("email"),
      name: formData.get("name"),
      password: formData.get("password"),
    });
  }

  async function login(event: any) {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);

    const { data } = await axios.post(
      "http://localhost:8001/auth/login",
      {
        email: formData.get("email"),
        password: formData.get("password"),
      },
      {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:3000",
        },
      }
    );
    const TWO_HOURS_IN_SECONDS = 2 * 60 * 60;

    setCookie(null, "token", data.token, {
      maxAge: TWO_HOURS_IN_SECONDS,
      sameSite: "lax",
    });
    router.push("/plp?type=products");
  }

  useEffect(() => {
    if (pathname !== "/" && pathname !== "signup") {
      const cookies = parseCookies();
      if (!cookies.token) {
        router.push("/");
      }
    }
  }, []);

  const memoizedContext = (obj: any) => useMemo(() => obj, [obj]);
  const context: AuthContext = memoizedContext({
    register,
    login,
  });

  const { Provider } = AuthContext;

  return <Provider value={context}>{children}</Provider>;
};

const useAuthContext = () => useContext(AuthContext) as AuthContext;

export { AuthProvider, useAuthContext };
export type { AuthContext };
