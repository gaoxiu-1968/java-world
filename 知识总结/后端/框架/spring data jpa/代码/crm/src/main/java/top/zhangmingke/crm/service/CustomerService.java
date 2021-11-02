package top.zhangmingke.crm.service;

import top.zhangmingke.crm.entity.CustomerEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author Zhang MingKe
 * @Date 2021/11/2
 */
public interface CustomerService {
    /**
     * 插入客户
     */
    void InsertCustomer(CustomerEntity customerEntity);

    /**
     * 通过客户id查询客户
     * @return
     */
    CustomerEntity queryCustomerById(Integer custId);

    /**
     * 更新客户
     */
    void updateCustomer(CustomerEntity customerEntity);

    /**
     * 通过客户id删除客户
     * @param custId
     */
    void deleteCustoemr(Integer custId);

    /**
     * 查询所有客户
     * @return
     */
    List<CustomerEntity> queryAllCustomer();

    /**
     * 通过客户名称查询客户
     * @return
     */
    List<CustomerEntity> queryCustomerByCustName();



}
