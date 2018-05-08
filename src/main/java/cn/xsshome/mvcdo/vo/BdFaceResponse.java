package cn.xsshome.mvcdo.vo;

/**
 *
 * @author 小帅丶
 * @create 2018年5月7日
 **/
public class BdFaceResponse extends BdResponse{
    //年龄
    private String age;
    //美丑
    private String beauty;
    //是否带眼睛
    private String glasses;
    //表情
    private String expression;
    //性别
    private String gender;
    //脸型
    private String faceShape;
    //人种
    private String raceType;
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBeauty() {
		return beauty;
	}
	public void setBeauty(String beauty) {
		this.beauty = beauty;
	}
	public String getGlasses() {
		return glasses;
	}
	public void setGlasses(String glasses) {
		this.glasses = glasses;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFaceShape() {
		return faceShape;
	}
	public void setFaceShape(String faceShape) {
		this.faceShape = faceShape;
	}
	public String getRaceType() {
		return raceType;
	}
	public void setRaceType(String raceType) {
		this.raceType = raceType;
	}
}
