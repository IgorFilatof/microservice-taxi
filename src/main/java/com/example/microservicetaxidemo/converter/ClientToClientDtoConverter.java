package com.example.microservicetaxidemo.converter;

import com.example.microservicetaxidemo.dto.CustomerDto;
import com.example.microservicetaxidemo.model.Client;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientToClientDtoConverter implements Function<Client, CustomerDto> {
    @Override
    public CustomerDto apply(Client client) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(client.getFirstName());
        customerDto.setLastName(client.getLastName());
        customerDto.setPhone(client.getPhone());
        customerDto.setUuid(client.getUuid());
        return customerDto;
    }
}
