package com.example;

import net.bytebuddy.asm.Advice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GetInputStreamAdvice {
    @Advice.OnMethodExit
    public static void onExit(@Advice.Return(readOnly = false) InputStream returned) {
        System.out.println("[Agent] getInputStream() 호출 → 가짜 응답 반환");

        String mockResponse = "MOCKED_RESPONSE_FROM_AGENT";
        returned = new ByteArrayInputStream(mockResponse.getBytes());
    }
}