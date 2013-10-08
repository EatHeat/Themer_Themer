package tk.eatheat.themer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceFragment;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//The Main Activity that loads the app screens

public class MainActivity extends Activity {
	
	private final static int BUFFER_SIZE = 1024;

	SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		//Don't mess with this part
		
        try {
            AssetManager assetFiles = getAssets();
            String[] files = assetFiles.list("zip");
            InputStream in = null;
            OutputStream out = null;

            for (int i = 0; i < files.length; i++) {
                    in = assetFiles.open("zip/" + files[i]);
                    out = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + "/MyColorScreen/Themer/Exported/zip/"
                                    + files[i]);
                    copyAssetFiles(in, out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }		

      //Don't mess with this part either
        
        try {
            AssetManager assetFiles = getAssets();
            String[] files = assetFiles.list("thumbs");
            InputStream in = null;
            OutputStream out = null;

            for (int i = 0; i < files.length; i++) {
                    in = assetFiles.open("thumbs/" + files[i]);
                    out = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + "/MyColorScreen/Themer/Exported/thumbs/"
                                    + files[i]);
                    copyAssetFiles(in, out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new Fragment();
			switch (position) {
			case 0:
			return fragment = new Fragment1();
			case 1:
			return fragment = new Fragment2();
			case 2:
			return fragment = new Fragment3();			
			case 3:
			return fragment = new Fragment4();			
			default:break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return 4;
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
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			}
			return null;
		}
	}

	//The first screen showing different options
	
	public static class Fragment1 extends PreferenceFragment {

		public Fragment1() {
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);  
	        addPreferencesFromResource(R.xml.prefs);
		}

	}
	
	//Second screen showing the first screenshot
	
	public static class Fragment2 extends Fragment {

		public Fragment2() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment1,
					container, false);
			return rootView;
		}
	}
	
	//Third screen showing the second screenshot
	
	public static class Fragment3 extends Fragment {

		public Fragment3() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment2,
					container, false);
			return rootView;
		}
	}
	
	//Fourth screen showing the third screenshot

	public static class Fragment4 extends Fragment {

		public Fragment4() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment3,
					container, false);
			return rootView;
		}
	}
	
    private static void copyAssetFiles(InputStream in, OutputStream out) {
        try {

            byte[] buffer = new byte[BUFFER_SIZE];
            int read;

            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
