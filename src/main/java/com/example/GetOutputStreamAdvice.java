package com.example;

import net.bytebuddy.asm.Advice;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GetOutputStreamAdvice {
    @Advice.OnMethodExit
    public static void onExit(@Advice.Return(readOnly = false) OutputStream returned, @Advice.This Socket socket) {
        if (socket != null && socket.getInetAddress() != null && 
            InterceptConfig.shouldIntercept(socket.getInetAddress().toString())) {
            System.out.println("[Agent] getOutputStream() 호출됨 - IP: " + socket.getInetAddress());
            returned = new ByteArrayOutputStream();
        }
    }
}