package demo.bw.com.week3.model;

import demo.bw.com.week3.callbak.MyCallBack;

public interface Model {
    void getData( String url, String name, String password, MyCallBack callBack);
}
