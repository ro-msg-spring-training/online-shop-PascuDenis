package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.SupplierDTO;
import ro.msg.learning.shop.exception.SupplierNotFoundException;
import ro.msg.learning.shop.service.SupplierService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/supplier")
public class SupplierController implements IController<SupplierDTO, Integer> {
    private final SupplierService supplierService;

    @Override
    @GetMapping("/supplier/{id}")
    public SupplierDTO getOne(@PathVariable Integer id) {
        try {
            return supplierService.findOne(id);
        } catch (SupplierNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @GetMapping("/products")
    public List<SupplierDTO> getAll() {
            return supplierService.findAll();
    }

    @Override
    public SupplierDTO save(SupplierDTO entity) {
        return null;
    }

    @Override
    public SupplierDTO update(SupplierDTO entity) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}
