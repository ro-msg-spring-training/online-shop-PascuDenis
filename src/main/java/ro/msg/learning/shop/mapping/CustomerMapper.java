package ro.msg.learning.shop.mapping;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.CustomerDTO;
import ro.msg.learning.shop.model.Customer;

@Component
@AllArgsConstructor
public class CustomerMapper implements Mapper<Customer, CustomerDTO> {
    @Override
     public Customer convertToEntity(CustomerDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Customer.class);
    }

    @Override
    public CustomerDTO convertToDto(Customer entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, CustomerDTO.class);
    }
}
