package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    private final ICustomerRepository customerRepository;

//    @Autowired
//    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public List<List<String>> getAllUsernamesAndPasswords() {
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        List<List<String>> listToReturn = new ArrayList<>();
        List<String> tempList;
        for (Customer c : customerList) {
            tempList = new ArrayList<>();
            tempList.add(c.getUsername());
            tempList.add(c.getPassword());
            listToReturn.add(tempList);
        }
        return listToReturn;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = customerRepository.findCustomerAfterUsername(username);
        User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles("USER");
        } else {
            throw new UsernameNotFoundException("UserModel not found.");
        }

        return builder.build();
    }
}
