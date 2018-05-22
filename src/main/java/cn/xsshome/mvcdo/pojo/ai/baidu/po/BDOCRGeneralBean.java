package cn.xsshome.mvcdo.pojo.ai.baidu.po;

import java.util.List;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月16日
 * <p>Description: 文字识别bean</p>
 */
public class BDOCRGeneralBean {
   /**
    * 日志id
    */
	private long log_id;
	/**
	 * 错误code
	 */
	private long error_code;
	/**
	 * 错误信息
	 */
	private String error_msg;
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
	private Integer words_result_num;
	/**
	 * 识别结果数组
	 */
	private List<WordsResult> words_result;
	
	public long getLog_id() {
		return log_id;
	}

	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getWords_result_num() {
		return words_result_num;
	}

	public void setWords_result_num(Integer words_result_num) {
		this.words_result_num = words_result_num;
	}

	public List<WordsResult> getWords_result() {
		return words_result;
	}

	public void setWords_result(List<WordsResult> words_result) {
		this.words_result = words_result;
	}

	public long getError_code() {
		return error_code;
	}

	public void setError_code(long error_code) {
		this.error_code = error_code;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public static class WordsResult{
		/**
		 * 识别结果字符串
		 */
		private String words;

		public String getWords() {
			return words;
		}

		public void setWords(String words) {
			this.words = words;
		}
		
	}
}
