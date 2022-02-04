package com.loja.backendlojanelio.services.validators;

import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.enums.TipoCliente;
import com.loja.backendlojanelio.exceptions.FieldMessage;
import com.loja.backendlojanelio.repositories.ClienteRepository;
import com.loja.backendlojanelio.resources.dto.ClienteInputDTO;
import com.loja.backendlojanelio.resources.dto.ClienteNewDTO;
import com.loja.backendlojanelio.services.validators.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteInputDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteInputDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.valueOf(map.get("id"));
        Cliente cliente = repository.findByEmail(objDto.getEmail());

        if(!isNull(cliente) && !cliente.getId().equals(uriId)){
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