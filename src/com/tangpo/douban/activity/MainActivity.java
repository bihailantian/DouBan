package com.tangpo.douban.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TabHost;
import android.widget.TabWidget;
import com.tangpo.douban.R;

import com.tangpo.douban.fragment.BaseFragment;
import com.tangpo.douban.widget.MyViewPager;
import com.tangpo.douban.widget.ViewPagerScroller;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TabHost mTabHost;
    private MyViewPager mViewPager;
    private TabsAdapter mTabsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       
        mViewPager = (MyViewPager) findViewById(R.id.pager);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);

        View tab_item1 = LayoutInflater.from(this).inflate(R.layout.main_tab_home, null);
        View tab_item2 = LayoutInflater.from(this).inflate(R.layout.main_tab_feed, null);
        View tab_item3 = LayoutInflater.from(this).inflate(R.layout.main_tab_subject, null);
        View tab_item4 = LayoutInflater.from(this).inflate(R.layout.main_tab_quanzi, null);
        View tab_item5 = LayoutInflater.from(this).inflate(R.layout.main_tab_profile, null);

        mTabsAdapter.addTab(mTabHost.newTabSpec("tab1").setIndicator(tab_item1), com.tangpo.douban.fragment.HomeFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("tab2").setIndicator(tab_item2), com.tangpo.douban.fragment.FeedFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("tab3").setIndicator(tab_item3), com.tangpo.douban.fragment.SubjectFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("tab4").setIndicator(tab_item4), com.tangpo.douban.fragment.QuanziFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("tab5").setIndicator(tab_item5), com.tangpo.douban.fragment.ProfileFragment.class, null);

        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        } else {
            mTabHost.setCurrentTab(0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mTabHost != null) {
            outState.putString("tab", mTabHost.getCurrentTabTag());
        }
    }

    private class TabsAdapter extends FragmentStatePagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

        Context context;
        TabHost tabHost;
        ViewPager viewPager;
        ArrayList<TabInfo> tabs = new ArrayList<TabInfo>();

        private class TabInfo {
            String tag;
            Class<?> clss;
            Bundle args;
            BaseFragment fragment;

            TabInfo(String _tag, Class<?> _class, Bundle _args) {
                tag = _tag;
                clss = _class;
                args = _args;
            }

            public BaseFragment getFragment() {
                if (fragment == null) {
                    fragment = (BaseFragment) Fragment.instantiate(context, clss.getName(), args);
                }
                return fragment;
            }
        }

        private class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context context;

            public DummyTabFactory(Context context) {
                this.context = context;
            }

            @Override
            public View createTabContent(String tag) {
                return new View(context);
            }
        }

        public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager viewPager) {
            super(activity.getSupportFragmentManager());
            FragmentManager.enableDebugLogging(true);
            this.context = activity;
            this.tabHost = tabHost;
            this.viewPager = viewPager;
            try {
                Field mField = ViewPager.class.getDeclaredField("mScroller");
                mField.setAccessible(true);
                Scroller scroller = new ViewPagerScroller(MainActivity.this);
                mField.set(mViewPager, scroller);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tabHost.setOnTabChangedListener(this);
            viewPager.setAdapter(this);
            viewPager.setOnPageChangeListener(this);
            // viewPager预加载0页
            viewPager.setOffscreenPageLimit(0);
        }

        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
            tabSpec.setContent(new DummyTabFactory(context));
            String tag = tabSpec.getTag();
            TabInfo info = new TabInfo(tag, clss, args);
            tabs.add(info);
            tabHost.addTab(tabSpec);
            notifyDataSetChanged();
        }

        @Override
        public void onTabChanged(String tabId) {
            int position = tabHost.getCurrentTab();
            viewPager.setCurrentItem(position);
            for (int i = 0; i < tabs.size(); i++) {
                if (tabs.get(i).fragment != null) {
                    tabs.get(i).fragment.onFragmentShowChanged(position == i);
                }
            }
        }

        @Override
        public Fragment getItem(int position) {
            TabInfo info = tabs.get(position);
            return info.getFragment();
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public void onPageSelected(int position) {
            TabWidget widget = tabHost.getTabWidget();
            int oldFocusability = widget.getDescendantFocusability();
            widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            tabHost.setCurrentTab(position);
            widget.setDescendantFocusability(oldFocusability);
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {}

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}
    }

}
