package com.example;

import java.util.HashSet;
import java.util.Set;

public class InterceptConfig {
    private static final Set<String> targetIps = new HashSet<>();
    
    static {
        // 여기에 가로챌 IP 주소들을 추가
        targetIps.add("142.250.198.110");
        targetIps.add("172.30.12.30");
        targetIps.add("172.30.10.48");
        targetIps.add("172.23.29.11");
        targetIps.add("172.30.10.48");
    }
    
    public static boolean shouldIntercept(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        // IP 주소에서 포트 번호 제거
        String cleanIp = ip.split("/")[1].split(":")[0];
        return targetIps.contains(cleanIp);
    }
    
    public static void addTargetIp(String ip) {
        targetIps.add(ip);
    }
    
    public static void removeTargetIp(String ip) {
        targetIps.remove(ip);
    }
    
    public static Set<String> getTargetIps() {
        return new HashSet<>(targetIps);
    }
} 