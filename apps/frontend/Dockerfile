FROM oven/bun:latest

WORKDIR /app/next-app

COPY package.json ./
COPY bun.lockb ./

RUN bun install

COPY . .

RUN bun run build

CMD bun start