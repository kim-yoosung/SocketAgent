package com.example;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.net.Socket;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class AgentMain {
    public static void premain(String agentArgs, Instrumentation inst) {
        AgentBuilder.Listener listener = new AgentBuilder.Listener.Adapter() {
            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader,
                                         JavaModule module, boolean loaded, DynamicType dynamicType) {
                System.out.println("[Agent] Transformed: " + typeDescription.getName());
            }

            @Override
            public void onError(String typeName, ClassLoader classLoader,
                                JavaModule module, boolean loaded, Throwable throwable) {
                System.out.println("[Agent] Error: " + typeName);
                throwable.printStackTrace();
            }
        };

        new AgentBuilder.Default()
                .with(listener)
                .ignore(none())
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .type(named("java.net.Socket"))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder
                                .visit(Advice.to(GetInputStreamAdvice.class).on(named("getInputStream")))
                                .visit(Advice.to(GetOutputStreamAdvice.class).on(named("getOutputStream")))
                )
                .installOn(inst);
    }
}
