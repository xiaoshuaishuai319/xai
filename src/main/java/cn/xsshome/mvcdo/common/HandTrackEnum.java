package cn.xsshome.mvcdo.common;

import cn.xsshome.mvcdo.util.QQSendEmailUtil;

public class HandTrackEnum {
	public static String getLabelName(String label) throws Exception {
		String labelName ="";
		if(label.equals("1")){
			labelName="1";
			return labelName;
		}else if (label.equals("6")||label.equals("SIX")) {
			labelName="6";
			return labelName;
		}else if (label.equals("8")||label.equals("EIGHT")) {
			labelName="8";
			return labelName;
		}else if (label.equals("FIST")) {
			labelName="拳头";
			return labelName;
		}else if (label.equals("HEART")) {
			labelName="比心";
			return labelName;
		}else if (label.equals("LIFT")) {
			labelName="托";
			return labelName;
		}else if (label.equals("LIKE")) {
			labelName="点赞";
			return labelName;
		}else if (label.equals("LOVE")) {
			labelName="我爱你";
			return labelName;
		}else if (label.equals("OK")) {
			labelName="OK";
			return labelName;
		}else if (label.equals("PAPER")) {
			labelName="布";
			return labelName;
		}else if (label.equals("ROCK")) {
			labelName="摇滚手势";
			return labelName;
		}else if (label.equals("SCISSOR")) {
			labelName="剪刀";
			return labelName;
		}else if (label.equals("ONE")) {
			labelName="食指";
			return labelName;
		}else{
			 labelName =label;
			 QQSendEmailUtil.send_email("优图手势未定义提醒", "接口识别的手势英文为："+label,AIConstant.EMAIL_ADDRESS);
		    return labelName;
		}
	}
}