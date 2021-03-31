package top.paakciu.client.listener;

/**
 * 这些回调事件会由nioEventLoopGroup接管
 *
 * 也就是说这是netty的线程组接管的
 */
public interface ResponseListener {
    //发送到服务器成功
    void onSendSuccess();
    //发送到服务器失败
    void onSendFail();
    //服务器返回结果为成功
    <T> void  onSuccess(Class<T> clazz, T msg);
//    Class<T> clazz, Function<T,R> function
    //服务器返回结果为错误
    <T> void  onFail(Class<T> clazz, T msg);
}
