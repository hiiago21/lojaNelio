package com.loja.backendlojanelio.resources;

import com.loja.backendlojanelio.domain.Categoria;
import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.exceptions.DataIntegrityException;
import com.loja.backendlojanelio.resources.dto.*;
import com.loja.backendlojanelio.services.ClienteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findByid(@PathVariable Integer id){
        Cliente cliente = service.findById(id);
        return  ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return  ResponseEntity.ok()
                .body(service.findAll().stream()
                        .map(ClienteDTO::toDTO)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findAllPage(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                        @RequestParam(value = "linesPerPage", defaultValue = "20", required = false) Integer linesPerPage,
                                                        @RequestParam(value = "orderBy", defaultValue = "nome", required = false) String orderBy,
                                                        @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        return  ResponseEntity.ok()
                .body(service.findPage(page, linesPerPage, orderBy, direction)
                        .map(ClienteDTO::new));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid final ClienteNewDTO obj){
        Cliente objEntity = service.insert(obj.toClienteEntity());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(objEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @Valid @RequestBody ClienteInputDTO obj){
        Cliente cliente = service.findById(id);
        service.update(updateData(cliente, obj));
        return ResponseEntity.noContent().build();
    }

    private Cliente updateData(Cliente cliente, ClienteInputDTO obj) {
        cliente.setEmail(obj.getEmail());
        cliente.setNome(obj.getNome());
        return cliente;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try {
            service.delete(id);
        }catch (DataIntegrityViolationException e ){
            throw new DataIntegrityException("Não é possível excluir uma categoria que tenha produtos.");
        }
        return ResponseEntity.noContent().build();
    }
}
