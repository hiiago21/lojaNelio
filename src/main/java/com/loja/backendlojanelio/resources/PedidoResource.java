package com.loja.backendlojanelio.resources;

import com.loja.backendlojanelio.domain.Pedido;
import com.loja.backendlojanelio.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoResource {

    private final PedidoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findByid(@PathVariable Integer id){
        Pedido pedido = service.buscaPedidoPorId(id);
        return  ResponseEntity.ok().body(pedido);
    }
}
