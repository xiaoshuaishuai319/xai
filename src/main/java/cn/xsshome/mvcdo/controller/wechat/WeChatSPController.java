package cn.xsshome.mvcdo.controller.wechat;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.pojo.system.WeChatUserInfoDO;
import cn.xsshome.mvcdo.service.system.WechatUserInfoService;
import cn.xsshome.mvcdo.util.AesCbcUtil;
import cn.xsshome.mvcdo.util.PrintUtil;
import cn.xsshome.mvcdo.util.QQSendEmailUtil;
import cn.xsshome.mvcdo.util.WeChatConstant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 微信小程序code换取微信用户信息
 * @author 小帅丶
 *
 */
@Controller
@RequestMapping(value="wcsp")
public class WeChatSPController {
	private static Logger logger = LoggerFactory.getLogger(WeChatSPController.class);
	@Autowired
	private WechatUserInfoService wechatUserInfoService;
	/**
	 * 获取微信小程序用户openid等信息
	 * @param encryptedData 加密数据
	 * @param iv 加密算法初始向量
	 * @param code 微信小程序code码
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/oauth")
	public void wxOauth(String encryptedData,String iv,String code,HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			logger.info("请求的参数有:\n加密数据="+encryptedData+"\n加密算法初始向量="+iv+"\n微信小程序code="+code);
			//1.拼接code等参数换取私钥值
			String param = "appid="+WeChatConstant.WCSP_APPID+"&secret="+WeChatConstant.WCSP_APPSECRET+"&grant_type="+WeChatConstant.GRANT_TYPE+"&js_code="+code;
			String result = cn.xsshome.mvcdo.util.HttpUtil.post(WeChatConstant.JSCODE2SESSION_URL, param);
			logger.info("=======接口返回的数据:"+result);
			JSONObject jsonObject = JSON.parseObject(result);
			String session_key = jsonObject.get("session_key").toString();
			logger.info("session_key私钥值===="+session_key);
			//2.使用私钥值 和 算法向量值 加密的数据进行解密
			String userInfo = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
			logger.info("解密后返回页面的数据==="+userInfo);
			PrintUtil.printJson(response, userInfo);
			WeChatUserInfoDO userInfoDO = JSONObject.toJavaObject(JSON.parseObject(userInfo), WeChatUserInfoDO.class);
			if(null!=userInfoDO){
				WeChatUserInfoDO dbUserInfoDO = wechatUserInfoService.get(userInfoDO.getOpenId());
				if(null==dbUserInfoDO){
					String nickNameEncode = URLEncoder.encode(userInfoDO.getNickName(),"UTF-8");
					userInfoDO.setNickName(nickNameEncode);
					wechatUserInfoService.save(userInfoDO);
				}else{
					String nickNameEncode = URLEncoder.encode(userInfoDO.getNickName(),"UTF-8");
					userInfoDO.setNickName(nickNameEncode);
					if(!userInfoDO.equals(dbUserInfoDO)){
						wechatUserInfoService.updateWechatUserInfo(userInfoDO);
					}
				}
			}else{
				QQSendEmailUtil.send_email("小程序获取用户信息失败", ""+userInfo, AIConstant.EMAIL_ADDRESS);
			}
		} catch (Exception e) {
			QQSendEmailUtil.send_email("小程序获取用户信息异常", ""+e.getMessage(), AIConstant.EMAIL_ADDRESS);
			logger.error("oauth===出错了"+e.getMessage());
		}
	}
	
}
