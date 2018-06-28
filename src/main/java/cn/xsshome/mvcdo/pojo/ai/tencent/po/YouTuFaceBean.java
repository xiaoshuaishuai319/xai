package cn.xsshome.mvcdo.pojo.ai.tencent.po;

import java.util.List;
/**
 * 
 * @author 小帅丶
 * @date 2018年6月4日
 * <p>Description: 优图人脸bean 部分字段</p>
 */
public class YouTuFaceBean {
	private String session_id;
    private int image_height;
    private int image_width;
    private List<Face> face;
    private int errorcode;
    private String errormsg;
    
    public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public int getImage_height() {
		return image_height;
	}

	public void setImage_height(int image_height) {
		this.image_height = image_height;
	}

	public int getImage_width() {
		return image_width;
	}

	public void setImage_width(int image_width) {
		this.image_width = image_width;
	}

	public List<Face> getFace() {
		return face;
	}

	public void setFace(List<Face> face) {
		this.face = face;
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public static class Face {
        private String face_id;
        private int x;
        private int y;
        private int height;
        private int width;
        private int pitch;
        private int roll;
        private int yaw;
        private int age;
        private int gender;
        private boolean glass;
        private int expression;
        private int glasses;
        private int mask;
        private int hat;
        private int beauty;
		public String getFace_id() {
			return face_id;
		}
		public void setFace_id(String face_id) {
			this.face_id = face_id;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getPitch() {
			return pitch;
		}
		public void setPitch(int pitch) {
			this.pitch = pitch;
		}
		public int getRoll() {
			return roll;
		}
		public void setRoll(int roll) {
			this.roll = roll;
		}
		public int getYaw() {
			return yaw;
		}
		public void setYaw(int yaw) {
			this.yaw = yaw;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getGender() {
			return gender;
		}
		public void setGender(int gender) {
			this.gender = gender;
		}
		public boolean isGlass() {
			return glass;
		}
		public void setGlass(boolean glass) {
			this.glass = glass;
		}
		public int getExpression() {
			return expression;
		}
		public void setExpression(int expression) {
			this.expression = expression;
		}
		public int getGlasses() {
			return glasses;
		}
		public void setGlasses(int glasses) {
			this.glasses = glasses;
		}
		public int getMask() {
			return mask;
		}
		public void setMask(int mask) {
			this.mask = mask;
		}
		public int getHat() {
			return hat;
		}
		public void setHat(int hat) {
			this.hat = hat;
		}
		public int getBeauty() {
			return beauty;
		}
		public void setBeauty(int beauty) {
			this.beauty = beauty;
		}
    }
    
}
