package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.Categoria;
import com.loja.backendlojanelio.domain.Pedido;
import com.loja.backendlojanelio.domain.Produto;
import com.loja.backendlojanelio.exceptions.ObjectNotFoundException;
import com.loja.backendlojanelio.repositories.PedidoRepository;
import com.loja.backendlojanelio.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaService categoriaService;

    public Produto findById(Integer id){
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(String.format("Produto de id: %s, não encontrado", id)));
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        List<Categoria> categorias = categoriaService.findAllById(ids);

        return repository.search(nome, categorias, pageRequest);
    }

}
