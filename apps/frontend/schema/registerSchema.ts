import { z } from "zod";
export const registerSchema = z.object({
  email: z.string().trim().email({ message: "Email invalido" }),
});
