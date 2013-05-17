package fr.mysocialfeed.supportingfiles;

import java.util.List;
import java.util.Locale;
import java.util.Vector;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	private SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;
	private List<Fragment> fragments = new Vector<Fragment>();
	public static boolean isConnected = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);		
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// Fragments list (viewpager)
        fragments.add(Fragment.instantiate(this,PageFilters.class.getName()));
        fragments.add(Fragment.instantiate(this,PageHome.class.getName()));
        fragments.add(Fragment.instantiate(this,PageSetting.class.getName()));
   
        this.mSectionsPagerAdapter = new SectionsPagerAdapter(super.getSupportFragmentManager(), fragments);
		
		// Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) super.findViewById(R.id.pager);
		mViewPager.setAdapter(this.mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		private final List<Fragment> fragments;

		public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
            this.fragments = fragments;
		}
        
		@Override
		public Fragment getItem(int position) {
            return this.fragments.get(position);
		}

		@Override
		public int getCount() {
            return this.fragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	public static class PageFilters extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
        	
        		if( isConnected == true )
        			return inflater.inflate(R.layout.filters, container, false);
        		else
        			return inflater.inflate(R.layout.signin, container, false);
        }
	}
	
	public static class PageHome extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {

        		if( isConnected == true )
        			return inflater.inflate(R.layout.home, container, false);
        		else
        			return inflater.inflate(R.layout.signin, container, false);
        }
	}
	
	public static class PageSetting extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
        	
        	if( isConnected == true )
    			return inflater.inflate(R.layout.setting, container, false);
    		else
    			return inflater.inflate(R.layout.signin, container, false);
        }
	}
    
    public void onLoginClick(View v) {
    	isConnected = true;
    }

}

