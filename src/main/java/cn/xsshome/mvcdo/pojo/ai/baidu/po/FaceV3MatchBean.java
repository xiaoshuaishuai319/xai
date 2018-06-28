package cn.xsshome.mvcdo.pojo.ai.baidu.po;

import java.util.List;
/**
 * 人脸V3对比Bean
 * @author 小帅丶
 */
public class FaceV3MatchBean {
    private int error_code;
    private String error_msg;
    private long log_id;
    private long timestamp;
    private int cached;
    private Result result;
    
    public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public long getLog_id() {
		return log_id;
	}
	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getCached() {
		return cached;
	}
	public void setCached(int cached) {
		this.cached = cached;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public static class Result{
        private double score;
        private List<Face_list> face_list;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public List<Face_list> getFace_list() {
			return face_list;
		}
		public void setFace_list(List<Face_list> face_list) {
			this.face_list = face_list;
		}
        
    }
    /**
     * @author 小帅丶
     *
     */
    public static class Face_list{
        private String face_token;

		public String getFace_token() {
			return face_token;
		}
		public void setFace_token(String face_token) {
			this.face_token = face_token;
		}
    }
}

