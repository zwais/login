package demo.bw.com.week3.model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import demo.bw.com.week3.bean.User;
import demo.bw.com.week3.callbak.MyCallBack;
import demo.bw.com.week3.utils.HttpUtils;

public class ModelImpI implements  Model{
    @SuppressLint("HandlerLeak")
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage( Message msg ) {

            try {
                String jsonStr =(String) msg.obj;
                Gson gson= new Gson();
                JSONObject object=new JSONObject(jsonStr);
                String data=object.optString("date");
                if (data!=null){
                    User user= new User();
                    user.setCode(object.optString("code"));
                    user.setMsg(object.optString("msg"));
                    callBack.setData(user);
                }else {
                    User user=gson.fromJson(jsonStr,User.class);
                    callBack.setData(user);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private MyCallBack callBack;
    @Override
    public void getData( final String url,final String mobile, final String password,final MyCallBack callBack ) {
        this.callBack=callBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonStr=HttpUtils.post(url,mobile,password);
                    mHandler.sendMessage(mHandler.obtainMessage(0, jsonStr));
                } catch (Exception e) {
                    Looper.prepare();
                    callBack.setError("异常");
                    Looper.loop();
                }
            }
        }).start();
    }
}
