package cn.ripple.entity.face;

import cn.ripple.entity.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "face_info")
@TableName("face_info")
public class FaceInfo extends BaseEntity {
	@ApiModelProperty(value = "人脸分组id")
	private Long groupId;
	@ApiModelProperty(value = "左位置")
	private int rectLeft;
	@ApiModelProperty(value = "上位置")
	private int rectTop;
	@ApiModelProperty(value = "右位置")
	private int rectRight;
	@ApiModelProperty(value = "下位置")
	private int rectBottom;
	@ApiModelProperty(value = "横滚角")
	private float face3dRoll;
	@ApiModelProperty(value = "偏航角")
	private float face3dYaw;
	@ApiModelProperty(value = "俯仰角")
	private float face3dPitch;
	@ApiModelProperty(value = "0: 正常，其他数值：检测结果不可信")
	private int face3dStatus;
	@ApiModelProperty(value = "逆时针方向")
	public int faceOrient;
	@ApiModelProperty(value = "年龄")
	private int age;
	@ApiModelProperty(value = "性别")
	private int  gender;
	@ApiModelProperty(value = "人脸token")
	private String token;
	@ApiModelProperty(value = "人脸特征")
	private byte[] faceFeature;
	@ApiModelProperty(value = "相似度")
	@TableField(exist=false)
	private Float similarValue;
}
