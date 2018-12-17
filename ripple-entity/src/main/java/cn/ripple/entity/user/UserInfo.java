package cn.ripple.entity.user;


import cn.ripple.entity.BaseEntity;
import cn.ripple.entity.face.FaceInfo;
import cn.ripple.entity.face.Rect;
import cn.ripple.enums.DataStatusEnum;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name = "user_info")
@TableName("user_info")
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @Column(unique = true, nullable = false)
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "0女 1男 2保密")
    private Integer sex;

    @ApiModelProperty(value = "状态 默认0正常 -1拉黑")
    private Integer status = DataStatusEnum.ENABLED.getValue();

    @ApiModelProperty(value = "人脸特征")
    private byte[] faceFeature;

    @ApiModelProperty(value = "人脸特征token")
    private String faceToken;

    @Transient
    @ApiModelProperty(value = "相似度")
    @TableField(exist=false)
    private Long similarValue;

    @Transient
    @ApiModelProperty(value = "相似度")
    @TableField(exist=false)
    private FaceInfo faceInfo;

}
