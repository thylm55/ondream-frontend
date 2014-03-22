package com.example.ondream.activities;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.ondream.Constant;
import com.example.ondream.R;
import com.example.ondream.models.MUser;

/**
 * Base activity for another activities in app
 * @author May
 *
 */
@SuppressLint("NewApi")
public class BaseActivity extends SherlockFragmentActivity {
	// TAG
	public static final String TAG = "BaseActivity";
	
	// Fragment to show main content
	protected Fragment mainContent;
	
	/**
	 * Settings for image loading
	 */
	protected ImageLoader imageLoader;
	
	/**
	 * Action bar
	 */
	protected ActionBar actionBar;
	
	/**
	 * Flag to check if it's search mode or not
	 */
	protected boolean isSearchMode = false;
	
	/**
	 * Value to save name of current fragment
	 */
	private String currentFragment;
	
	private MUser mCurrentUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		
		//setImageLoader();
		
		actionBar = getSupportActionBar();
	}
	
	/**
	 * Initialize image loader
	 */
	/*private void setImageLoader() {
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(getBaseContext()));
	}*/
	
	/**
	 * Get Image loader
	 * @return
	 */
	public ImageLoader getImageLoader() {
		return imageLoader;
	}
	
	/**
	 * Listener for error response
	 * @return
	 */
	public Response.ErrorListener getErrorListener() {
		return new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				showProgressBar(false);
				handleOnError(arg0);
			}
		};
	}
	
	/**
	 * Handle attitude on request data got error
	 */
	public void handleOnError(VolleyError arg0) {
		String message = arg0.toString();
		//LogUtility.e(TAG, Constant.ERROR+message);
		if (message.contains(Constant.MESSAGE_NETWORK_UNREACHABLE)) {
			Toast.makeText(getBaseContext(), Constant.MESSAGE_NETWORK_UNREACHABLE, Toast.LENGTH_SHORT).show();
		} else if (message.contains(Constant.REQUEST_TIMEOUT)) {
			Toast.makeText(getBaseContext(), Constant.MESSAGE_REQUEST_TIMEOUT, Toast.LENGTH_SHORT).show();
		} else {
			//Log.e(TAG, arg0.getMessage());
			Toast.makeText(getBaseContext(), Constant.MESSSAGE_SERVER_ERROR, Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Method to hide soft keyboard
	 */
	public void hideSoftKeyboard() {
		InputMethodManager inputMethodManager = (InputMethodManager) this
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		if (this.getCurrentFocus() != null)
			inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus()
					.getWindowToken(), 0);
	}
	
	/**
	 * Set current fragment
	 * @param fragmentTag
	 */
	public void setCurrentFragment(String fragmentTag) {
		this.currentFragment = fragmentTag;
	}
	
	/**
	 * Get current fragment
	 * @return
	 */
	public String getCurrentFragment() {
		return currentFragment;
	}
	
	/**
	 * Show the progress bar
	 * @param visible
	 */
	public void showProgressBar(boolean visible) {
		setProgressBarIndeterminateVisibility(visible);
	}
	
	protected void getOverflowMenu() {
		 
	    try {
	        ViewConfiguration config = ViewConfiguration.get(this);
	         Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	         if (menuKeyField != null) {
	             menuKeyField.setAccessible(true);
	             menuKeyField.setBoolean(config, false);
	         }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Switch content of main view when user have just choosed a different kind
	 * of category from Left Menu
	 * 
	 * @param fragment
	 */
	public void switchContent(Fragment fragment, boolean addToBackstack) {
		if (fragment != null) {
			actionBar.setIcon(R.drawable.ic_logo);
			mainContent = fragment;
			if (addToBackstack) {
				getSupportFragmentManager()
						.beginTransaction()
						// .setCustomAnimations(R.anim.slide_in_right,
						// R.anim.slide_out_left, R.anim.slide_in_left,
						// R.anim.slide_out_right)
						/*.setCustomAnimations(android.R.anim.fade_in,
								android.R.anim.fade_out,
								android.R.anim.fade_in, android.R.anim.fade_out)*/
						.replace(R.id.content_frame, fragment)
						.addToBackStack(null).commitAllowingStateLoss();

			} else {
				getSupportFragmentManager()
				.beginTransaction()
				// .setCustomAnimations(R.anim.slide_in_right,
				// R.anim.slide_out_left, R.anim.slide_in_left,
				// R.anim.slide_out_right)
				/*.setCustomAnimations(android.R.anim.fade_in,
						android.R.anim.fade_out,
						android.R.anim.fade_in, android.R.anim.fade_out)*/
				.replace(R.id.content_frame, fragment)
				.commitAllowingStateLoss();
			}

			actionBar.setDisplayHomeAsUpEnabled(addToBackstack);
		}

	}

	public void switchContent(Fragment fragment, boolean addToBackstack,
			boolean clearBackstack) {
		if (fragment != null) {
			actionBar.setIcon(R.drawable.ic_logo);
			if (clearBackstack) {
				getSupportFragmentManager().popBackStack(null,
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
			mainContent = fragment;
			if (addToBackstack) {
				getSupportFragmentManager()
						.beginTransaction()
						// .setCustomAnimations(R.anim.slide_in_right,
						// R.anim.slide_out_left, R.anim.slide_in_left,
						// R.anim.slide_out_right)
						/*.setCustomAnimations(android.R.anim.fade_in,
								android.R.anim.fade_out,
								android.R.anim.fade_in, android.R.anim.fade_out)*/
						.replace(R.id.content_frame, fragment)
						.addToBackStack(null).commitAllowingStateLoss();

			} else {
				getSupportFragmentManager()
				.beginTransaction()
				// .setCustomAnimations(R.anim.slide_in_right,
				// R.anim.slide_out_left, R.anim.slide_in_left,
				// R.anim.slide_out_right)
				/*.setCustomAnimations(android.R.anim.fade_in,
						android.R.anim.fade_out,
						android.R.anim.fade_in, android.R.anim.fade_out)*/
				.replace(R.id.content_frame, fragment)
				.commitAllowingStateLoss();
			}

			actionBar.setDisplayHomeAsUpEnabled(addToBackstack);
		}
	}

	/**
	 * Switch content with fragment tag
	 * 
	 * @param fragment
	 * @param addToBackstack
	 * @param tag
	 */
	public void switchContent(Fragment fragment, boolean addToBackstack,
			String tag) {
		if (fragment != null) {
			actionBar.setIcon(R.drawable.ic_logo);
			mainContent = fragment;
			if (addToBackstack) {
				getSupportFragmentManager()
						.beginTransaction()
						// .setCustomAnimations(R.anim.slide_in_right,
						// R.anim.slide_out_left, R.anim.slide_in_left,
						// R.anim.slide_out_right)
						/*.setCustomAnimations(android.R.anim.fade_in,
								android.R.anim.fade_out,
								android.R.anim.fade_in, android.R.anim.fade_out)*/
						.replace(R.id.content_frame, fragment, tag)
						.addToBackStack(null).commitAllowingStateLoss();

			} else {
				getSupportFragmentManager()
				.beginTransaction()
				// .setCustomAnimations(R.anim.slide_in_right,
				// R.anim.slide_out_left, R.anim.slide_in_left,
				// R.anim.slide_out_right)
				/*.setCustomAnimations(android.R.anim.fade_in,
						android.R.anim.fade_out,
						android.R.anim.fade_in, android.R.anim.fade_out)*/
				.replace(R.id.content_frame, fragment, tag)
				.commitAllowingStateLoss();
			}

			actionBar.setDisplayHomeAsUpEnabled(addToBackstack);
		}
	}

	public void switchContent(Fragment fragment, boolean addToBackstack,
			boolean clearBackstack, String tag) {
		if (fragment != null) {
			actionBar.setIcon(R.drawable.ic_logo);
			if (clearBackstack) {
				getSupportFragmentManager().popBackStack(null,
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
			mainContent = fragment;
			if (addToBackstack) {
				getSupportFragmentManager()
						.beginTransaction()
						// .setCustomAnimations(R.anim.slide_in_right,
						// R.anim.slide_out_left, R.anim.slide_in_left,
						// R.anim.slide_out_right)
						/*.setCustomAnimations(android.R.anim.fade_in,
								android.R.anim.fade_out,
								android.R.anim.fade_in, android.R.anim.fade_out)*/
						.replace(R.id.content_frame, fragment, tag)
						.addToBackStack(null).commitAllowingStateLoss();

			} else {
				getSupportFragmentManager()
				.beginTransaction()
				// .setCustomAnimations(R.anim.slide_in_right,
				// R.anim.slide_out_left, R.anim.slide_in_left,
				// R.anim.slide_out_right)
				/*.setCustomAnimations(android.R.anim.fade_in,
						android.R.anim.fade_out,
						android.R.anim.fade_in, android.R.anim.fade_out)*/
				.replace(R.id.content_frame, fragment, tag)
				.commitAllowingStateLoss();
			}

			actionBar.setDisplayHomeAsUpEnabled(addToBackstack);
		}
	}
	
	
	public MUser getCurrentUser() {
		return mCurrentUser;
	}
	
	public void setCurrentUser(MUser mCurrentUser) {
		this.mCurrentUser = mCurrentUser;
	}
}
