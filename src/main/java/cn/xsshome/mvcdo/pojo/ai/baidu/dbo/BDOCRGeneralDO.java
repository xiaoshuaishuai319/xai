package cn.xsshome.mvcdo.pojo.ai.baidu.dbo;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月18日
 * <p>Description: 文字识别数据库表对象</p>
 */
public class BDOCRGeneralDO {
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
	 * 识别结果字符串
	 */
	private String words;
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
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
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
