package cn.xsshome.mvcdo.vo;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月21日
 * <p>Description: 身份证识别结果页面对象</p>
 */
public class BDOCRIdCardResponse extends RestResponse{
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
	 * 接口类型
	 */
	private String ocrType;
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
	public String getImageStatus() {
		return imageStatus;
	}
	public void setImageStatus(String imageStatus) {
		this.imageStatus = imageStatus;
	}
	public String getRiskType() {
		return riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	public String getOcrType() {
		return ocrType;
	}
	public void setOcrType(String ocrType) {
		this.ocrType = ocrType;
	}
}
