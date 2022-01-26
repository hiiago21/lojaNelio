package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.exceptions.ObjectNotFoundException;
import com.loja.backendlojanelio.repositories.ClienteRepository;
import com.loja.backendlojanelio.resources.dto.ClienteInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final EnderecoService enderecoService;

    public Cliente findById(Integer id){
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(String.format("Cliente de id: %s, não encontrado", id)));
    }

    public Collection<Cliente> findAll() {
        return repository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente update(ClienteInputDTO obj, Integer id) {
        Cliente cliente = findById(id);
        updateData(cliente, obj);
        return repository.save(cliente);
    }

    private void updateData(Cliente cliente, ClienteInputDTO obj) {
        cliente.setEmail(obj.getEmail());
        cliente.setNome(obj.getNome());
    }

    public void delete(Integer id) {
        repository.findById(id);
        repository.deleteById(id);
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        Cliente clienteSave = repository.save(cliente);
        enderecoService.insert(clienteSave.getEnderecos());
        return clienteSave;
    }
}
