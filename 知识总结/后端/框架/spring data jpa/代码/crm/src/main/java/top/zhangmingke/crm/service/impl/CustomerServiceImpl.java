package top.zhangmingke.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangmingke.crm.entity.CustomerEntity;
import top.zhangmingke.crm.repository.CustomerRepository;
import top.zhangmingke.crm.service.CustomerService;

import java.util.List;
import java.util.Optional;

/**
 * @author Zhang MingKe
 * @Date 2021/11/2
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void InsertCustomer(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity queryCustomerById(Integer custId) {
        CustomerEntity byId = customerRepository.findById(custId).get();
        return byId;
    }

    @Override
    public void updateCustomer(CustomerEntity customerEntity) {
        Optional<CustomerEntity> byId = customerRepository.findById(customerEntity.getCustId());
    }

    @Override
    public void deleteCustoemr(Integer custId) {
        customerRepository.deleteById(custId);
    }

    @Override
    public List<CustomerEntity> queryAllCustomer() {
        return null;
    }

    @Override
    public List<CustomerEntity> queryCustomerByCustName() {
        return null;
    }
}
