package crud.bean;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回类
 */
public class Msg {
    private int code;
    private String msg;
    private Map<String, Object> extendMap = new HashMap<>();

    public static Msg success() {
        Msg msg = new Msg();
        msg.setCode(100);
        msg.setMsg("处理成功");
        return msg;
    }

    public static Msg fail() {
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("处理失败");
        return msg;
    }

    public Msg add(String key, Object val) {
        this.extendMap.put(key, val);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtendMap() {
        return extendMap;
    }

    public void setExtendMap(Map<String, Object> extendMap) {
        this.extendMap = extendMap;
    }

}
