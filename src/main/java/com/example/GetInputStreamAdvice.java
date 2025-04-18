package com.example;

import net.bytebuddy.asm.Advice;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.Socket;

public class GetInputStreamAdvice {
    @Advice.OnMethodExit
    public static void onExit(@Advice.Return(readOnly = false) InputStream returned, @Advice.This Socket socket) {
        if (socket != null && socket.getInetAddress() != null && 
            InterceptConfig.shouldIntercept(socket.getInetAddress().toString())) {
            System.out.println("[Agent] getInputStream() 호출 → 가짜 응답 반환 - IP: " + socket.getInetAddress());
            String mockResponse = "MOCKED_RESPONSE_FROM_AGENT";
            returned = new ByteArrayInputStream(mockResponse.getBytes());
        }
    }
}