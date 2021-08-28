package top.zhangmingke.springfoxswaggerspringboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhangmingke.springfoxswaggerspringboot.pojo.Person;

@Api(value = "demo类",tags = "测试类")
@RestController
public class DemoController {

    @ApiOperation("演示方法")
    @GetMapping("/demo")
    public Person demoMethod(){
        return Person.builder().name("zhangsna").age(23).build();
    }

}
