package com.example.yujin.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by SSU on 2016-12-18.
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    /*
        * 이 클래스의 부모생성자 호출시 인수로 반드시 FragmentManager객체를 넘겨야한다.
        * 이 객체는 Activity에서만 만들수 있고, 여기서사용중인 Fragment가 v4이기 때문에
        * Activity중에서도 ActionBarActivity에서 얻어와야한다.
        */
    int tabCount;
    int pageNum;

    public MyViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount=tabCount;
    }

    //아래의 메서드들의 호출 주체는 ViewPager이다.
    //ListView와 원리가 같다.
	/*
	 * 여러 프레그먼트 중 어떤 프레그먼트를 보여줄지 결정
	 */
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                    MoimInfo Moiminfo = new MoimInfo();
                    //isNext=false;
                    return Moiminfo;
            case 1:
                /* fragmentB=new FragmentB();
                return fragmentB;*/ //지도 Fragment 추가하기~
            case 2:
                    MoimAttendance Moimattend = new MoimAttendance();
                Log.d("","모임참석자");
                    //isNext=false;
                    return Moimattend;
            default:
                return null;
        }
    }
    /*
     * 보여질 프레그먼트가 몇개인지 결정
     */
    public int getCount() {
        return tabCount;
    }

    public void toPageNum(int num) {
        pageNum = num;
    }

}

