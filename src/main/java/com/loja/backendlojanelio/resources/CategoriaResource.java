package com.loja.backendlojanelio.resources;

import com.loja.backendlojanelio.domain.Categoria;
import com.loja.backendlojanelio.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaResource {

    private final CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findByid(@PathVariable Integer id){
        Categoria categoria = service.buscaCategoriaPorId(id);
        return  ResponseEntity.ok().body(categoria);
    }
}
