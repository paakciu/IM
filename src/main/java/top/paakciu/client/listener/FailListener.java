package top.paakciu.client.listener;
/*
------------------------------------------------------------------------
if(failListener!=null)
    failListener.onFail(str);
------------------------------------------------------------------------

 */
public interface FailListener<T> {
    void onFail(T str);
}
