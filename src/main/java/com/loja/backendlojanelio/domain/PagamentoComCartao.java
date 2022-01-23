package com.loja.backendlojanelio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PagamentoComCartao extends Pagamento{

    private Integer numeroParcelas;

    @Builder
    public PagamentoComCartao(Integer id, Integer estadoPagamento, Pedido pedido, Integer numeroParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }
}
