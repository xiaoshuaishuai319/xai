package cn.xsshome.mvcdo.pojo.ai.baidu.dbo;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月10日
 * <p>Description: 针对于logo、animal、plant、ingredient、car通用</p>
 */
public class BDICRFuseDO {
	/**
	 * 主键id
	 */
	private Long icrId;
	/**
	 * 日志id
	 */
	private String logId;
	/**
	 * 返回结果数目
	 */
	private Integer resultNum;
	/**
	 * 识别的名称
	 */
	private String icrName;
	/**
	 * 识别的分数
	 */
	private String score;
	/**
	 * 识别的年份
	 */
	private String Pyear;
	/**
	 * 识别的颜色
	 */
	private String colorResult;
	/**
	 * width
	 */
	private Integer localWidth;
	/**
	 * height
	 */
	private Integer localHeight;
	/**
	 * top
	 */
	private Integer localTop;
	/**
	 * left
	 */
	private Integer localLeft;
	/**
	 * LOGOTYPE
	 */
	private String logoType;
	/**
	 * 置信度值，0-1
	 */
	private String probability;
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
	public Long getIcrId() {
		return icrId;
	}
	public void setIcrId(Long icrId) {
		this.icrId = icrId;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public Integer getResultNum() {
		return resultNum;
	}
	public void setResultNum(Integer resultNum) {
		this.resultNum = resultNum;
	}
	public String getIcrName() {
		return icrName;
	}
	public void setIcrName(String icrName) {
		this.icrName = icrName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getPyear() {
		return Pyear;
	}
	public void setPyear(String pyear) {
		Pyear = pyear;
	}
	public String getColorResult() {
		return colorResult;
	}
	public void setColorResult(String colorResult) {
		this.colorResult = colorResult;
	}
	public Integer getLocalWidth() {
		return localWidth;
	}
	public void setLocalWidth(Integer localWidth) {
		this.localWidth = localWidth;
	}
	public Integer getLocalHeight() {
		return localHeight;
	}
	public void setLocalHeight(Integer localHeight) {
		this.localHeight = localHeight;
	}
	public Integer getLocalTop() {
		return localTop;
	}
	public void setLocalTop(Integer localTop) {
		this.localTop = localTop;
	}
	public Integer getLocalLeft() {
		return localLeft;
	}
	public void setLocalLeft(Integer localLeft) {
		this.localLeft = localLeft;
	}
	public String getLogoType() {
		return logoType;
	}
	public void setLogoType(String logoType) {
		this.logoType = logoType;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
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
