"use client";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import WrapperWithTitle from "@/components/WrapperWithTitle/WrapperWithTitle";
import {
  ColumnDef,
  ColumnFiltersState,
  SortingState,
  VisibilityState,
  flexRender,
  getCoreRowModel,
  getFilteredRowModel,
  getPaginationRowModel,
  getSortedRowModel,
  useReactTable,
} from "@tanstack/react-table";
import axios from "axios";
import { useRouter } from "next/navigation";
import { parseCookies } from "nookies";
import * as React from "react";

export type Payment = {
  id: string;
  amount: number;
  status: "pending" | "processing" | "success" | "failed";
  email: string;
};

export const columns: ColumnDef<Payment>[] = [
  {
    accessorKey: "name",
    header: "Nome",
    cell: ({ row }) => <div className="capitalize">{row.getValue("name")}</div>,
  },
  {
    accessorKey: "description",
    header: "Descrição",
    cell: ({ row }) => (
      <div className="capitalize">{row.getValue("description")}</div>
    ),
  },
  {
    accessorKey: "quantity",
    header: "Quantidade",
    cell: ({ row }) => (
      <div className="capitalize">{row.getValue("quantity")}</div>
    ),
  },
  {
    accessorKey: "category",
    header: "category",
    cell: ({ row }) => (
      <div className="capitalize">{row.getValue("category")}</div>
    ),
  },
  {
    accessorKey: "price",
    header: () => <div className="text-right">Preço</div>,
    cell: ({ row }) => {
      const price = parseFloat(row.getValue("price"));

      const formatted = new Intl.NumberFormat("pt-BR", {
        style: "currency",
        currency: "BRL",
      }).format(price);

      return <div className="text-right font-medium">{formatted}</div>;
    },
  },
  {
    accessorKey: "icon",
    header: () => <div className="text-right">Editar</div>,
    cell: ({ row }) => {
      const [updateModel, setUpdateModel] = React.useState(false);
      async function onSubmit(event: any) {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const cookies = parseCookies();
        const product = {
          price: formData.get("price"),
          quantity: formData.get("quantity"),
          description: formData.get("description"),
        };
        await axios.patch(
          `http://localhost:8001/products/${row.original.id}`,
          product,
          {
            headers: {
              Authorization: `Bearer ${cookies.token}`,
            },
          }
        );
        setUpdateModel(false);
      }

      return (
        <div>
          {updateModel && (
            <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
              <div className="bg-white w-2/5 h-2/5 p-8 rounded-lg shadow-lg relative">
                <h2 className="text-2xl font-bold mb-6">
                  Editar um novo produto
                </h2>
                <form className="space-y-4" onSubmit={onSubmit}>
                  <div>
                    <Label className="block text-sm font-medium text-gray-700">
                      Preço
                    </Label>
                    <Input
                      type="number"
                      name="price"
                      className="w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200"
                    />
                  </div>

                  <div>
                    <Label className="block text-sm font-medium text-gray-700">
                      Quantidade
                    </Label>
                    <Input
                      type="number"
                      name="quantity"
                      className="w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200"
                    />
                  </div>

                  <div>
                    <Label className="block text-sm font-medium text-gray-700">
                      Descrição
                    </Label>
                    <Input
                      name="description"
                      className="w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200"
                    ></Input>
                  </div>

                  <div className="flex justify-end">
                    <Button
                      type="submit"
                      className="bg-indigo-600 text-white px-4 py-2 rounded-md"
                    >
                      Editar item
                    </Button>
                  </div>
                </form>
              </div>
            </div>
          )}
          <Button
            type="button"
            className="bg-indigo-600 text-white px-4 py-2 rounded-md"
            onClick={() => setUpdateModel((p) => !p)}
          >
            Editar
          </Button>
        </div>
      );
    },
  },
];

export function DataTable() {
  const [sorting, setSorting] = React.useState<SortingState>([]);
  const [columnFilters, setColumnFilters] = React.useState<ColumnFiltersState>(
    []
  );
  const [columnVisibility, setColumnVisibility] =
    React.useState<VisibilityState>({});
  const [rowSelection, setRowSelection] = React.useState({});
  const [data, setData] = React.useState([]);
  const [createModel, setCreateModel] = React.useState(false);
  const [category, setCategory] = React.useState("products");

  const fetchProducts = async () => {
    const { data } = await axios.get("http://localhost:8001/products/list");
    setData(data);
  };

  React.useEffect(() => {
    fetchProducts();
  }, []);

  const onSubmit = async (event: any) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const cookies = parseCookies();
    const product = {
      name: formData.get("name"),
      imageUrl: formData.get("imageUrl"),
      price: formData.get("price"),
      quantity: formData.get("quantity"),
      description: formData.get("description"),
      category,
    };
    await axios.post("http://localhost:8001/products/add-product", product, {
      headers: {
        Authorization: `Bearer ${cookies.token}`,
      },
    });
    await fetchProducts();
    setCreateModel(false);
  };

  const table = useReactTable({
    data,
    columns,
    onSortingChange: setSorting,
    onColumnFiltersChange: setColumnFilters,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
    getSortedRowModel: getSortedRowModel(),
    getFilteredRowModel: getFilteredRowModel(),
    onColumnVisibilityChange: setColumnVisibility,
    onRowSelectionChange: setRowSelection,
    state: {
      sorting,
      columnFilters,
      columnVisibility,
      rowSelection,
    },
  });

  if (data.length === 0) {
    return <p>Loading...</p>;
  }

  return (
    <div className="w-full">
      <div className="rounded-md border, m-2">
        <div className="w-ful flex items-center justify-center">
          {createModel && (
            <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
              <div className="bg-white w-2/5 h-3/5 p-8 rounded-lg shadow-lg relative">
                <h2 className="text-2xl font-bold mb-6">Criar Novo Produto</h2>
                <form className="space-y-4" onSubmit={onSubmit}>
                  <div>
                    <Label className="block text-sm font-medium text-gray-700">
                      Nome
                    </Label>
                    <Input
                      type="text"
                      name="name"
                      className="w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200"
                    />
                  </div>

                  <div>
                    <Label className="block text-sm font-medium text-gray-700">
                      URL da Imagem
                    </Label>
                    <Input
                      type="text"
                      name="imageUrl"
                      className="w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200"
                    />
                  </div>

                  <div>
                    <Label className="block text-sm font-medium text-gray-700">
                      Preço
                    </Label>
                    <Input
                      type="number"
                      name="price"
                      className="w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200"
                    />
                  </div>

                  <div>
                    <Label className="block text-sm font-medium text-gray-700">
                      Quantidade
                    </Label>
                    <Input
                      type="number"
                      name="quantity"
                      className="w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200"
                    />
                  </div>

                  <div>
                    <Label className="block text-sm font-medium text-gray-700">
                      Descrição
                    </Label>
                    <Input
                      name="description"
                      className="w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200"
                    ></Input>
                  </div>

                  <div>
                    <Label htmlFor="category">Categoria</Label>
                    <Select
                      value={category}
                      onValueChange={(value) => {
                        setCategory(value);
                      }}
                    >
                      <SelectTrigger className="w-full text-gray-500">
                        <SelectValue
                          placeholder="Categoria do item"
                          className="w-full"
                        />
                      </SelectTrigger>
                      <SelectContent>
                        <SelectGroup>
                          <SelectLabel>Categoria do item</SelectLabel>
                          {["products", "services"].map((item) => (
                            <SelectItem key={item} value={item}>
                              {item}
                            </SelectItem>
                          ))}
                        </SelectGroup>
                      </SelectContent>
                    </Select>
                  </div>

                  <div className="flex justify-end">
                    <Button
                      type="submit"
                      className="bg-indigo-600 text-white px-4 py-2 rounded-md"
                    >
                      Criar Item
                    </Button>
                  </div>
                </form>
              </div>
            </div>
          )}
          <Button
            variant="default"
            className="m-5"
            size="sm"
            onClick={() => setCreateModel((p) => !p)}
          >
            Criar
          </Button>
        </div>
        <Table>
          <TableHeader>
            {table.getHeaderGroups().map((headerGroup) => (
              <TableRow key={headerGroup.id}>
                {headerGroup.headers.map((header) => {
                  return (
                    <TableHead key={header.id}>
                      {header.isPlaceholder
                        ? null
                        : flexRender(
                            header.column.columnDef.header,
                            header.getContext()
                          )}
                    </TableHead>
                  );
                })}
              </TableRow>
            ))}
          </TableHeader>
          <TableBody>
            {table.getRowModel().rows?.length ? (
              table.getRowModel().rows.map((row) => (
                <TableRow
                  key={row.id}
                  data-state={row.getIsSelected() && "selected"}
                  className="text-gray-700"
                >
                  {row.getVisibleCells().map((cell) => (
                    <TableCell key={cell.id}>
                      {flexRender(
                        cell.column.columnDef.cell,
                        cell.getContext()
                      )}
                    </TableCell>
                  ))}
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell
                  colSpan={columns.length}
                  className="h-24 text-center text-gray-500"
                >
                  No results.
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </div>
      <div className="flex items-center justify-end space-x-2 py-4">
        <div className="flex-1 text-sm text-muted-foreground text-gray-700">
          {table.getFilteredSelectedRowModel().rows.length} of{" "}
          {table.getFilteredRowModel().rows.length} row(s) selected.
        </div>
        <div className="space-x-2 text-gray-700">
          <Button
            variant="default"
            size="sm"
            onClick={() => table.previousPage()}
            disabled={!table.getCanPreviousPage()}
          >
            Previous
          </Button>
          <Button
            variant="default"
            size="sm"
            onClick={() => table.nextPage()}
            disabled={!table.getCanNextPage()}
          >
            Next
          </Button>
        </div>
      </div>
    </div>
  );
}

export default function Page() {
  const router = useRouter();
  async function featchRole() {
    const cookies = parseCookies();
    const { data } = await axios.get("http://localhost:8001/users/role", {
      headers: {
        Authorization: `Bearer ${cookies.token}`,
      },
    });
    if (data.role !== "ADMIN") {
      router.push("/");
    }
  }
  React.useEffect(() => {
    featchRole();
  }, []);
  return (
    <div className="items-center justify-items-center min-h-[40dvw] p-8 pb-20 gap-16 sm:p-20 bg-white">
      <WrapperWithTitle title="Produtos" subtitle="Area de Administração">
        <DataTable />
      </WrapperWithTitle>
    </div>
  );
}
