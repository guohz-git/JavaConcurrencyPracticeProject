package chapter5.FutureTask;

/**
 * 自定义数据加载发送异常
 */
public class DataLoadException  extends  RuntimeException{

    private long code;
    private String message;

    public DataLoadException(String message, long code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }
}
