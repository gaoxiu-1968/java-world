package top.zhangmingke.crm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.zhangmingke.crm.entity.CustomerEntity;
import top.zhangmingke.crm.service.CustomerService;

import java.util.Date;

@SpringBootTest
class CrmApplicationTests {
    @Autowired
    private CustomerService customerService;

    /**
     * 新增客户
     */
    @Test
    void InsertCustomer() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustName("zhangmingke");
        customerEntity.setCustTel("18438622759");
        customerEntity.setCustInfo("这是个男人！");
        customerEntity.setCreateTime(new Date());
        customerEntity.setIsDeleted(false);
        customerService.InsertCustomer(customerEntity);
    }

    /**
     * 通过客户id查询客户
     */
    @Test
    void queryCustomerById(){
        CustomerEntity customerEntity = customerService.queryCustomerById(1);
        System.out.println(customerEntity.toString());
    }

    /**
     * 通过客户id删除客户
     */
    @Test
    void deleteCustomerById(){
        customerService.deleteCustoemr(1);
    }

}
