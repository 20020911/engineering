package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 解析目标方法
 */
public abstract class AspectSupport {
    Method resolveMethod(ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature)point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        Method method =getDeclareMethod(targetClass,signature.getName(),signature.getMethod().getParameterTypes());
        if(method == null){
            throw new IllegalStateException("无法解析目标："+signature.getMethod());
        }
        return  method;
    }

    private Method getDeclareMethod(Class<?> clazz,String name,Class<?>... parameterTypes){
        try{
            return clazz.getDeclaredMethod(name,parameterTypes);
        }catch (NoSuchMethodException e){
            Class<?> superClass = clazz.getSuperclass();
            if(superClass != null) {
                return getDeclareMethod(superClass,name ,parameterTypes);
            }
        }
        return null;
    }
}
