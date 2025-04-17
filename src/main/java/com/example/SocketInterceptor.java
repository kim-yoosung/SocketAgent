package com.example;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

public class SocketInterceptor {

    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Origin Method method,
                               @Advice.AllArguments Object[] args) {
        System.out.println("[Agent] " + method.getName() + "() 호출됨");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                System.out.println("         └ 파라미터: " + arg);
            }
        }
    }
}

