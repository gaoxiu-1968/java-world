package top.zhangmingke.cache.dao;

import org.springframework.stereotype.Repository;
import top.zhangmingke.cache.entity.Person;

@Repository
public interface PersonDao {
    Person getPerson(int id);
}
