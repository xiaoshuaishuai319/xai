package cn.xsshome.mvcdo.vo;

public class BDDishResponse extends RestResponse{
	/**
	 * 卡路里，每100g的卡路里含量
	 */
	private String calorie;
	/**
	 * 是否包含卡路里
	 */
	private String hasCalorie;
	/**
	 * 菜名
	 */
	private String dishName;
	/**
	 * 置信度值，0-1
	 */
	private String probability;
	public String getCalorie() {
		return calorie;
	}
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}
	public String getHasCalorie() {
		return hasCalorie;
	}
	public void setHasCalorie(String hasCalorie) {
		this.hasCalorie = hasCalorie;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	
}
