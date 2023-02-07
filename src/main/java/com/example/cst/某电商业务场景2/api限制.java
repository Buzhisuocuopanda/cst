package com.example.cst.某电商业务场景2;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class api限制 {
    private static Map<String, Integer> apiMap = new HashMap<>();

    public static boolean filter(HttpServletRequest request, int maxCount) {
        String api = request.getRequestURI();
        Integer count = apiMap.get(api);
        if (count == null) {
            count = 0;
        }
        if (count >= maxCount) {
            return false;
        }
        apiMap.put(api, ++count);
        return true;
    }
}


