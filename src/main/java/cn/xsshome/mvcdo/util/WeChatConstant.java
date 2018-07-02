package cn.xsshome.mvcdo.util;
/**
 * 微信相关常量值
 * @author 小帅丶
 */
public class WeChatConstant {
	/**
	 * 微信小程序的APPID
	 */
	public static final String WCSP_APPID="微信小程序的APPID";
	/**
	 * 微信小程序的APPSECRET
	 */
	public static final String WCSP_APPSECRET="微信小程序的APPSECRET";
	/**
	 * 获取session_key秘钥接口地址
	 */
	public static final String JSCODE2SESSION_URL="https://api.weixin.qq.com/sns/jscode2session";
	/**
	 * 授权类型
	 */
	public static final String GRANT_TYPE="authorization_code";
}
