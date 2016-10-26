package com.mycompany.myapp;

import android.app.Activity;
import android.os.Bundle;
import com.mycompany.myapp.zhu_jie_mian.biao_ti_lan;
import cn.bmob.v3.*;
import cn.bmob.v3.datatype.*;
import java.io.*;
import android.webkit.*;
import cn.bmob.v3.listener.*;
import cn.bmob.v3.exception.*;
import android.view.View.*;
import android.view.View;
import android.widget.*;
import android.content.*;


public class MainInfoActivity extends Activity
{
    private User user;
	private Button login_out;


    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_info_activity);
		// 获取本地用户对象

		initialize();
	}

    private void initialize()
	{
		user = BmobUser.getCurrentUser(User.class);
		biao_ti_lan bt=(biao_ti_lan)findViewById(R.id.main_biao_ti_lan1);//获取组合控件
		bt.setUser(user, this.getExternalCacheDir().toString());//
		login_out=(Button)findViewById(R.id.biaotilan_login_out);
		login_out.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					user.logOut();
					intent();
					MainInfoActivity.this.finish();
				}
		});
	}

	public void intent()
	{
		Intent intent=new Intent(MainInfoActivity.this, LoginActivity.class);
		startActivity(intent);
	}

}
