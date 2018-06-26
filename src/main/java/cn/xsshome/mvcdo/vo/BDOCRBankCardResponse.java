package cn.xsshome.mvcdo.vo;

public class BDOCRBankCardResponse extends RestResponse{
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
}
