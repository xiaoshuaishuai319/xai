package cn.xsshome.mvcdo.pojo.ai.tencent.po;

public class FaceAgeBean {
	private int ret;
	private String msg;
	private Data data;
	public static class Data{
		private double score;
		private String degree;
		private String scores;
		private String info;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getDegree() {
			return degree;
		}
		public void setDegree(String degree) {
			this.degree = degree;
		}
		public String getScores() {
			return scores;
		}
		public void setScores(String scores) {
			this.scores = scores;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
	}
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
}
