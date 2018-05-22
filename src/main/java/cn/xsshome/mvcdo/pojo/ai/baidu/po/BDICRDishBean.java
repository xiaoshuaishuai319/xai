package cn.xsshome.mvcdo.pojo.ai.baidu.po;

import java.util.List;
/**
 *
 * @author 小帅丶
 * @date 2018年5月9日
 * <p>Description: 菜品识别Bean </p>
 */
public class BDICRDishBean {
	private long log_id;
	private Integer result_num;
	private List<Result> result;
	
	public long getLog_id() {
		return log_id;
	}

	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}

	public Integer getResult_num() {
		return result_num;
	}

	public void setResult_num(Integer result_num) {
		this.result_num = result_num;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public static class Result{
		private String probability;
		private boolean has_calorie;
		private String calorie;
		private String name;
		public String getProbability() {
			return probability;
		}
		public void setProbability(String probability) {
			this.probability = probability;
		}
		public boolean isHas_calorie() {
			return has_calorie;
		}
		public void setHas_calorie(boolean has_calorie) {
			this.has_calorie = has_calorie;
		}
		public String getCalorie() {
			return calorie;
		}
		public void setCalorie(String calorie) {
			this.calorie = calorie;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
}
