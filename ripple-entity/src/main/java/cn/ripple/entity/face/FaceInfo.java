package cn.ripple.entity.face;
import lombok.Data;

import java.io.Serializable;

/**
 * 单人脸
 *
 * @author Jastar·Wang
 * @email jastar_wang@163.com
 * @date 2018-12-05
 * @since 2.0
 */
@Data
public class FaceInfo implements Serializable {

	// 位置
	public int left;
	public int top;
	public int right;
	public int bottom;

	// 角度，逆时针方向
	public int faceOrient;

	/**
	 * 年龄
	 */
	private int age;

	/**
	 * 性别
	 */
	private int  gender;

}
