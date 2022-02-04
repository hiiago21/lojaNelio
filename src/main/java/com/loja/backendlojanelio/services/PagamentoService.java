package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.Pagamento;
import com.loja.backendlojanelio.repositories.PagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class PagamentoService {

    private final PagamentoRepository repository;

    @Transactional
    public Pagamento insert(Pagamento pagamento){
        return repository.save(pagamento);
    }
}
