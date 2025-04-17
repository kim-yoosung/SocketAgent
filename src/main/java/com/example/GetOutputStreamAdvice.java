package com.example;

import net.bytebuddy.asm.Advice;

import java.io.OutputStream;

public class GetOutputStreamAdvice {
    @Advice.OnMethodExit
    public static void onExit(
            @Advice.Return(readOnly = false) OutputStream returnedStream
    ) {
        System.out.println("[agent - socket] get222!!!");
        // 원래 Socket.getOutputStream()이 만들어낸 스트림을 InterceptedOutputStream으로 교체
//        returnedStream = new InterceptedOutputStream(returnedStream, AgentConfig.getLogFilePath());
    }
}
