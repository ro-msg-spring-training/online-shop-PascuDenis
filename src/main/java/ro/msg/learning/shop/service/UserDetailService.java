package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService {
    private  final ICustomerRepository customerRepository;

    @Autowired
    public UserDetailService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public List<List<String>> getAllUsernamesAndPasswords(){
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        List<List<String>> listToReturn = new ArrayList<>();
        List<String> tempList;
        for (Customer c : customerList){
            tempList = new ArrayList<>();
            tempList.add(c.getUsername());
            tempList.add(c.getPassword());
            listToReturn.add(tempList);
        }
        return listToReturn;
    }
}
