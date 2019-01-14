package demo.bw.com.week3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import demo.bw.com.week3.MainActivity;
import demo.bw.com.week3.R;
import demo.bw.com.week3.base.BaseActivity;
import demo.bw.com.week3.bean.User;
import demo.bw.com.week3.prester.PresenterImpl;
import demo.bw.com.week3.view.IView;

public class RegisterActivity <T>extends BaseActivity implements IView<T> {
    private PresenterImpl presenter;
    private EditText mMobile ,mpassword;
    private Button mRegister;
    private ImageView mlogin;
    private String mUrl="http://120.27.23.105/user/reg";

    @Override
    protected int getLayout() {
        return R.layout.activity_regin;
    }

    @Override
    protected void initViews() {
        mMobile=findViewById(R.id.phone);
        mpassword=findViewById(R.id.pass);
        mRegister=findViewById(R.id.regin);
        mlogin=findViewById(R.id.img);
    }

    @Override
    protected void setOnclick() {
        mRegister.setOnClickListener(this);
        mlogin.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        presenter=new PresenterImpl(this);
    }

    @Override
    public void onClick( View view ) {
        switch (view.getId()){
            case R.id.regin:
                String mobile=mMobile.getText().toString().trim();
                String pass=mpassword.getText().toString().trim();
                if (mobile.isEmpty()||pass.isEmpty()){
                    Toast.makeText(this,"账号密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    presenter.startRequest(mUrl,mobile,pass);
                }
                break;
            case R.id.img:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    @Override
    public void success( T data ) {
        User user=(User) data;
        if(user.getCode().equals("1")){
            Toast.makeText(this,user.getMsg(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,user.getMsg(),Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    @Override
    public void error( T error ) {
        Toast.makeText(this, error.toString() + "失败", Toast.LENGTH_SHORT).show();
    }
}
