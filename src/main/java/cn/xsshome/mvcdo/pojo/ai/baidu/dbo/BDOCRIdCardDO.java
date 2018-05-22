package cn.xsshome.mvcdo.pojo.ai.baidu.dbo;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月18日
 * <p>Description: 文字识别数据库表对象4身份证识别</p>
 */
public class BDOCRIdCardDO {
   /**
    * 主键id
    */
	private Integer ocrId;
   /**
    * 日志id
    */
	private long logId;
	/**
	 * 错误code
	 */
	private long errorCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;
	/**
	 * 图像方向，当detect_direction=true时存在。
	 *  -1:未定义，
	 *  - 0:正向，
	 *  - 1: 逆时针90度，
	 *  - 2:逆时针180度，
	 *  - 3:逆时针270度
	 */
	private Integer direction;
	/**
	 * 识别结果数，表示words_result的元素个数
	 */
	private Integer wordsResultNum;
	/**
	 * 图片状态
	 */
	private String imageStatus;
	/**
	 * 住址
	 */
	private String address;
	/**
	 * 出生
	 */
	private String birth;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 公民身份号码
	 */
	private String idCardNum;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 签发日期
	 */
	private String issueDate;
	/**
	 * 签发机关
	 */
	private String authority;
	/**
	 * 失效日期
	 */
	private String expiryDate;
	/**
	 * 风险类型
	 */
	private String riskType;
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
	public Integer getOcrId() {
		return ocrId;
	}
	public void setOcrId(Integer ocrId) {
		this.ocrId = ocrId;
	}
	public long getLogId() {
		return logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
	}
	public long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public Integer getWordsResultNum() {
		return wordsResultNum;
	}
	public void setWordsResultNum(Integer wordsResultNum) {
		this.wordsResultNum = wordsResultNum;
	}
	public String getImageStatus() {
		return imageStatus;
	}
	public void setImageStatus(String imageStatus) {
		this.imageStatus = imageStatus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCardNum() {
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
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
	public String getRiskType() {
		return riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	
}
