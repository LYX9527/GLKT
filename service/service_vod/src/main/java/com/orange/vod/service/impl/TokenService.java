package com.orange.vod.service.impl;

import com.orange.ajaxresult.Constants;
import com.orange.ajaxresult.StringUtils;
import com.orange.idUtils.UUID;
import com.orange.vod.domain.TempUser;
import com.orange.vod.redis.RedisCache;
import com.orange.vod.service.TempUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.vod.service.impl
 * @className : TokenSrevice
 * @description:
 * @date : 2022/7/7 12:30
 */
@Service
public class TokenService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TempUserService tempUserService;

    public String createToken(Integer userId) {
        refreshToken(userId);
        String token = UUID.fastUUID().toString();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    public String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "abcdefghijklmnopqrstuvwxyz")
                .compact();
        return token;
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody();
    }


    public void refreshToken(Integer id) {
        TempUser tempUser = tempUserService.getById(id);
        String tokenKey = getTokenKey(tempUser.getPassword());
        redisCache.setCacheObject(tokenKey, tempUser, 30, TimeUnit.MINUTES);
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return "login_tokens:" + uuid;
    }
}
