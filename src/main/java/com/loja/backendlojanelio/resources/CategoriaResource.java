package com.loja.backendlojanelio.resources;

import com.loja.backendlojanelio.domain.Categoria;
import com.loja.backendlojanelio.services.CategoriaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoriaResource {

    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findByid(@PathVariable Integer id){
        Categoria categoria = service.buscaCategoriaPorId(id);
        return  ResponseEntity.ok().body(categoria);
    }
}
