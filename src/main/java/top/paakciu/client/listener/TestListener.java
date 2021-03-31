package top.paakciu.client.listener;

import java.util.function.Function;
/*
private Function activeFun;
    public  <T,R> Function setActiveListener(Class<T> clazz,Function<T,R> function){
        activeFun=function;
        return activeFun;
    }

 */
public interface TestListener {
    <T,R> R test(Class<T> clazz, Function<T,R> function);
}
