package com.orange.vod.controller;

import cn.dsna.util.images.ValidateCode;
import com.orange.ajaxresult.AjaxResult;
import com.orange.ajaxresult.Constants;
import com.orange.base64.Base64;
import com.orange.idUtils.IdUtils;
import com.orange.vod.redis.RedisCache;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Package : com.orange.vod.controller
 * @Author : yilantingfeng
 * @Date : 2022/7/2 9:34 PM
 * @Version : V1.0
 */
@RestController
@Api(tags = "验证码")
public class VerificationCodeController {

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/verificationCode")
    public AjaxResult verificationCode() throws IOException {
        //180高，40宽，5个数字，50干扰线
        ValidateCode verifyCode = new ValidateCode(180, 40, 5, 50);
        BufferedImage buffImg = verifyCode.getBuffImg();
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(buffImg, "png", os);
        String code = verifyCode.getCode();
        HashMap<String, Object> map = new HashMap<>();
        map.put("image", "data:image/png;base64," + Base64.encode(os.toByteArray()));
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        map.put("uuid", uuid);
        return AjaxResult.success("获取成功！", map);
    }
}
