package cn.xsshome.mvcdo.pojo.ai.baidu.dbo;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月29日
 * <p>Description: 银行卡对象</p>
 */
public class BDOCRBankCardDO {
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
	 * 银行卡卡号
	 */
	private String bankCardNumber;
	/**
	 * 银行卡类别
	 */
	private String bankCardType;
	/**
	 * 银行名
	 */
	private String bankName;
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
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getBankCardType() {
		return bankCardType;
	}
	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
