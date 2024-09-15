//package br.com.fiap.mspedidos.suppliers;
//import br.com.fiap.mspedidos.models.ProdutoVendido;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//import java.util.function.Supplier;
//
//@Component
//@Slf4j
//public class CreatedPedidoSupplier implements Supplier<PedidoCreated> {
//    @Override
//    public PedidoCreated get() {
//        log.info("Pedido criado");
//        List<ProdutoVendido> list = Arrays.asList(
//                new ProdutoVendido(UUID.fromString("2b65dfab-83d7-41b2-91a4-0163b6823e2e"), 10L),
//                new ProdutoVendido(UUID.fromString("2b65dfab-83d7-41b2-91a4-0163b6823e2e"), 150L)
//                );
//
//        return new PedidoCreated("2b65dfab-83d7-41b2-91a4-0163b6823e2e", "2b65dfab-83d7-41b2-91a4-0163b6823e2e", list);
//    }
//}
