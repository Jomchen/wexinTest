package com.weixin.controller;

import com.weixin.common.BaseController;
import com.weixin.entity.common.WechatConfig;
import com.weixin.service.DeveloperFeedbackService;
import com.weixin.utils.Sha1;
import com.weixin.utils.WeChatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpc on 2017/5/15.
 */

@Controller
@RequestMapping(value = "/developerFeedback")
public class DeveloperFeedbackController extends BaseController {

    @Autowired
    WechatConfig wechatConfig;

    @Autowired
    private DeveloperFeedbackService developerFeedbackService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 成为微信公众号开发者
     * @param request
     * @return
     */
    @RequestMapping(value = "/developer", method = RequestMethod.GET)
    @ResponseBody
    public String developer(HttpServletRequest request) {

        /*
         * 加密校验流程如下：
         * 1. 将token、timestamp、nonce三个参数进行字典序排序
         * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
         * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
         *
         * 如果经过加密验证确认是微信发送的消息则原样返回echostr参数内容
         */

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String echostr = request.getParameter("echostr");
        String nonce = request.getParameter("nonce");
        String resultStr = "error";

        List< String> requestParamList = new ArrayList<String>();
        requestParamList.add(wechatConfig.getToken());
        requestParamList.add(timestamp);
        requestParamList.add(nonce);
        String signatureTmp = WeChatUtils.wechatDictionarySorting(requestParamList);
        signatureTmp = Sha1.encode(signatureTmp);

        if (signature.equals(signatureTmp)) {
            developerFeedbackService.insert(signature, timestamp, nonce, echostr);
            logger.warn("成为微信开发者成功。。。");
            resultStr = echostr;
        } else {
            logger.warn("成为微信开发者失败。。。");
        }

        logger.warn("#################################");
        logger.warn("signature：" + signature);
        logger.warn("timestamp：" + timestamp);
        logger.warn("echostr：" + echostr);
        logger.warn("nonce：" + nonce);
        logger.warn("signature：" + signature);
        logger.warn("signatureTmp：" + signatureTmp);
        logger.warn("#################################");

        return resultStr;
    }

}
