package cn.ripple.entity.face;

import lombok.Data;
import java.io.Serializable;


@Data
public class Face3DAngle implements Serializable {
	private float roll;
	private float yaw;
	private float pitch;
	private int status;
}
