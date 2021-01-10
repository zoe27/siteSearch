package cn.com.site.page.dto;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: ResponseSalary
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2021-01-03 11:39
 */
public class ResponseSalary<T> {

    private Integer status;

    private String msg;

    private T t;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
