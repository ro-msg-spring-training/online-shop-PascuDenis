package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.CustomerDTO;
import ro.msg.learning.shop.exception.CustomerNotFoundException;
import ro.msg.learning.shop.mapping.CustomerMapper;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.ICustomerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements IService<CustomerDTO, Integer> {
    private final ICustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerDTO findOne(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
        return new CustomerMapper().convertToDto(customer);
    }

    @Override
    @Transactional
    public List<CustomerDTO> findAll() {
        List customerList = (List) customerRepository.findAll();
        if (customerList.isEmpty()){
            throw new CustomerNotFoundException("Unable to find any customers");
        }
        return customerList;
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO entity) {
        return null;
    }

    @Override
    @Transactional
    public void remove(Integer id) {

    }

    @Override
    @Transactional
    public CustomerDTO update(CustomerDTO entity) {
        return null;
    }
    }
