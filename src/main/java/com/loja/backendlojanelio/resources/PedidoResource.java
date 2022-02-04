package com.loja.backendlojanelio.resources;

import com.loja.backendlojanelio.domain.Categoria;
import com.loja.backendlojanelio.domain.Pedido;
import com.loja.backendlojanelio.resources.dto.CategoriaInputDTO;
import com.loja.backendlojanelio.services.PedidoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoResource {

    private final PedidoService service;

    @GetMapping("/{id}")
    @ApiOperation("Busca um pedido por id")
    public ResponseEntity<Pedido> findByid(@PathVariable Integer id){
        Pedido pedido = service.findById(id);
        return  ResponseEntity.ok().body(pedido);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid final Pedido obj){
        Pedido objEntity = service.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(objEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
