import { FaGithub, FaLinkedin, FaSitemap } from "react-icons/fa";

const NavMocks = [
  { name: "Home", href: "/" },
  { name: "Produtos", href: "/plp?type=products" },
  { name: "Serviços", href: "/plp?type=services" },
  { name: "Cadastro", href: "/admin" },
];

const SocialIconMocks = [
  {
    name: "Instagram",
    href: "https://rafaelpermec.github.io",
    icon: FaSitemap,
  },
  { name: "GitHub", href: "https://github.com/rafaelPermec", icon: FaGithub },
  {
    name: "LinkedIn",
    href: "https://www.linkedin.com/in/rafael-permec",
    icon: FaLinkedin,
  },
];

export { NavMocks, SocialIconMocks };
