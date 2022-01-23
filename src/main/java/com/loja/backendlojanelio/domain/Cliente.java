package com.loja.backendlojanelio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente;
    @OneToMany(mappedBy = "cliente")
    @NonNull
    private final List<Endereco> enderecos = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    @NonNull
    private final Set<String> telefones = new HashSet<>();
    @OneToMany(mappedBy = "cliente")
    @NonNull
    private final List<Pedido> pedidos = new ArrayList<>();
}
