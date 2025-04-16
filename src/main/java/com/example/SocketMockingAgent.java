package com.example;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
// import net.bytebuddy.dynamic.scaffold.TypeValidation; // 사용 안 함
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
// import java.util.jar.JarFile; // 사용 안 함
import java.io.File;
import java.net.Socket;
import java.security.ProtectionDomain;
import java.util.jar.JarFile;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class SocketMockingAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[SocketAgent] premain() loaded. Basic Advice.");

        new AgentBuilder.Default()
            // 기본 설정 사용, 필요 시 ignore 추가 가능
            // .ignore(none()) 
            // 기본 재정의/재변환 전략 사용
            .type(named("java.net.Socket")) // 이름으로 Socket 클래스 찾기
            .transform((builder, typeDescription, classLoader, module, protectionDomain) -> {
                System.out.println("[SocketAgent] Transforming java.net.Socket");
                return builder
                    .visit(Advice.to(GetInputStreamAdvice.class).on(named("getInputStream")))
                    .visit(Advice.to(GetOutputStreamAdvice.class).on(named("getOutputStream")));
            })
            .installOn(inst);

        System.out.println("[SocketAgent] Agent installed.");
    }
} 