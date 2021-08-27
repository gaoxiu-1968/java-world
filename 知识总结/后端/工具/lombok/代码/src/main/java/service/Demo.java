package service;

import lombok.Builder;
import pojo.Person;

public class Demo {
    public static void main(String[] args) {
        //@Data
        Person person = Person.builder().name("zhangsan").age(23).build();
        System.out.println(person.toString());
    }
}
