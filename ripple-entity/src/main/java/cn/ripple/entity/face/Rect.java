package cn.ripple.entity.face;


import lombok.Data;

import java.io.Serializable;

@Data
public class Rect implements Serializable {
	private int left;
	private int top;
	private int right;
	private int bottom;
}
