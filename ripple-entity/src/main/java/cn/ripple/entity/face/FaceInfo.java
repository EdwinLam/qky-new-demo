package cn.ripple.entity.face;

import cn.ripple.entity.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


@Data
@Entity
@Table(name = "face_info")
@TableName("face_info")
public class FaceInfo extends BaseEntity {
	@Transient
	@TableField(exist=false)
	private Rect rect;
	@Transient
	@TableField(exist=false)
	private Face3DAngle face3DAngle;

	// 角度，逆时针方向
	@Transient
	@TableField(exist=false)
	public int faceOrient;

	/**
	 * 年龄
	 */
	@Transient
	@TableField(exist=false)
	private int age;
	/**
	 * 性别
	 */
	@Transient
	@TableField(exist=false)
	private int  gender;

	@ApiModelProperty(value = "人脸token")
	private String token;

	@ApiModelProperty(value = "人脸特征")
	private byte[] faceFeature;

}
