package cn.xsshome.mvcdo.controller.rest;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.interceptor.BDFactory;
import cn.xsshome.mvcdo.pojo.ai.baidu.BDFaceDetectDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.FaceV3DetectBean;
import cn.xsshome.mvcdo.service.ai.baidu.BDFaceDetectService;
import cn.xsshome.mvcdo.util.FileUtil;
import cn.xsshome.mvcdo.util.PrintUtil;
import cn.xsshome.mvcdo.vo.BDConstant;
import cn.xsshome.mvcdo.vo.BdFaceResponse;

/**
 * 
 * @author 小帅丶
 *
 */
@Controller
@RequestMapping(value="rest/face")
@Scope("prototype")
public class BDFaceRestController {
	AipFace aipFace = BDFactory.getAipFace();
	private static Logger logger = LoggerFactory.getLogger(BDFaceRestController.class);
	@Autowired
	private BDFaceDetectService bdFaceDetectService;
	/**
	 * 人脸检测网页上传
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转人脸检测网页上传");
		return "rest/bdfacefile";
	}
	 /**
     * 人脸检测上传百度
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detect",method = {RequestMethod.POST})
    public String uploadDetectFace(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        String clientType = request.getParameter("clientType");
        logger.info("=======访问的IP"+request.getRemoteAddr()+"======访问的User-Agent:"+request.getHeader("User-Agent"));
        logger.info("=======访问的类型"+clientType);
        String resultData = "";
        String perfix = "face/";
        String dbPath ="/"+perfix;
        String fileName = "faceV3BD"+new Date().getTime()/1000+FileUtil.fileType(file.getOriginalFilename());
        String filePath = request.getSession().getServletContext().getRealPath(perfix);
        logger.info("=======保存的路径"+filePath+"/"+fileName);
        try {
            if(!clientType.equals("web")){
        		 BdFaceResponse bdFaceResponse = new BdFaceResponse();
                 bdFaceResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
                 bdFaceResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
                 resultData = JSON.toJSONString(bdFaceResponse);
                 logger.info("=====接口返回的内容:"+resultData);
                 PrintUtil.printJson(response,resultData);
            }else if(clientType!=null&&clientType.equals("wcs")){
              String authCode = request.getParameter("authCode");
              logger.info("=======authCode:"+authCode);
            	if(!authCode.equals("123456")){
            		 BdFaceResponse bdFaceResponse = new BdFaceResponse();
                     bdFaceResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
                     bdFaceResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
                     resultData = JSON.toJSONString(bdFaceResponse);
                     logger.info("=====接口返回的内容:"+resultData);
                     PrintUtil.printJson(response,resultData);
            	}
            }else{
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
            //图片的本地路径
            String imagePath = filePath+fileName;
            HashMap<String, String> option = new HashMap<String, String>();
            option.put("face_field","age,beauty,expression,faceshape,gender,glasses,race,qualities");
            option.put("max_face_num", "1");
			JSONObject jsonObject = aipFace.detect(Base64Util.encode(FileUtil.readFileByBytes(imagePath)),"BASE64",option);
            FaceV3DetectBean faceBDJSON = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),FaceV3DetectBean.class);
            logger.info("======="+jsonObject.toString(2));
            if(null!=faceBDJSON.getResult()){
                BDFaceDetectDO faceBdDO = new BDFaceDetectDO();
                dbPath += fileName;
                faceBdDO = getFaceBdDO(faceBDJSON,dbPath);
                int result =bdFaceDetectService.save(faceBdDO);
                System.out.println("保存成功了 "+result);
                BdFaceResponse bdFaceResponse = new BdFaceResponse();
                bdFaceResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
                bdFaceResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
                bdFaceResponse.setAge(String.valueOf(faceBDJSON.getResult().getFace_list().get(0).getAge()));
                bdFaceResponse.setBeauty(String.valueOf(faceBDJSON.getResult().getFace_list().get(0).getBeauty()));
                bdFaceResponse.setExpression(faceBDJSON.getResult().getFace_list().get(0).getExpression().getType());
                bdFaceResponse.setFaceShape(faceBDJSON.getResult().getFace_list().get(0).getFace_shape().getType());
                bdFaceResponse.setGender(faceBDJSON.getResult().getFace_list().get(0).getGender().getType());
                bdFaceResponse.setGlasses(faceBDJSON.getResult().getFace_list().get(0).getGlasses().getType());
                bdFaceResponse.setRaceType(faceBDJSON.getResult().getFace_list().get(0).getRace().getType());
                bdFaceResponse.setExpression(getExpression(faceBDJSON));
                bdFaceResponse.setGender(getGender(faceBDJSON));
                bdFaceResponse.setGlasses(getGlasses(faceBDJSON));
                bdFaceResponse.setFaceShape(getFaceShape(faceBDJSON));
                bdFaceResponse.setRaceType(getRaceType(faceBDJSON));
                resultData = JSON.toJSONString(bdFaceResponse);
                logger.info("=====接口返回的内容:"+resultData);
                PrintUtil.printJson(response,resultData);
            }else{
                System.out.print("人脸检测百度接口返回为:"+faceBDJSON.getError_code()+"======"+faceBDJSON.getError_msg());
                BdFaceResponse bdFaceResponse = new BdFaceResponse();
                 bdFaceResponse.setCode(BDConstant.BD_NOFACE.getCode().toString());
                 bdFaceResponse.setMsg(BDConstant.BD_NOFACE.getMsg());
                resultData = JSON.toJSONString(bdFaceResponse);
                System.out.println(resultData);
                PrintUtil.printJson(response,resultData);
            }
            } 
        }catch (Exception e){
        	e.printStackTrace();
            System.out.println("人脸检测百度接口出错了"+e.getMessage());
            BdFaceResponse bdFaceResponse = new BdFaceResponse();
            bdFaceResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
            bdFaceResponse.setMsg(BDConstant.BD_ERROR.getMsg());
            resultData = JSON.toJSONString(bdFaceResponse);
            System.out.println(resultData);
            PrintUtil.printJson(response,resultData);
        }
        return null;
    }
    //获取人种中文说明 yellow: 黄种人 white: 白种人 black:黑种人 arabs: 阿拉伯人
    private String getRaceType(FaceV3DetectBean faceBDJSON) {
		String result = "";
		if (faceBDJSON.getResult().getFace_list().get(0).getRace().getType().equals("yellow")) {
			result = "黄种人";
		} else if (faceBDJSON.getResult().getFace_list().get(0).getRace().getType().equals("white")) {
			result = "白种人";
		} else if (faceBDJSON.getResult().getFace_list().get(0).getRace().getType().equals("black")) {
			result = "黑种人";
		} else if (faceBDJSON.getResult().getFace_list().get(0).getRace().getType().equals("arabs")) {
			result = "阿拉伯人";
		} else {
			result = "未知";
		}
		return result;
	}
    //获取脸型中文说明  square: 正方形 triangle:三角形 oval: 椭圆 heart: 心形 round: 圆形
	private String getFaceShape(FaceV3DetectBean faceBDJSON) {
		String result = "";
		if (faceBDJSON.getResult().getFace_list().get(0).getFace_shape().getType().equals("square")) {
			result = "正方形";
		} else if (faceBDJSON.getResult().getFace_list().get(0).getFace_shape().getType().equals("triangle")) {
			result = "三角形";
		} else if (faceBDJSON.getResult().getFace_list().get(0).getFace_shape().getType().equals("oval")) {
			result = "椭圆";
		} else if (faceBDJSON.getResult().getFace_list().get(0).getFace_shape().getType().equals("heart")) {
			result = "心形";
		} else if (faceBDJSON.getResult().getFace_list().get(0).getFace_shape().getType().equals("round")) {
			result = "圆形";
		}  else {
			result = "未知";
		}
		return result;
	}
	//获取是否带眼镜 中文说明 none:无眼镜，common:普通眼镜，sun:墨镜
	private String getGlasses(FaceV3DetectBean faceBDJSON) {
		String result = "";
		if (faceBDJSON.getResult().getFace_list().get(0).getGlasses().getType().equals("none")) {
			result = "无眼镜";
		} else if (faceBDJSON.getResult().getFace_list().get(0).getGlasses().getType().equals("common")) {
			result = "普通眼镜";
		} else if (faceBDJSON.getResult().getFace_list().get(0).getGlasses().getType().equals("sun")) {
			result = "墨镜";
		} else {
			result = "未知";
		}
		return result;
	}
	  //获取性别中文说明 性别 male:男性 female:女性
	  private String getGender(FaceV3DetectBean faceBDJSON) {
		  String result="";
	      if(faceBDJSON.getResult().getFace_list().get(0).getGender().getType().equals("male")){
	    	  result="男性";
	      }else if (faceBDJSON.getResult().getFace_list().get(0).getGender().getType().equals("female")) {
	    	  result="女性";
	      }else{
	    	  result="未知";
	      }
			return result;
		}
	  //获取表情中文说明       表情 none:不笑；smile:微笑；laugh:大笑
	  private String getExpression(FaceV3DetectBean faceBDJSON) {
		  String result="";
			if (faceBDJSON.getResult().getFace_list().get(0).getExpression().getType().equals("none")) {
				result = "不笑";
			} else if (faceBDJSON.getResult().getFace_list().get(0).getExpression().getType().equals("smile")) {
				result = "微笑";
			} else if (faceBDJSON.getResult().getFace_list().get(0).getExpression().getType().equals("laugh")) {
				result = "大笑";
			} else{
				result = "未知";
			}
			return result;
		}
  	//组装数据
    private BDFaceDetectDO getFaceBdDO(FaceV3DetectBean faceBDJSON,String imagePath) {
        BDFaceDetectDO bdDO = new BDFaceDetectDO();
        bdDO.setErrorCode(String.valueOf(faceBDJSON.getError_code()));
        bdDO.setErrorMsg(faceBDJSON.getError_msg());
        bdDO.setLogId(String.valueOf(faceBDJSON.getLog_id()));
        bdDO.setTimestamp(String.valueOf(faceBDJSON.getTimestamp()));
        bdDO.setCached(faceBDJSON.getCached());
        bdDO.setFaceNum(faceBDJSON.getResult().getFace_num());
        bdDO.setFaceToken(faceBDJSON.getResult().getFace_list().get(0).getFace_token());
        bdDO.setFaceProbability(String.valueOf(faceBDJSON.getResult().getFace_list().get(0).getFace_probability()));
        bdDO.setAge(faceBDJSON.getResult().getFace_list().get(0).getAge());
        bdDO.setBeauty(String.valueOf(faceBDJSON.getResult().getFace_list().get(0).getBeauty()));
        bdDO.setExpressionType(faceBDJSON.getResult().getFace_list().get(0).getExpression().getType());
        bdDO.setFaceShapeType(faceBDJSON.getResult().getFace_list().get(0).getFace_shape().getType());
        bdDO.setGender(faceBDJSON.getResult().getFace_list().get(0).getGender().getType());
        bdDO.setGlassesType(faceBDJSON.getResult().getFace_list().get(0).getGlasses().getType());
        bdDO.setRaceType(faceBDJSON.getResult().getFace_list().get(0).getRace().getType());
        bdDO.setImagePath(imagePath);
        return bdDO;
    }
}
