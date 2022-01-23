package com.loja.backendlojanelio.resources;

import com.loja.backendlojanelio.domain.Categoria;
import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.services.CategoriaService;
import com.loja.backendlojanelio.services.ClienteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@AllArgsConstructor
public class ClienteResource {

    private ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findByid(@PathVariable Integer id){
        Cliente cliente = service.buscaClientePorId(id);
        return  ResponseEntity.ok().body(cliente);
    }
}
