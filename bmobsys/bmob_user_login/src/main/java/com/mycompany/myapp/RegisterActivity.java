package com.mycompany.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageView;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.BmobUser;
import java.io.File;
import cn.bmob.v3.datatype.BmobFile;
import android.provider.MediaStore;
import android.net.Uri;
import android.os.Environment;
import android.graphics.*;
import java.net.*;
import cn.bmob.v3.listener.*;

public class RegisterActivity extends Activity implements View.OnClickListener
{

    private EditText etusername;
    private EditText etpassword;
    private EditText etemail;
    private Button register;
    private Button cancel;
	private String TAG="aaa";
	private File file;
	private String fileurl;



	/* 头像文件 */
	private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

	/* 请求识别码 */
	private static final int CODE_GALLERY_REQUEST = 0xa0;
	private static final int CODE_CAMERA_REQUEST = 0xa1;
	private static final int CODE_RESULT_REQUEST = 0xa2;

	// 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
	private static int output_X = 480;
	private static int output_Y = 480;

	private ImageView headImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
	{

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
    }


    private void initialize()
	{

        etusername = (EditText) findViewById(R.id.et_username);
        etpassword = (EditText) findViewById(R.id.et_password);
		headImage = (ImageView)findViewById(R.id.regView1);
        etemail = (EditText) findViewById(R.id.et_email);
        register = (Button) findViewById(R.id.register);
		register.setBackground(getResources().getDrawable(R.drawable.button_back));
        register.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancel);
		cancel.setBackground(getResources().getDrawable(R.drawable.button_back));
        cancel.setOnClickListener(this);
		Button buttonLocal = (Button) findViewById(R.id.activityregisterButton1);
		buttonLocal.setOnClickListener(this);
		Button buttonCamera = (Button) findViewById(R.id.activityregisterButton2);
		buttonCamera.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
	{
        switch (v.getId())
		{
            case R.id.register:
                registerData();
                break;
            case R.id.cancel:
                break;
			case R.id.activityregisterButton1:
				choseHeadImageFromGallery();
				break;
			case R.id.activityregisterButton2:
				choseHeadImageFromCameraCapture();
				break;

        }
    }

    /**
     * 注册
     */



    private void registerData()
	{

        final String name = etusername.getText().toString();
        final String password = etpassword.getText().toString();
        final String email = etemail.getText().toString();
		if (file == null)
		{
			Toast.makeText(RegisterActivity.this, "请选择一张照片作为头像", Toast.LENGTH_SHORT).show();
		}
		else
		{
			final User user = new User();
			final BmobFile bmobfile=new BmobFile(file);
			bmobfile.upload(new UploadFileListener(){//启动上传任务

					@Override
					public void done(BmobException p1)
					{
						if(p1==null){
						// 头像上传成功
							user.setUsername(name);
							user.setIcon(bmobfile);
							user.setEmail(email);
							user.setPassword(password);
							user.signUp(new SaveListener<BmobUser>() {
									@Override
									public void done(BmobUser p1, BmobException p2)
									{
										// TODO: Implement this method
										if (p2 == null)
										{
											Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
										}
										else
										{
											Toast.makeText(RegisterActivity.this, "注册失败" + p2.getMessage(), Toast.LENGTH_SHORT).show();
										}
									}
								});
							}else{
								Toast.makeText(RegisterActivity.this,p1.getMessage(),Toast.LENGTH_SHORT).show();
							}
						}
				});
		}
	}

	@Override
	public  void onFailure(int i, String s)
	{
		Log.d("RegisterActivity", "报错了" + s.toString());
	}


	// 从本地相册选取图片作为头像
	private void choseHeadImageFromGallery()
	{
		Intent intentFromGallery = new Intent();
		// 设置文件类型
		intentFromGallery.setType("image/*");
		intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
	}

	// 启动手机相机拍摄照片作为头像
	private void choseHeadImageFromCameraCapture()
	{
		Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		// 判断存储卡是否可用，存储照片文件
		if (hasSdcard())
		{
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
									   .fromFile(new File(Environment
														  .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
		}

		startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
									Intent intent)
	{

		Uri uri=null;
		// 用户没有进行有效的设置操作，返回
		if (resultCode == RESULT_CANCELED)
		{
			Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
			return;
		}

		switch (requestCode)
		{
			case CODE_GALLERY_REQUEST://从相册返回
				uri = intent.getData();
				break;

			case CODE_CAMERA_REQUEST://从相机返回
				if (hasSdcard())
				{
					File tempFile = new File(
						Environment.getExternalStorageDirectory(),
						IMAGE_FILE_NAME);
					uri = Uri.fromFile(tempFile);
				}
				else
				{
					Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
						.show();
				}
				break;
		}

		Bitmap bt=BitmapFactory.decodeFile(uri.getPath());
		file = new File(uri.getPath());
		headImage.setImageBitmap(bt);
		super.onActivityResult(requestCode, resultCode, intent);
	}


	public static boolean hasSdcard()
	{
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED))
		{
			// 有存储的SDCard
			return true;
		}
		else
		{
			return false;
		}
	}
}




//////
