package com.jekken.eureka.Component;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * Create by Jekken
 * 2020/8/20 23:15
 */
@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.refreshTime}")
    private int refreshTime;

    /**
     * 生成JWT的Token
     */
    private String createToken(Map<String,Object> claims){
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate()).signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    /**
     * Token超时时间
     */
    private Date expirationDate(){
        return new Date(System.currentTimeMillis()+expiration * 1000);
    }

    /**
     * Token中获取jwt负载
     */
    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            LOGGER.info("JWT验证失败:{}",e.getMessage());
        }
        return claims;
    }

    /**
     * 获取超时时间
     */
    private Date getExpiredDateFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }


    /**
     * toke是否在指定时间内刷新
     */
    private boolean tokenRefreshJustBefore(String token,int time){
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        //刷新时间在创建的指定时间内
        if(refreshDate.after(created)&&refreshDate.before(DateUtil.offsetSecond(created,time))){
            return true;
        }
        return false;
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }



    /**
     * 刷新token
     */
    public String refreshHeadToken(String oldToken){
        if (StringUtils.isEmpty(oldToken)){
            return null;
        }
        Claims claims = getClaimsFromToken(oldToken);
        if(claims == null){
            return null;
        }
        if (isTokenExpired(oldToken)){
            return null;
        }
        //token在10分钟内刷新返回原token
        if (tokenRefreshJustBefore(oldToken,refreshTime)){
            return null;
        }else {
            claims.put(CLAIM_KEY_CREATED,new Date());
            return createToken(claims);
        }
    }

}
