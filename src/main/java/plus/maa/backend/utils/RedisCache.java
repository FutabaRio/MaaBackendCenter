package plus.maa.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import plus.maa.backend.domain.LoginUser;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author AnselYuki
 */
@Setter
@Component
public class RedisCache {
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public RedisCache(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setCacheLoginUser(final String key, LoginUser value, long timeout, TimeUnit timeUnit) {
        String str;
        try {
            str = new ObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return;
        }
        redisTemplate.opsForValue().set(key, str, timeout, timeUnit);
    }

    public LoginUser getCacheLoginUser(final String key) {
        LoginUser loginUser;
        try {
            String json = redisTemplate.opsForValue().get(key);
            if (json == null || json.isEmpty()) {
                return null;
            }
            loginUser = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(json, LoginUser.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return loginUser;
    }
}