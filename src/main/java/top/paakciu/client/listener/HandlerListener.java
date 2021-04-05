package top.paakciu.client.listener;

/**
 * @author paakciu
 * @InterfaceName: HandlerListener
 * @date: 2021/4/4 20:28
 */
public interface HandlerListener<T> {

    void onHandle(T str);

}
