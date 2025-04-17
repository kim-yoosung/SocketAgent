package com.example;

import net.bytebuddy.asm.Advice;

import java.io.OutputStream;

public class GetOutputStreamAdvice {
    @Advice.OnMethodExit
    public static void onExit(@Advice.Return(readOnly = false) OutputStream returned) {
        System.out.println("[Agent] getOutputStream() 호출 → 무력화된 OutputStream으로 대체");

        returned = new OutputStream() {
            @Override
            public void write(int b) {
                // 아무것도 안함
                System.out.println("[Agent] write() → 차단됨");
            }

            @Override
            public void write(byte[] b, int off, int len) {
                // 무시
                System.out.println("[Agent] write(byte[]) → 전송 차단됨");
            }
        };
    }
}