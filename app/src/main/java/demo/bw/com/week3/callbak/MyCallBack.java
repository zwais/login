package demo.bw.com.week3.callbak;

public interface MyCallBack<T> {
    void setData(T user);
    void  setError(T error);
}
