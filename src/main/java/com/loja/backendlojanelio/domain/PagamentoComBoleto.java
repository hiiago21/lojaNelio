package com.loja.backendlojanelio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PagamentoComBoleto extends Pagamento{

    private Date dataVencimento;
    private Date dataPagamento;

    @Builder
    public PagamentoComBoleto(Integer id, Integer estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
