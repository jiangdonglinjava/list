package com.mycompany.myapp.zhu_jie_mian;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton;
import com.mxn.soul.slidingcard_core.*;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;

public class zhu_jie_mian extends Activity implements ContainerView.ContainerInterface
{

    private ContainerView contentView;
    private List<card> dataList,listindex ;
	private card photoContent ;




	LayoutInflater inflater;
	LinearLayout llt;
	RelativeLayout rly;
	LinearLayout.LayoutParams lps;
	CheckBox suolvckb,mCheckBox;
	ImageView suolviv,mImageView;
	private TextView mTextView,b;

	private String dir="/storage/emulated/0/miui/gallery/cloud/owner/戴媚媚的美甲/";


	public class suolvonCheckedChangeListener implements CompoundButton.OnCheckedChangeListener
	{

		@Override
		public void onCheckedChanged(CompoundButton p1, boolean p2)
		{
			// TODO: Implement this method
			String url=(String)p1.getTag();
			Object c=llt.findViewWithTag(url);
			llt.removeView((View) c);
			for(int i=0;i<listindex.size();i++){
				String a= listindex.get(i).getUrl();
				if(a==url){
					listindex.remove(i);
					b=(TextView)findViewById(R.id.activitymainTextView1);
					b.setText("删除了第"+(i+1)+"张，"+"还有"+listindex.size()+"张");
					break;
				}
			}
		}
	}



	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentView = (ContainerView) findViewById(R.id.contentview);

		start();
    }



	public void start()
	{
		File path=new File(dir);
		String[] flist= path.list();
		initData(flist);
	}



    private void initData(String[] flist)
	{
        dataList = new ArrayList<>();
		listindex = new ArrayList<>();
    	for (int i=0;i < flist.length;i++)
		{
			String title=flist[i].substring(0, flist[i].indexOf("."));
			String url=flist[i];
			String id=String.valueOf(i);
			photoContent =  new card() ;
			photoContent.setSelectIs(false);
			photoContent.setId(id) ;
			photoContent.setTitle(title);
			photoContent.setUrl(url);
			dataList.add(photoContent);
		}
		contentView.initCardView(zhu_jie_mian.this, R.layout.sliding_card_item, R.id
								 .sliding_card_content_view);
    }



    @Override
    public void initCard(SlidingCard card, final int index)
	{


		mImageView = (ImageView) card.findViewById(R.id.user_imageview);
        mTextView = (TextView) card.findViewById(R.id.user_text);
		mCheckBox=(CheckBox) card.findViewById(R.id.slidingcarditemCheckBox1);
		if (dataList.get(index) != null)
		{
            mTextView.setText(dataList.get(index).getTitle());
			String a=dir + dataList.get(index).getUrl();
			mImageView.setImageBitmap(BitmapFactory.decodeFile(a));

			mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton p1, boolean p2)
					{
						if (p1.isChecked())
						{
							boolean bl=false;
							for(int i=0;i<listindex.size();i++){
								if(dataList.get(0).getUrl()==listindex.get(i).getUrl()){
									bl=true;
									break;
								}
							}
							if(bl==false){
								listindex.add(dataList.get(0));

								b=(TextView)findViewById(R.id.activitymainTextView1);
								b.setText("你选择了"+listindex.size()+"张");
								inflater= LayoutInflater.from(zhu_jie_mian.this);
								rly=(RelativeLayout) inflater.inflate(R.layout.suolvcard, null, false).findViewById(R.id.suolvcardRelativeLayout1);
								rly.setTag(dataList.get(0).getUrl());

								suolviv=(ImageView) rly.findViewById(R.id.suolvcardImageView1);
								suolvckb=(CheckBox) rly.findViewById(R.id.suolvcardCheckBox1);
								suolvckb.setTag(dataList.get(0).getUrl());
								suolviv.setImageBitmap(BitmapFactory.decodeFile(dir + dataList.get(0).getUrl()));
								suolvonCheckedChangeListener sllistener=new suolvonCheckedChangeListener();
								suolvckb.setOnCheckedChangeListener(sllistener);

								lps=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
								llt=(LinearLayout)findViewById(R.id.activitymainLinearLayout1);
								llt.addView(rly, lps);
							}
						}
					}
				});
        }
    }

    @Override
    public void exChangeCard()
	{
        card item = dataList.get(0);
        dataList.remove(0);
        dataList.add(item);
    }

    public int getResourceByReflect(String imageName)
	{
        Class drawable = R.drawable.class;
        Field field ;
        int r_id;
        try
		{
            field = drawable.getField(imageName);
            r_id = field.getInt(field.getName());
        }
		catch (Exception e)
		{
            r_id = R.mipmap.ic_launcher;
            Log.e("ERROR", String.valueOf(e.getMessage()));
        }
        return r_id;
    }
}
