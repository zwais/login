package demo.bw.com.week3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import demo.bw.com.week3.activity.Main2Activity;
import demo.bw.com.week3.activity.RegisterActivity;
import demo.bw.com.week3.base.BaseActivity;
import demo.bw.com.week3.bean.User;
import demo.bw.com.week3.prester.PresenterImpl;
import demo.bw.com.week3.view.IView;

public class MainActivity<T> extends BaseActivity implements IView<T> {
    private PresenterImpl presenter;
    private String mUrl="http://120.27.23.105/user/login";
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private EditText psize,password;
    private TextView reg,login;
    private boolean islogin;
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        psize=findViewById(R.id.phone);
        password=findViewById(R.id.pass);
        reg=findViewById(R.id.regin);
        login=findViewById(R.id.login);
        presenter = new PresenterImpl(this);
    }

    @Override
    protected void setOnclick() {
        login.setOnClickListener(this);
        reg.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        mShared=getSharedPreferences(".....",MODE_PRIVATE);
        mEditor=mShared.edit();
        islogin=mShared.getBoolean("islogin",false);
    }

    @Override
    public void onClick( View view ) {
        switch (view.getId()){
            case R.id.login:
                String mobile=psize.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if (mobile.isEmpty()||pass.isEmpty()){
                    Toast.makeText(this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    presenter.startRequest(mUrl,mobile,pass);
                }
                break;
            case R.id.regin:
                startActivity(new Intent(this,RegisterActivity.class));
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
            startActivity(new Intent(this,Main2Activity.class));
            finish();
            mEditor.putBoolean("islogin",true);
            mEditor.commit();
        }
    }

    @Override
    public void error( T error ) {
        String e=(String) error;
        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
    }
}
