package com.loja.backendlojanelio.resources;

import com.loja.backendlojanelio.domain.Produto;
import com.loja.backendlojanelio.resources.dto.ProdutoDTO;
import com.loja.backendlojanelio.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.loja.backendlojanelio.resources.utils.URL.decodeParam;
import static com.loja.backendlojanelio.resources.utils.URL.decodeStringIdsForInt;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoResource {

    private final ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findByid(@PathVariable Integer id){
        Produto produto = service.findById(id);
        return  ResponseEntity.ok().body(produto);
    }

    @GetMapping()
    public ResponseEntity<Page<ProdutoDTO>> findAllPage(@RequestParam(value = "nome", defaultValue = "", required = false) String nome,
                                                        @RequestParam(value = "categorias", defaultValue = "", required = false) String categorias,
                                                        @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                        @RequestParam(value = "linesPerPage", defaultValue = "20", required = false) Integer linesPerPage,
                                                        @RequestParam(value = "orderBy", defaultValue = "nome", required = false) String orderBy,
                                                        @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        return  ResponseEntity.ok()
                .body(service.search(decodeParam(nome), decodeStringIdsForInt(categorias), page, linesPerPage, orderBy, direction).map(ProdutoDTO::new));
    }
}
