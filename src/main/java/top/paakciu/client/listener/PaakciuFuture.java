package top.paakciu.client.listener;

import io.netty.channel.ChannelFuture;

/**
 * @author paakciu
 * @ClassName: PaakciuFuture
 * @date: 2021/3/26 10:46
 *
 * private PaakciuFuture future;
 *
 *     public PaakciuFuture getFuture() {
 *         return future;
 *     }
 *
 *     public void setFuture(PaakciuFuture future) {
 *         this.future = future;
 *     }
 *
 */
public class PaakciuFuture{
    private ChannelFuture future;
    private ResponseListener listener=null;
    //private ResponseListener sendlistener=null;

    public PaakciuFuture() { }
    public PaakciuFuture(ChannelFuture future) {
        this.future = future;
        future.addListener((f)->{
            if(f.isSuccess()){
                if(listener!=null)
                    listener.onSendSuccess();
            }else{
                if(listener!=null)
                    listener.onSendFail();
            }
        });
    }

    public ResponseListener getListener() {
        return listener;
    }

    public PaakciuFuture addListener(ResponseListener listener) {
        this.listener = listener;
        return this;
    }



}

//    public ChannelFuture getFuture() {
//        return future;
//    }
//
//    public void setFuture(ChannelFuture future) {
//        this.future = future;
//    }
//addListeners(GenericFutureListener<? extends Future<? super Void>>... listeners);
//    public PaakciuFuture addSendListener(GenericFutureListener<? extends Future<? super Void>>... listeners){
//        future.addListeners(listeners);
//        return this;
//    }
//public class PaakciuFuture {
//    boolean success;
//    CompletableFuture future;
//
//    public PaakciuFuture(boolean success,CompletableFuture future) {
//        this.success=success;
//        this.future = future;
//    }
//
//    public CompletableFuture<Void> then(Consumer<? super ErrorReturn> action) {
//        return future.thenAccept(action);
//    }
//
//}