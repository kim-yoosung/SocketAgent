package com.example;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class AgentMain {
    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("[agent] gogo");

        new AgentBuilder.Default()
                .ignore(none())
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .type(named("java.net.Socket"))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder
                                .visit(Advice.to(SocketInterceptor.class).on(named("connect")))
                )
                .installOn(inst);
    }
}
