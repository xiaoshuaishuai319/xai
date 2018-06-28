package cn.xsshome.mvcdo.common;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.error.AipError;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.util.Base64Util;
import com.baidu.aip.util.Util;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月15日
 * <p>Description: sdk未加入的接口封装</p>
 */
public class AipAdded extends AipImageClassify{
	//食材识别接口
	static final String INGREDIENT_URL="https://aip.baidubce.com/rest/2.0/image-classify/v1/classify/ingredient";
	//手写文字识别
	static final String HANDWRITING_URL="https://aip.baidubce.com/rest/2.0/ocr/v1/handwriting";
	public AipAdded(String appId, String apiKey, String secretKey) {
		super(appId, apiKey, secretKey);
	}
	/**
	 * 食材识别
	 * @param image 图片本地路径
	 * @param options 其他参数 
	 * @return JSONObject
	 */
    public JSONObject ingredientDetect(String image, HashMap<String, String> options) {
    	AipRequest request = new AipRequest();
    	preOperation(request);
    	try {
            byte[] data = Util.readFileByBytes(image);
            String base64Content = Base64Util.encode(data);
            request.addBody("image", base64Content);
            if (options != null) {
                request.addBody(options);
            }
            request.setUri(INGREDIENT_URL);
            postOperation(request);
            return requestServer(request);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }
	/**
	 * 手写文字识别
	 * @param image 图片本地路径
	 * @param options 其他参数 
	 * @return JSONObject
	 */
    public JSONObject handwritingDetect(String image, HashMap<String, String> options) {
    	AipRequest request = new AipRequest();
    	preOperation(request);
    	try {
            byte[] data = Util.readFileByBytes(image);
            String base64Content = Base64Util.encode(data);
            request.addBody("image", base64Content);
            if (options != null) {
                request.addBody(options);
            }
            request.setUri(HANDWRITING_URL);
            postOperation(request);
            return requestServer(request);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }
}
