package com.beiyuan.appyujing.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.service.UrlService;
import com.beiyuan.appyujing.service.UrlServiceImpl;
import com.beiyuan.appyujing.tools.Tools;
import com.beiyuan.appyujing.view.CornerListView;
import com.beiyuan.appyujing.view.TitleView;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;

public class UpdateInfo extends MyActivity{
	private TitleView mTitle;
	
	private final int FIRST = 0;// 专业
	private final int SECOND = 1;// 班级
	private final int THIRD = 2;//手机号
	private final int FOURTH = 3;//密码
	
	
	private CornerListView mListView1 = null;
	private CornerListView mListView2 = null;
	private CornerListView mListView3 = null;
	ArrayList<HashMap<String, String>> map_list1 = null;
	ArrayList<HashMap<String, String>> map_list2 = null;
	ArrayList<HashMap<String, String>> map_list3 = null;
	String number;
	String password;
	
	//网络传输数据变量
	ArrayList<String> phoneKey;
	ArrayList<String> phoneValue;
	String strLoginRst;
	private UrlService urlService = new UrlServiceImpl();
	Handler handler;
	private ProgressDialog pdlogin;
	SharedPreferences sp1;
	SharedPreferences sp2;
	SimpleAdapter adapter3;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateinfo);
	
		
		getDataSource1();
        getDataSource2();
        getDataSource3();
        

		SimpleAdapter adapter1 = new SimpleAdapter(this, map_list1,
				R.layout.updateinfo_llist_item, new String[] { "user_id" ,"user_name"},
				new int[] { R.id.user_id ,R.id.user_name });
		SimpleAdapter adapter2 = new SimpleAdapter(this, map_list2,
				R.layout.updateinfo_llist_item, new String[] { "user_id" ,"user_name"},
				new int[] { R.id.user_id ,R.id.user_name });
		adapter3 = new SimpleAdapter(this, map_list3,
				R.layout.updateinfo_simple_list_item, new String[] { "user_id" ,"user_name"},
				new int[] { R.id.user_id ,R.id.user_name });
		
		
		mListView1 = (CornerListView)findViewById(R.id.list1);
		mListView2 = (CornerListView)findViewById(R.id.list2);
		mListView3 = (CornerListView)findViewById(R.id.list3);
		
		
		mListView1.setAdapter(adapter1);
		mListView2.setAdapter(adapter2);
		mListView3.setAdapter(adapter3);
		mListView1.setEnabled(false);
		mListView2.setEnabled(false);
		
		setListViewHeightBasedOnChildren(mListView1);
		setListViewHeightBasedOnChildren(mListView2);
		setListViewHeightBasedOnChildren(mListView3);
		
		initTopTitle();
		mListView3.setOnItemClickListener(new OnItemListSelectedListener());
	
		
	}

	//listview1的设计

	public ArrayList<HashMap<String, String>> getDataSource1() {
	
		map_list1 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
	
		map1.put("user_name","姓名");
		map1.put("user_id", "孙晓林");
	    map2.put("user_name", "昵称");
	    map2.put("user_id", "死一只鸟");
	
		map_list1.add(map1);
		map_list1.add(map2);


	
		return map_list1;
	}
	
	
	//listview2的设计
	
	public ArrayList<HashMap<String, String>> getDataSource2() {
		
		map_list2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		HashMap<String, String> map3 = new HashMap<String, String>();
	
		map1.put("user_name","年级");
		map1.put("user_id", "2012");
		map2.put("user_name","学号");
		map2.put("user_id", "201242247");
	    map3.put("user_name", "身份证号");
	    map3.put("user_id", "136983199203121652");
	
		map_list2.add(map1);
		map_list2.add(map2);
		map_list2.add(map3);


	
		return map_list2;
	}
	
	
	
	//listview3的设计
		
		public ArrayList<HashMap<String, String>> getDataSource3() {
			
			map_list3 = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map1 = new HashMap<String, String>();
			HashMap<String, String> map2 = new HashMap<String, String>();
			HashMap<String, String> map3 = new HashMap<String, String>();
			HashMap<String, String> map4 = new HashMap<String, String>();
		     
			map1.put("user_name","专业");
			map1.put("user_id", "电子信息工程");
		    map2.put("user_name", "班级");
		    map2.put("user_id", "三班");
			map3.put("user_name","手机号");
			map3.put("user_id", "15369302550");
		    map4.put("user_name", "密码");
		    map4.put("user_id","******");
	
			map_list3.add(map1);
			map_list3.add(map2);
	        map_list3.add(map3);
			map_list3.add(map4);
		
			return map_list3;
		}
		
		
		  
	     // 动态设置listview的高度  
	    
	    public void setListViewHeightBasedOnChildren(ListView listView) {    
	        ListAdapter listAdapter = listView.getAdapter();    
	        if (listAdapter == null) {    
	            return;    
	        }    
	        int totalHeight = 0;    
	        for (int i = 0; i < listAdapter.getCount(); i++) {    
	            View listItem = listAdapter.getView(i, null, listView);    
	            listItem.measure(0, 0);  //<span style="font-family: Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; line-height: 25px; text-align: left; ">在还没有构建View 之前无法取得View的度宽。 </span><span style="font-family: Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; line-height: 25px; text-align: left; ">在此之前我们必须选 measure 一下. </span><br style="font-family: Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; line-height: 25px; text-align: left; ">  
	            totalHeight += listItem.getMeasuredHeight();    
	        }    
	        ViewGroup.LayoutParams params = listView.getLayoutParams();    
	        params.height = totalHeight    
	                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));    
	        // params.height += 5;// if without this statement,the listview will be    
	        // a    
	        // little short    
	        // listView.getDividerHeight()获取子项间分隔符占用的高度    
	        // params.height最后得到整个ListView完整显示需要的高度    
	        listView.setLayoutParams(params);    
	    }    

	
	    //listView选择点击事件
	    
	class OnItemListSelectedListener implements OnItemClickListener {
		AlertDialog reviseAlertDialog1;
		AlertDialog reviseAlertDialog2;
		AlertDialog reviseAlertDialog3;
		AlertDialog reviseAlertDialog4;
		Button backBtn;
		Button confirmBtn;
		

		// @Override
		public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
				long arg3) {
//			Intent intent = new Intent();
			switch (arg2) {
			case THIRD:
				AlertDialog.Builder builder1 = new AlertDialog.Builder(UpdateInfo.this);
				
				LayoutInflater inflater = LayoutInflater.from(UpdateInfo.this);
				View view = inflater.inflate(R.layout.alertdialog, null);
				final EditText edit1 = (EditText)view.findViewById(R.id.phone);
				confirmBtn = (Button)view.findViewById(R.id.left);
				backBtn = (Button)view.findViewById(R.id.right);
				
				
                confirmBtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						number = edit1.getText().toString();
						String phoneNumber = "15369302550"; 
						int num = number.length();
//						HashMap<String, String> map1 = new HashMap<String, String>();
						
						if( number.equals("")|| num != 11||number.equals(phoneNumber)){
							Toast.makeText(UpdateInfo.this, "请输入新的11位手机号", Toast.LENGTH_SHORT).show();
						}
						else{
//					map1.put("user_id",number);
					Toast.makeText(UpdateInfo.this, "修改成功", Toast.LENGTH_SHORT).show();
										map_list3.get(arg2).put("user_id",number);
					adapter3.notifyDataSetChanged();
					
					reviseAlertDialog1.dismiss();
					
					
					}
						
					}
				});
               
               backBtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
					reviseAlertDialog1.dismiss();
						
					}
				});
               
				builder1.setTitle("修改手机号");
				builder1.setView(view);
				reviseAlertDialog1 = builder1.create();
				reviseAlertDialog1.show();
              
				
				
				break;
				
				
			case FOURTH:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(UpdateInfo.this);
				
				LayoutInflater inflater2 = LayoutInflater.from(UpdateInfo.this);
				View view2 = inflater2.inflate(R.layout.alertdialog2, null);
				final EditText edit2 = (EditText)view2.findViewById(R.id.password);
				final EditText edit3 = (EditText)view2.findViewById(R.id.pass);
				final EditText edit4 = (EditText)view2.findViewById(R.id.m_password);
				confirmBtn = (Button)view2.findViewById(R.id.left);
				backBtn = (Button)view2.findViewById(R.id.right);
				
				
                confirmBtn.setOnClickListener(new OnClickListener() {
					
                	
					@Override
					public void onClick(View v) {
						password = edit2.getText().toString();
						String pass = edit3.getText().toString();
						String m_password = edit4.getText().toString();
						int passnum = password.length();
						String mpassword = "12345678";
						
					if(!m_password.equals(mpassword)){
							Toast.makeText(UpdateInfo.this, "输入的原密码不正确", Toast.LENGTH_SHORT).show();
						}
					else if( passnum <=6){
						Toast.makeText(UpdateInfo.this, "请输入7-16位的新密码", Toast.LENGTH_SHORT).show();
					}else if(!password.equals(pass)){
						Toast.makeText(UpdateInfo.this, "两次输入的密码不一样或为空", Toast.LENGTH_SHORT).show();
					}
					else if(password.equals(mpassword)){
						Toast.makeText(UpdateInfo.this, "请输入与原密码不同的的新密码", Toast.LENGTH_SHORT).show();
					}
					
					else{
						
				    Toast.makeText(UpdateInfo.this, "修改成功", Toast.LENGTH_SHORT).show();
					reviseAlertDialog2.dismiss();
						
					}
						
						
					}
				});
               
               backBtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
				    reviseAlertDialog2.dismiss();	
						
					}
				});
               
				builder2.setTitle("修改密码");
				builder2.setView(view2);
				reviseAlertDialog2 = builder2.create();
				reviseAlertDialog2.show();
//				intent.setClass(UpdateInfo.this, Login.class);
//				startActivity(intent);
				break;

			}
		}
	}

	

	
	// 顶部菜单栏设计
	
	
	private void initTopTitle() {
		mTitle = (TitleView) findViewById(R.id.title);
		mTitle.setTitle("个人信息修改");
		mTitle.setLeftButton("返回", new OnLeftButtonClickListener() {
       
			@Override
			public void onClick(View button) {
				finish();
			}

		});
	    mTitle.setRightButton("完成", new OnRightButtonClickListener() {
			
			@Override
			public void onClick(View button) {
				
				//输出测试
				
				System.out.println("password =" + password);
				System.out.println("number = " + number);
				transPhonenumber();
				transPassword();
				
				
				
			}

			private void transPassword() {
				pdlogin = Tools.pd(UpdateInfo.this);
				
				try{// 从这里将开始连接服务器
					phoneKey = new ArrayList<String>();
					phoneValue = new ArrayList<String>();
					// 和服务端对应的属性有服务端Action接收
					phoneKey.add("userid");
					phoneKey.add("username");
					// key相应的值
					phoneValue.add("密码");
					phoneValue.add(password);
//
//					System.out.println("password============" + password);
//
//					System.out.println("u_name============" + number);

					// 登陆连接服务器，开辟新的线程
					Thread threadUpdateInfo = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Looper.prepare();

							// strLoginRst = "success";

//							strLoginRst = urlService.sentParams2Server(
//									phoneKey, phoneValue);

							System.out.println("strLoginRst======"
									+ strLoginRst);
							Log.i("JSON", "strLoginRst"+strLoginRst);
//							System.out.println("观察饭返回值是不是布尔型的========"
//									+ strLoginRst.equals("success"));

							if (strLoginRst.equals(null)) {

								Log.i("JSON", "连接失败");
								Message message = new Message();
								message.what = 5;
								handler.sendMessage(message);
								pdlogin.dismiss();
							}else if (strLoginRst.equals("{\"Status\":\"Success\"}")) {
								
								Message message = new Message();
								message.what = 1;
								handler.sendMessage(message);
								pdlogin.dismiss();

							} else if (strLoginRst
									.equals("{\"Status\":\"NotHaveUser\"}")) {
								Message message = new Message();
								message.what = -1;
								handler.sendMessage(message);
								pdlogin.dismiss();

							}else if (strLoginRst
									.equals("{\"Status\":\"PasswordError\"}")) {
								Message message = new Message();
								message.what = 2;
								handler.sendMessage(message);
								pdlogin.dismiss();

							} else {
								Message message = new Message();
								message.what = 5;
								handler.sendMessage(message);
								pdlogin.dismiss();
//								Tools.mToast(Login.this, "有问题了");
//								pdlogin.dismiss();
							}
						}
					});
					threadUpdateInfo.start();

				} catch (Exception e) {
					

					Toast.makeText(UpdateInfo.this, "连接服务器失败···",
							Toast.LENGTH_SHORT).show();
					pdlogin.dismiss();
					e.printStackTrace();
					Toast.makeText(UpdateInfo.this, "修改成功", Toast.LENGTH_SHORT).show();
				}
				
			}

			private void transPhonenumber() {
				// TODO Auto-generated method stub
				
			}
		});
	
	}


	}

