package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.exceptions.ObjectNotFoundException;
import com.loja.backendlojanelio.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente buscaClientePorId(Integer id){
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(String.format("Cliente de id: %s, não encontrado", id)));
    }

}
