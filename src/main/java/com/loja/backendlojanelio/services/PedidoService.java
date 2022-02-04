package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.PagamentoComBoleto;
import com.loja.backendlojanelio.domain.Pedido;
import com.loja.backendlojanelio.domain.Produto;
import com.loja.backendlojanelio.enums.EstadoPagamento;
import com.loja.backendlojanelio.exceptions.ObjectNotFoundException;
import com.loja.backendlojanelio.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final BoletoService boletoService;
    private final PagamentoService pagamentoService;
    private final ProdutoService produtoService;
    private final ItemPedidoService itemPedidoService;

    public Pedido findById(Integer id){
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(String.format("Peido de id: %s, não encontrado", id)));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        Pedido pedidoSalvo = repository.save(obj);

        buildPagameto(pedidoSalvo);
        pagamentoService.insert(pedidoSalvo.getPagamento());

        buildItensPedido(pedidoSalvo);
        itemPedidoService.insertAll(pedidoSalvo.getItens());

        return pedidoSalvo;
    }

    private void buildItensPedido(Pedido pedidoSalvo) {
        pedidoSalvo.getItens().forEach(it ->{
            it.setDesconto(0.0);
            it.setPreco(produtoService.findById(it.getProduto().getId()).getPreco());
            it.setPedido(pedidoSalvo);
        });
    }

    private void buildPagameto(Pedido pedidoSalvo) {
        pedidoSalvo.setInstante(new Date());
        pedidoSalvo.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE.getCod());
        pedidoSalvo.getPagamento().setPedido(pedidoSalvo);
        if(pedidoSalvo.getPagamento() instanceof PagamentoComBoleto){
            boletoService.calcularVencimentoBoleto(pedidoSalvo);
        }
    }
}
