package cn.xsshome.mvcdo.vo;

public class BDOCRGeneralResponse extends RestResponse{
	/**
	 * 识别结果字符串
	 */
	private String words;
	/**
	 * 识别结果数，表示words_result的元素个数
	 */
	private Integer wordsesultNum;
	/**
	 * 接口类型
	 */
	private String ocrType;
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public Integer getWordsesultNum() {
		return wordsesultNum;
	}
	public void setWordsesultNum(Integer wordsesultNum) {
		this.wordsesultNum = wordsesultNum;
	}
	public String getOcrType() {
		return ocrType;
	}
	public void setOcrType(String ocrType) {
		this.ocrType = ocrType;
	}
}
