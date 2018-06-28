package cn.xsshome.mvcdo.pojo.ai.tencent.dbo;
/**
 * 
 * @author 小帅丶
 * @date 2018年6月5日
 * <p>Description: 优图识别数据库表对象</p>
 */
public class YouTuDetectFuseDO {
	/**
	 * 主键id
	 */
	private int youtuId;
	/**
	 * 返回状态值
	 */
	private int errorcode;
	/**
	 * 返回错误消息
	 */
	private String errormsg;
	/**
	 * 保留字段，目前不使用
	 */
	private String sessionId;
	/**
	 * 类型标签
	 */
	private String label;
	/**
	 * 置信度
	 */
	private String confidence;
	/**
	 * 字段字符串
	 */
	private String itemstring;
	/**
	 * 官网暂无说明
	 */
	private String faceId;
	/**
	 * 手势识别个数
	 */
	private int classifyCnt;
	/**
	 * 年龄[0~100]
	 */
	private int age;
	/**
	 * 性别[0/(female)~100(male)]
	 */
	private int gender;
	/**
	 * 微笑[0(normal)~50(smile)~100(laugh)]
	 */
	private int expression;
	/**
	 * 眼镜[0不戴眼镜 1戴眼镜 2戴墨镜] 注：替代原glass（Bool）字段
	 */
	private int glasses;
	/**
	 * 魅力[0~100]
	 */
	private int beauty;
	/**
	 * 帽子 官网暂无说明
	 */
	private int hat;
	/**
	 * 遮挡 官网暂无说明
	 */
	private int mask;
	/**
	 * 图片路径
	 */
	private String imagePath;
	/**
	 * 微信用户openid
	 */
	private String openId;
	/**
	 * 微信用户昵称
	 */
	private String nikeName;
	/**
	 * 入口类型 web wcs
	 */
	private String enterType;
	/**
	 * 接口类型
	 */
	private String apiType;
	public int getYoutuId() {
		return youtuId;
	}
	public void setYoutuId(int youtuId) {
		this.youtuId = youtuId;
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
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getConfidence() {
		return confidence;
	}
	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
	public String getItemstring() {
		return itemstring;
	}
	public void setItemstring(String itemstring) {
		this.itemstring = itemstring;
	}
	public String getFaceId() {
		return faceId;
	}
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	public int getClassifyCnt() {
		return classifyCnt;
	}
	public void setClassifyCnt(int classifyCnt) {
		this.classifyCnt = classifyCnt;
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
	public int getBeauty() {
		return beauty;
	}
	public void setBeauty(int beauty) {
		this.beauty = beauty;
	}
	public int getHat() {
		return hat;
	}
	public void setHat(int hat) {
		this.hat = hat;
	}
	public int getMask() {
		return mask;
	}
	public void setMask(int mask) {
		this.mask = mask;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	public String getEnterType() {
		return enterType;
	}
	public void setEnterType(String enterType) {
		this.enterType = enterType;
	}
	public String getApiType() {
		return apiType;
	}
	public void setApiType(String apiType) {
		this.apiType = apiType;
	}
}
