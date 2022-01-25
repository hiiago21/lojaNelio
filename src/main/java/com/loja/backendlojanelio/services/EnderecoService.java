package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.Endereco;
import com.loja.backendlojanelio.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;

    public List<Endereco> insert(List<Endereco> enderecos){
        return repository.saveAll(enderecos);
    }
}
