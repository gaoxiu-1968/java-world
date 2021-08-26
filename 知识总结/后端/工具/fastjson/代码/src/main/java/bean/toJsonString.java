package bean;

import com.alibaba.fastjson.JSON;
import pojo.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * java对象转json字符串
 */
public class toJsonString {
    public static void main(String[] args) {
        //java对象 -》 json字符串

        //java对象转json字符串
        Person person = new Person("zhangsan", 23);
        String s = JSON.toJSONString(person);
        System.out.println(s);

        //Map转json字符串
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","zhansan");
        map.put("age","23");
        String s1 = JSON.toJSONString(map);
        System.out.println(s1);

        //List转json字符串
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("lisi1",24));
        list.add(new Person("lisi2",25));
        list.add(new Person("lisi3",26));
        list.add(new Person("lisi4",27));
        String s2 = JSON.toJSONString(list);
        System.out.println(s2);
    }
}
