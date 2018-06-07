package cn.zucc.edu.Util;

public class MyResponse<T> {
    private  int code=0;
    private  String meesage;
    private T myBody;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMeesage() {
        return meesage;
    }

    public void setMeesage(String meesage) {
        this.meesage = meesage;
    }

    public T getMyBody() {
        return myBody;
    }

    public void setMyBody(T myBody) {
        this.myBody = myBody;
    }
}
