package com.example.cst.某电商业务场景2;

import java.security.MessageDigest;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SignChecker签名验证 {

    private static final String SECRET = "YOUR_SECRET";

    public static boolean checkSign(Map<String, String> params) {
        String sign = params.get("sign");
        if (sign == null) {
            return false;
        }
        SortedMap<String, String> sortedParams = new TreeMap<>(params);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!"sign".equals(key) && value != null) {
                sb.append(key).append("=").append(value).append("&");
            }
        }
        sb.append("key=").append(SECRET);
        String mySign = getMD5(sb.toString());
        return sign.equalsIgnoreCase(mySign);
    }

    private static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

