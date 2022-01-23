package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.Categoria;
import com.loja.backendlojanelio.exceptions.ObjectNotFoundException;
import com.loja.backendlojanelio.repositories.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository repository;

    public Categoria buscaCategoriaPorId(Integer id){
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(String.format("Objeto de id: %s, não encontrado", id)));
    }

}
