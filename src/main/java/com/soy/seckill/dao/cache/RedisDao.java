package com.soy.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.soy.seckill.entity.Seckill;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Soy on 2016/12/14.
 */
public class RedisDao {
    private final JedisPool jedisPool;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public String putSeckill(Seckill seckill) {
        String result;
        try (Jedis jedis = jedisPool.getResource()) {
            //序列化
            String key = "seckill:" + seckill.getSeckillId();
            byte[] bytes =
                    ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            //超时缓存
            int timeout = 60 * 60; //单位：秒
            result = jedis.setex(key.getBytes(), timeout, bytes);
        }
        return result;
    }


    public Seckill getSeckill(int seckillId) {
        //redis操作逻辑
        byte[] bytes;
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "seckill:" + seckillId;
            bytes = jedis.get(key.getBytes());
        }
        //缓存是否存在
        if (bytes != null) {
            //如果存在，创建一个空对象来放置
            Seckill seckill = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, seckill, schema); //反序列化
            return seckill;
        } else {
            return null;
        }
    }
}
