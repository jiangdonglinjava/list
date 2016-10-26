package com.mycompany.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import com.mycompany.myapp.zhu_jie_mian.*;
import com.mycompany.myapp3.*;

public class LoginActivity extends Activity implements View.OnClickListener
{


    private EditText etusername;
    private EditText etpassword;
    private Button login;
    private Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //这里的AppLication ID 写上自己创建项目得到的那个AppLication ID
        Bmob.initialize(this, "bf6235acbddffb68b874bd94a062d15d");
		isLogin();
        initialize();
    }



    private void initialize()
	{

        etusername = (EditText) findViewById(R.id.et_username);
        etpassword = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        sign = (Button) findViewById(R.id.sign);
        sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
	{
        switch (v.getId())
		{
            case R.id.login:
                final String username = etusername.getText().toString();
                String password = etpassword.getText().toString();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password))
				{
                    final BmobUser bmobUser = new BmobUser();
                    bmobUser.setUsername(username);
                    bmobUser.setPassword(password);
                    bmobUser.login(new SaveListener<BmobUser>() {
							@Override
							public void done(BmobUser p1, BmobException p2)
							{
								// TODO: Implement this method
								if (p2 == null)
								{
									toMainInfoActivity();
									//登录成功
									Toast("登录成功");
								}
								else
								{
									Toast("登陆失败！bmob服务器返回" + p2.getMessage());
								}
							}
						});
                }
				else
				{
					Toast("请输入用户名和密码");
				}
                break;
            case R.id.sign:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
//***
	private  void Toast(String p0)
	{
		Toast.makeText(LoginActivity.this, p0, Toast.LENGTH_SHORT).show();
		// TODO: Implement this method
	}

	//***
	private  void isLogin()
	{
		if (BmobUser.getCurrentUser(User.class) != null)
		{
			toMainInfoActivity();
		}

	}
	//***
	private void toMainInfoActivity()
	{
		User user = BmobUser.getCurrentUser(User.class);
		Intent intent = new Intent(LoginActivity.this, MainInfoActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}
}
