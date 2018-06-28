package cn.xsshome.mvcdo.pojo.ai.baidu.po;

public class BDOCRBankCardBean {
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
	private Result result;
	public long getLog_id() {
		return log_id;
	}

	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}
	
	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public long getError_code() {
		return error_code;
	}

	public void setError_code(long error_code) {
		this.error_code = error_code;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	public static class Result{
	/**
	 * 银行卡卡号
	 */
	private String bank_card_number;
	/**
	 * 银行卡类别
	 */
	private String bank_card_type;
	/**
	 * 银行名
	 */
	private String bank_name;
	public String getBank_card_number() {
		return bank_card_number;
	}
	public void setBank_card_number(String bank_card_number) {
		this.bank_card_number = bank_card_number;
	}
	public String getBank_card_type() {
		return bank_card_type;
	}
	public void setBank_card_type(String bank_card_type) {
		this.bank_card_type = bank_card_type;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	}
}
