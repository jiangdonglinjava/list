package com.mycompany.myapp.zhu_jie_mian;
import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.mycompany.myapp.*;
import cn.bmob.v3.*;
import android.content.res.*;
import cn.bmob.v3.datatype.*;
import cn.bmob.v3.listener.*;
import cn.bmob.v3.exception.*;
import java.io.*;
import android.widget.SearchView.*;
import android.app.*;
import de.hdodenhof.circleimageview.CircleImageView;

public class biao_ti_lan extends LinearLayout
{
	private CircleImageView userico_iv;
	private TextView username_tv,useremail_tv;
	private String path;
	private User user;

	public biao_ti_lan(Context context)
	{
		this(context, null);
	}


	public biao_ti_lan(Context context, AttributeSet attr)
	{
		super(context, attr);
		View view=View.inflate(context, R.layout.biao_ti_lan, this);
		//userico_iv = (ImageView)view.findViewById(R.id.biaotilan_user_ico);
		userico_iv=(CircleImageView)findViewById(R.id.biaotilan_user_ico);
		username_tv = (TextView)view.findViewById(R.id.biaotilan_user_name);
		useremail_tv = (TextView)view.findViewById(R.id.biaotilan_user_email);
		//	TypedArray ta = getContext().obtainStyledAttributes(attr, R.styleable.biaoti);
	}

	public void setUser(User user,String path)
	{
		this.user = user;
		this.path=path;
		init();
	}

	public void init()
	{
		if (null == user)
		{
			// 未登录，跳转到登录Or注册界面
		}
		else
		{
			// 已登录，正在进入应用
			String name=user.getIcon().getFilename();
			String url=user.getIcon().getFileUrl();
			BmobFile avatarFile =new BmobFile(name,"",url);
			avatarFile.download(new DownloadFileListener(){

					@Override
					public void done(String p1, BmobException p2)//p1是下载的文件存放路径
					{
						if(p2==null){
						// TODO: Implement this method
						Log.i("jjjj","bb");
						userico_iv.setImageBitmap(BitmapFactory.decodeFile(p1));
						username_tv.setText(user.getUsername());
						useremail_tv.setText(user.getEmail());
						}
					}

					@Override
					public void onProgress(Integer p1, long p2)
					{
						// TODO: Implement this method
					}
				});
		}
	}
	
	//***单击事件监听器
	
}


		//private String namespace = "http://schemas.android.com/apk/res/com.mycompany.myapp.zhu_jie_mian";

		//private Bitmap heardbitmap;
		//private String username;http://bmob-cdn-6840.b0.upaiyun.com/2016/10/24/d191bf93cb4e47a3811aeb30b2422957.jpg
