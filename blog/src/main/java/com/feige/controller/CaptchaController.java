package com.feige.controller;


import com.feige.common.constants.Constants;
import com.feige.common.utils.Base64;
import com.feige.common.utils.IdUtils;
import com.feige.common.utils.ResultAjax;
import com.feige.common.utils.VerifyCodeUtils;
import com.feige.common.utils.redis.RedisCache;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 * 
 * @author ruoyi
 */
@Api(tags = "生成验证码")
@RestController
@RequestMapping("/api")
public class CaptchaController
{
    @Autowired
    @Qualifier("redisCache")
    private RedisCache redisCache;

    /**
     * 生成验证码
     * getCode() {
     *       getCodeImg().then(res => {
     *         this.codeUrl = "data:image/gif;base64," + res.img;
     *         this.loginForm.uuid = res.uuid;
     *       });
     *     },
     *
     */
    @GetMapping("/captcha_image")
    public ResultAjax getCode(HttpServletResponse response) throws IOException
    {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try
        {
            ResultAjax ajax = ResultAjax.success();
            ajax.put("uuid", uuid);
            ajax.put("img", Base64.encode(stream.toByteArray()));
            return ajax;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultAjax.error(e.getMessage());
        }
        finally
        {
            stream.close();
        }
    }
}
