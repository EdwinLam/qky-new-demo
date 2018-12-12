package cn.ripple.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Edwin
 * @Date: 2018/12/12 11:21
 * @Description:
 */
@Data
public class Table implements Serializable {
    @ApiModelProperty(value = "表名称")
    private String tableName;

    @ApiModelProperty(value = "表注释")
    private String tableComment;
}
