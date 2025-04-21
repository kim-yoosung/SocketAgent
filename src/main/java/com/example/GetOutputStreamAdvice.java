package com.example;

import net.bytebuddy.asm.Advice;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class GetOutputStreamAdvice {
    @Advice.OnMethodExit
    public static void onExit(@Advice.Return(readOnly = false) OutputStream returned) {
        System.out.println("[Agent] getOutputStream() 호출됨");
        // ByteArrayOutputStream을 사용하여 데이터를 메모리에 저장
        returned = new ByteArrayOutputStream();
    }
}