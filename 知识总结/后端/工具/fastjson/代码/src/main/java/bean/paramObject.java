package bean;

import com.alibaba.fastjson.JSON;
import pojo.Person;

import java.util.List;
import java.util.Map;

/**
 * json字符串转java对象
 */
public class paramObject {
    public static void main(String[] args) {
        //对象
        String s1= "{\"age\":23,\"name\":\"zhangsan\"}";
        //map
        String s2 = "{\"name\":\"zhansan\",\"age\":\"23\"}";
        //list
        String s3 = "[{\"age\":24,\"name\":\"lisi1\"},{\"age\":25,\"name\":\"lisi2\"},{\"age\":26,\"name\":\"lisi3\"},{\"age\":27,\"name\":\"lisi4\"}]";
        Person person = JSON.parseObject(s1, Person.class);
        System.out.println(person);

        Map<String,String> map = JSON.parseObject(s2, Map.class);
        System.out.println(map.toString());

        List<Person> list = JSON.parseObject(s3, List.class);
        System.out.println(list.toString());
    }
}
