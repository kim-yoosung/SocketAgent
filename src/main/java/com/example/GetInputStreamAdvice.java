package com.example;

import net.bytebuddy.asm.Advice;

import java.io.InputStream;

public class GetInputStreamAdvice {
    @Advice.OnMethodExit
    public static InputStream onExit(@Advice.Return(readOnly = false) InputStream returnedStream) {

        System.out.println("[agent - socket] get!!!");
//        returnedStream = new InterceptedInputStream(returnedStream, AgentConfig.getLogFilePath());
        return returnedStream;
    }
}
