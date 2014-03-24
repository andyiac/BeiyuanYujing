/*
 * Copyright (C) 2012 yueyueniao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beiyuan.appyujing.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beiyuan.appyujing.R;

public class News extends Fragment {
	//
	// private Button showLeft;
	// private Button showRight;
	private MyAdapter mAdapter;
	private ViewPager mPager;

	private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.fragment4, null);

		// showLeft = (Button) mView.findViewById(R.id.showLeft);
		// showRight = (Button) mView.findViewById(R.id.showRight);
		mPager = (ViewPager) mView.findViewById(R.id.pager);

		NewsYuJingPiceFragment1 page1 = new NewsYuJingPiceFragment1();
		NewsYuJingPiceFragment1 page2 = new NewsYuJingPiceFragment1();
		pagerItemList.clear();
		pagerItemList.add(page1);
		pagerItemList.add(page2);
		mAdapter = new MyAdapter(getChildFragmentManager());
		mPager.setAdapter(null);
		mPager.removeAllViews();
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				if (myPageChangeListener != null)
					myPageChangeListener.onPageSelected(position);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int position) {

			}
		});
		// mPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager())
		// {
		//
		//
		// @Override
		// public int getCount() {
		// return 2;
		// }
		//
		// @Override
		// public Fragment getItem(int arg0) {
		// return
		// SwitcherUtils.getTableFragment(arg0==0?FragmentType.TYPE_COMMUNITY_SERVICE:FragmentType.TYPE_COMMUNITY_ACTIVE);
		//
		// }
		// });
		
		return mView;
	}

//	@Override
//	public void onDestroyView() {
//		// TODO Auto-generated method stub
//		// ((FragmentPagerAdapter)mViewPager.getAdapter()).
//		mPager.setAdapter(null);
//		mPager.removeAllViews();
//		super.onDestroyView();
//	}

	public boolean isFirst() {
		if (mPager.getCurrentItem() == 0)
			return true;
		else
			return false;
	}

	public boolean isEnd() {
		if (mPager.getCurrentItem() == pagerItemList.size() - 1)
			return true;
		else
			return false;
	}

	public class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return pagerItemList.size();
		}

		@Override
		public Fragment getItem(int position) {

			Fragment fragment = null;
			if (position < pagerItemList.size())
				fragment = pagerItemList.get(position);
			else
				fragment = pagerItemList.get(0);

			return fragment;

		}
	}

	private MyPageChangeListener myPageChangeListener;

	public void setMyPageChangeListener(MyPageChangeListener l) {

		myPageChangeListener = l;

	}

	public interface MyPageChangeListener {
		public void onPageSelected(int position);
	}

}
