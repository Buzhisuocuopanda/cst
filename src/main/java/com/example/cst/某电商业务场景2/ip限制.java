package com.example.cst.某电商业务场景2;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ip限制 {

    private static Map<String, Integer> ipMap = new HashMap<>();

    public static boolean filter(HttpServletRequest request, int maxCount) {
        String ip = request.getRemoteAddr();
        Integer count = ipMap.get(ip);
        if (count == null) {
            count = 0;
        }
        if (count >= maxCount) {
            return false;
        }
        ipMap.put(ip, ++count);
        return true;
    }

}
