package com.example;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class SocketInterceptor {

    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Origin Method method,
                               @Advice.AllArguments Object[] args) {
        Object endpoint = args[0];String raw = endpoint.toString();
        System.out.println("[Agent] " + method.getName() + "() 호출됨");
        String ip = "";
        int slash = raw.indexOf('/');
        int colon = raw.indexOf(':');
        if (slash >= 0 && colon > slash) {
            ip = raw.substring(slash + 1, colon);
        }
        System.out.println("[Agent] " + ip + "ip 호출됨");

        String[] interceptIpArray = {
                "192.168.0.10",
                "10.0.0.5",
                "142.250.66.46"
        };

        if (args != null && args.length > 0 && Arrays.asList(interceptIpArray).contains(ip)) {
            for (Object arg : args) {
                System.out.println("         └ 파라미터: " + arg);
            }
        }
    }
}

