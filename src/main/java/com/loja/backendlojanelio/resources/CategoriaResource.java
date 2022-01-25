package com.loja.backendlojanelio.resources;

import com.loja.backendlojanelio.domain.Categoria;
import com.loja.backendlojanelio.exceptions.DataIntegrityException;
import com.loja.backendlojanelio.resources.dto.CategoriaDTO;
import com.loja.backendlojanelio.resources.dto.CategoriaInputDTO;
import com.loja.backendlojanelio.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Validated
public class CategoriaResource {

    private final CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findByid(@PathVariable Integer id){
        Categoria categoria = service.findById(id);
        return  ResponseEntity.ok().body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        return  ResponseEntity.ok()
                .body(service.findAll().stream().map(CategoriaDTO::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findAllPage(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                          @RequestParam(value = "linesPerPage", defaultValue = "20", required = false) Integer linesPerPage,
                                                          @RequestParam(value = "orderBy", defaultValue = "nome", required = false) String orderBy,
                                                          @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        return  ResponseEntity.ok()
                .body(service.findPage(page, linesPerPage, orderBy, direction).map(CategoriaDTO::new));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid final CategoriaInputDTO obj){
        Categoria objEntity = service.insert(obj.toCategoriaEntity());
        URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(objEntity.getId())
                        .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @Valid @RequestBody CategoriaInputDTO obj){
        obj.setId(id);
        service.update(obj.toCategoriaEntity());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try {
            service.delete(id);
        }catch (DataIntegrityViolationException e ){
            throw new DataIntegrityException("Não é possível excluir uma categoria que tenha produtos.");
        }
        return ResponseEntity.noContent().build();
    }
}
