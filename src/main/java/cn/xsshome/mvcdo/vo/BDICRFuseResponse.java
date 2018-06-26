package cn.xsshome.mvcdo.vo;

public class BDICRFuseResponse extends RestResponse{
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
	private String year;
	/**
	 * 识别的颜色
	 */
	private String colorResult;
	/**
	 * 置信度值，0-1
	 */
	private String probability;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getColorResult() {
		return colorResult;
	}
	public void setColorResult(String colorResult) {
		this.colorResult = colorResult;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	
}
