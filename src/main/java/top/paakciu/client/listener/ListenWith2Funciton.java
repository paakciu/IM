package top.paakciu.client.listener;

/**
 * @author paakciu
 * @ClassName: ListenWith2Funciton
 * @date: 2021/4/12 14:30
 */
public class ListenWith2Funciton {
    public SendSuccessListener sendsuccessListener;
    public SendFailListener sendfailListener;

    /**
     * 添加各类监视器
     * @param listener
     * @return
     */
    public void setSendSuccessListener(SendSuccessListener listener){sendsuccessListener=listener;}
    public void setSendFailListener(SendFailListener listener){sendfailListener=listener;}
}
