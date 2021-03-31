package top.paakciu.client.listener;

import java.util.ArrayList;

/*
------------------------------------------------------------------------
private SimpleListener xxxListener;
public SimpleListener setxxxListener(SimpleListener listener){
    activeListener=listener;
    return activeListener;
}
------------------------------------------------------------------------
if(activeListener!=null)
    activeListener.apply(ctx);
------------------------------------------------------------------------
.setxxxListener((stc)->{
            System.out.println(stc);
            return stc;
        });
------------------------------------------------------------------------
*/
public interface SimpleListener<T, R>{
    R apply(T t);
}

