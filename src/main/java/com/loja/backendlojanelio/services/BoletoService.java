package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.PagamentoComBoleto;
import com.loja.backendlojanelio.domain.Pedido;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class BoletoService {

    public void calcularVencimentoBoleto(Pedido obj) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(obj.getInstante());
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        ((PagamentoComBoleto) obj.getPagamento()).setDataVencimento(calendar.getTime());
    }
}
