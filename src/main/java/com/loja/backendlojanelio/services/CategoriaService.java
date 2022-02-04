package com.loja.backendlojanelio.services;

import com.loja.backendlojanelio.domain.Categoria;
import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.exceptions.ObjectNotFoundException;
import com.loja.backendlojanelio.repositories.CategoriaRepository;
import com.loja.backendlojanelio.resources.dto.CategoriaInputDTO;
import com.loja.backendlojanelio.resources.dto.ClienteInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria findById(Integer id){
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(String.format("Objeto de id: %s, não encontrado", id)));
    }

    public Categoria insert(Categoria obj) {
        return repository.save(obj);
    }

    public Categoria update(CategoriaInputDTO obj) {
        Categoria categoria = findById(obj.getId());
        updateData(categoria, obj);
        return repository.save(categoria);
    }

    private void updateData(Categoria categoria, CategoriaInputDTO obj) {
        categoria.setNome(obj.getNome());
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public List<Categoria> findAllById(List<Integer> ids) {
        return repository.findAllById(ids);
    }
}
