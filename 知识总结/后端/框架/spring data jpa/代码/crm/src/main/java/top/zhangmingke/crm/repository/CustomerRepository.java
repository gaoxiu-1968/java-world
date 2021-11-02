package top.zhangmingke.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.zhangmingke.crm.entity.CustomerEntity;

/**
 * @author Zhang MingKe
 * @Date 2021/11/2
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer>, JpaSpecificationExecutor<CustomerEntity> {
}
