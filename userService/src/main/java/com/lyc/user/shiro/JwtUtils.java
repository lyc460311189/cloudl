package com.lyc.user.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 24*60*60*1000;
    private static final String SECRET = "dwise.com";

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public static Integer getUserId(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return Integer.parseInt(jwt.getClaim("userid").asString());
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
    public static String sign(Integer userId,String username) {
        try
        {
            Map<String,String> map=new HashMap<>();
            map.put("userid",userId.toString());
            map.put("username",username);
            return createToken(map);
        }
        catch (Exception ex){
            return StringUtils.EMPTY;
        }
    }
    /**
     * 生成token，保存claim字典
     * @param claims
     * @return
     * @throws Exception
     */
    public static String createToken(Map<String, String> claims) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            JWTCreator.Builder builder = JWT.create()
                    //.withIssuer(ISSUER)
                    //设置过期时间为2小时
                    .withExpiresAt(date);
            claims.forEach(builder::withClaim);
            return builder.sign(algorithm);
        } //catch (IllegalArgumentException | UnsupportedEncodingException e) {
        catch (IllegalArgumentException  e) {
            throw new Exception("生成token失败");
        }
    }
    /**
     * 验证jwt，并返回数据
     */
    public static Map<String, String> verifyToken(String token) throws Exception {
        Algorithm algorithm;
        Map<String, Claim> map;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                   // .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            map = jwt.getClaims();
        } catch (Exception e) {
            throw new Exception("鉴权失败");
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }
}
