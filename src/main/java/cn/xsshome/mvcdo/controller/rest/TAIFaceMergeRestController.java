package cn.xsshome.mvcdo.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.common.Constant;
import cn.xsshome.mvcdo.util.PrintUtil;
import cn.xsshome.mvcdo.util.QQSendEmailUtil;
import cn.xsshome.mvcdo.vo.BDConstant;
import cn.xsshome.mvcdo.vo.BDDishResponse;
import cn.xsshome.taip.ptu.TAipPtu;
/**
 * @author 小帅丶
 * @date 2018年5月29日
 * <p>Description: 腾讯ai人脸融合</p>
 */
@Controller
@RequestMapping(value="rest/ptu")
@Scope("prototype")
public class TAIFaceMergeRestController {
	private static Logger logger = LoggerFactory.getLogger(TAIFaceMergeRestController.class);
	TAipPtu aipPtu = new TAipPtu(AIConstant.QQ_APPID, AIConstant.QQ_APPKEY);
	/**
	 * 人脸融合接口
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/facemerge",method={RequestMethod.POST})
	public void uoloadFaceMerge(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		String resultData = "";	
		String clientType = request.getParameter("clientType");
		int model = Integer.parseInt(request.getParameter("model"));
		logger.info("=======访问的IP"+request.getRemoteAddr()+"======访问的User-Agent:"+request.getHeader("User-Agent"));
		logger.info("=======访问的类型"+clientType+"=======模板id："+model); 
		try {
			if(!clientType.equals("wcs")){
				 BDDishResponse bdDishResponse = new BDDishResponse();
				 bdDishResponse.setCode(BDConstant.BD_403.getCode().toString());
				 bdDishResponse.setMsg("缺少必要参数");
                resultData = JSON.toJSONString(bdDishResponse);
                logger.info("=====接口返回的内容:"+resultData);
                PrintUtil.printJson(response,resultData);
			}else{
				String authCode = request.getParameter("authCode");
				if(!authCode.equals(Constant.AUTH_CODE)){
					BDDishResponse bdDishResponse = new BDDishResponse();
            		bdDishResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
            		bdDishResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
                    resultData = JSON.toJSONString(bdDishResponse);
                    logger.info("=====接口返回的内容:"+resultData);
                    PrintUtil.printJson(response,resultData);
				}else{
					resultData = aipPtu.faceMerge(file.getBytes(), model);
					JSONObject object = JSON.parseObject(resultData);
					if(object.getInteger("ret")!=0){
						QQSendEmailUtil.send_email("人脸融合接口错误警报", "接口返回内容：\n"+resultData+"\n访问接口的ip："+request.getRemoteAddr(), "youngxiaoshuai@163.com");
					}
					PrintUtil.printJson(response, resultData);
				}
			}
		} catch (Exception e) {
			logger.info("人脸融合接口出错了"+e.getMessage());
			BDDishResponse bdDishResponse = new BDDishResponse();
			bdDishResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
			bdDishResponse.setMsg(BDConstant.BD_ERROR.getMsg());
            resultData = JSON.toJSONString(bdDishResponse);
            System.out.println(resultData);
            PrintUtil.printJson(response,resultData);
		}
	}
}
