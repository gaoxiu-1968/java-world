package top.zhangmingke.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhangmingke.cache.dao.PersonDao;
import top.zhangmingke.cache.entity.Person;

@RestController
public class DemoController {
    @Autowired
    private PersonDao personDao;

    @GetMapping("/getPerson")
    public Person getPerson(){
        return personDao.getPerson(1);
    }
}
