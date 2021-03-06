package com.loja.backendlojanelio.services.validators;

import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.enums.TipoCliente;
import com.loja.backendlojanelio.exceptions.FieldMessage;
import com.loja.backendlojanelio.repositories.ClienteRepository;
import com.loja.backendlojanelio.resources.dto.ClienteNewDTO;
import com.loja.backendlojanelio.services.validators.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfCnpj", "Cpf inválido"));
        } else if (objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfCnpj", "Cnpj inválido"));
        }

        Cliente cliente = repository.findByEmail(objDto.getEmail());

        if(!isNull(cliente)){
            list.add(new FieldMessage("email", "email já existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}