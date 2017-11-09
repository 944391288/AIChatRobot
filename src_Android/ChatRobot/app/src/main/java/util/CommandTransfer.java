package util;

import java.io.Serializable;

/**
 * Created by 11917 on 2017/11/9.
 */

public class CommandTransfer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object data;//传输数据
    private String result;//返回结果
    private boolean flag;//操作是否成功
    private String cmd;//操作指令
    public CommandTransfer() {
        super();
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getCmd() {
        return cmd;
    }
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

}
