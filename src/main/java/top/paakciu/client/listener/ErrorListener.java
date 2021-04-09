package top.paakciu.client.listener;

public interface ErrorListener <T> {
    void onError(T error);
}