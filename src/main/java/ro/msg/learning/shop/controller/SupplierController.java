package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.SupplierDTO;
import ro.msg.learning.shop.exception.SupplierNotFoundException;
import ro.msg.learning.shop.service.SupplierService;

import java.util.List;

//@RestController
@AllArgsConstructor
//@RequestMapping("/supplier")
public class SupplierController implements IController<SupplierDTO, Integer> {

    @Autowired
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
    @GetMapping("/supplier")
    public List<SupplierDTO> getAll() {
            return supplierService.findAll();
    }

    @Override
    @GetMapping("/supplier")
    public SupplierDTO save(SupplierDTO entity) {
        return null;
    }

    @Override
    @PutMapping("/supplier/{id}")
    public SupplierDTO update(SupplierDTO entity) {
        return null;
    }

    @Override
    @DeleteMapping("/supplier/{id}")
    public void remove(Integer id) {

    }
}
