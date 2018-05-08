package cn.xsshome.mvcdo.pojo.ai.baidu;
/**
 * 百度人脸检测javabean
 * 表对应的javabean
 * @author 小帅丶
 * TableName:ai_bd_face
 */
public class BDFaceDetectDO {
	/**
	 * 主键id
	 */
	private Integer faceId;
	/**
	 * 错误码
	 */
	private String errorCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;
	/**
	 * 日志id
	 */
	private String logId;
	/**
	 * 时间戳
	 */
	private String timestamp;
	/**
	 * 缓存
	 */
	private Integer cached;
	/**
	 * 检测到的图片中的人脸数量
	 */
	private Integer faceNum;
	/**
	 * 人脸图片的唯一标识
	 */
	private String faceToken;
	/**
	 * 人脸置信度
	 */
	private String faceProbability;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 美丑打分，范围0-100，越大表示越美。
	 */
	private String beauty;
	/**
	 * 表情 none:不笑；smile:微笑；laugh:大笑
	 */
	private String expressionType;
	/**
	 * 脸型 square: 正方形 triangle:三角形 oval: 椭圆 heart: 心形 round: 圆形
	 */
	private String faceShapeType;
	/**
	 * 性别 male:男性 female:女性
	 */
	private String gender;
	/**
	 * 是否带眼镜 none:无眼镜，common:普通眼镜，sun:墨镜
	 */
	private String glassesType;
	/**
	 * 人种 yellow: 黄种人 white: 白种人 black:黑种人 arabs: 阿拉伯人
	 */
	private String raceType;
	/**
	 * 微信用户openid
	 */
	private String openId;
	/**
	 * 微信用户昵称
	 */
	private String nikeName;
	/**
	 * 图片路径
	 */
	private String imagePath;
	public Integer getFaceId() {
		return faceId;
	}
	public void setFaceId(Integer faceId) {
		this.faceId = faceId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getCached() {
		return cached;
	}
	public void setCached(Integer cached) {
		this.cached = cached;
	}
	public Integer getFaceNum() {
		return faceNum;
	}
	public void setFaceNum(Integer faceNum) {
		this.faceNum = faceNum;
	}
	public String getFaceToken() {
		return faceToken;
	}
	public void setFaceToken(String faceToken) {
		this.faceToken = faceToken;
	}
	public String getFaceProbability() {
		return faceProbability;
	}
	public void setFaceProbability(String faceProbability) {
		this.faceProbability = faceProbability;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getBeauty() {
		return beauty;
	}
	public void setBeauty(String beauty) {
		this.beauty = beauty;
	}
	public String getExpressionType() {
		return expressionType;
	}
	public void setExpressionType(String expressionType) {
		this.expressionType = expressionType;
	}
	public String getFaceShapeType() {
		return faceShapeType;
	}
	public void setFaceShapeType(String faceShapeType) {
		this.faceShapeType = faceShapeType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGlassesType() {
		return glassesType;
	}
	public void setGlassesType(String glassesType) {
		this.glassesType = glassesType;
	}
	public String getRaceType() {
		return raceType;
	}
	public void setRaceType(String raceType) {
		this.raceType = raceType;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNikeName() {
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
