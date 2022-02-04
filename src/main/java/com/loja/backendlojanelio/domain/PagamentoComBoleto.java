package com.loja.backendlojanelio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    @Builder
    public PagamentoComBoleto(Integer id, Integer estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }


}
