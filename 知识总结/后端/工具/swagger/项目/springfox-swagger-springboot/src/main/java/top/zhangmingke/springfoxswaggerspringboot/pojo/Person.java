package top.zhangmingke.springfoxswaggerspringboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "人员",description = "人员信息")
public class Person {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("年龄")
    private int age;
}
