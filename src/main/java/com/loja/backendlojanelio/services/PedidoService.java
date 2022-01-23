package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.Pedido;
import com.loja.backendlojanelio.exceptions.ObjectNotFoundException;
import com.loja.backendlojanelio.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public Pedido buscaPedidoPorId(Integer id){
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(String.format("Peido de id: %s, não encontrado", id)));
    }

}
