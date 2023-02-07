package com.example.cst.某电商业务场景2;


import redis.clients.jedis.Jedis;

public class redis限流 {

    private Jedis jedis;

    public void RedisRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = userId + ":" + actionKey;
        long nowTs = System.currentTimeMillis();
        jedis.zadd(key, nowTs, "" + nowTs);
        jedis.zremrangeByScore(key, 0, nowTs - period * 1000);
        return jedis.zcard(key) <= maxCount;
    }
}
