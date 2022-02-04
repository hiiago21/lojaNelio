package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.ItemPedido;
import com.loja.backendlojanelio.repositories.ItemPedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemPedidoService {

    private final ItemPedidoRepository repository;

    public List<ItemPedido> insertAll(Collection<ItemPedido> itemPedido){
        return repository.saveAll(itemPedido);
    }
}
