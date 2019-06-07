package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.SupplierDTO;
import ro.msg.learning.shop.exception.SupplierNotFoundException;
import ro.msg.learning.shop.mapping.SupplierMapper;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ISupplierRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class SupplierService implements IService<SupplierDTO, Integer> {
    private final ISupplierRepository supplierRepository;

    @Override
    @Transactional
    public SupplierDTO findOne(Integer id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
        return new SupplierMapper().convertToDto(supplier);

    }

    @Override
    @Transactional
    public List<SupplierDTO> findAll() {

        SupplierMapper mapper = new SupplierMapper();
        return StreamSupport.stream(supplierRepository.findAll().spliterator(), false).map(mapper::convertToDto).
                collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SupplierDTO save(SupplierDTO entity) {
        Supplier supplier = supplierRepository.save(new SupplierMapper().convertToEntity(entity));
        return new SupplierMapper().convertToDto(supplier);
    }

    @Override
    @Transactional
    public SupplierDTO update(SupplierDTO entity) {
        Supplier supplierToUpdate = supplierRepository.findById(entity.getId()).orElseThrow(() -> new SupplierNotFoundException(entity.getId()));

        if (entity.getName() != null && !entity.getName().equals(supplierToUpdate.getName())) {
            supplierToUpdate.setName(entity.getName());
        }


        Supplier updatedSupplier = supplierRepository.save(supplierToUpdate);
        return new SupplierMapper().convertToDto(updatedSupplier);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        supplierRepository.deleteById(id);
    }
}
