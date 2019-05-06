package ro.msg.learning.shop.mapping;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.SupplierDTO;
import ro.msg.learning.shop.model.Supplier;

@Component
@AllArgsConstructor
public class SupplierMapper extends Mapper<Supplier, SupplierDTO> {
    @Override
    public Supplier convertToEntity(SupplierDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Supplier.class);
    }

    @Override
    public SupplierDTO convertToDto(Supplier entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, SupplierDTO.class);    }
}
