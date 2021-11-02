package top.zhangmingke.crm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * @author Zhang MingKe
 * @Date 2021/11/2
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Integer custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_tel")
    private String custTel;
    @Column(name = "cust_info")
    private String custInfo;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
