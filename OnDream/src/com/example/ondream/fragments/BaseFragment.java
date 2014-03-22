package com.example.ondream.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.ondream.activities.BaseActivity;
import com.example.ondream.activities.ContentActivity;

/**
 * Base activity for another activities in app
 * @author May
 *
 */
@SuppressLint("NewApi")
public class BaseFragment extends SherlockFragment {
	// TAG
	public static final String TAG = "BaseFragment";
	
	/**
	 * Settings for image loading
	 */
	protected ImageLoader imageLoader;
	//protected DisplayImageOptions options;
	
	/**
	 * Action bar
	 */
	protected ActionBar actionBar;
	
	/**
	 * value for hold current page
	 */
	//protected int page = Constant.FIRST_PAGE;
	
	/**
	 * Context
	 */
	protected Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = getSherlockActivity();
		
		setImageLoader();
				
		// Configure image loader options
		/*options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.default_thumbnail)
				.showImageForEmptyUri(R.drawable.default_thumbnail)
				.showImageOnFail(R.drawable.default_thumbnail)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.cacheInMemory(true).cacheOnDisc(true).build();*/
				
		actionBar = getSherlockActivity().getSupportActionBar();
	}
	
	/**
	 * Get image loader value from base activity
	 */
	private void setImageLoader() {
		if (mContext == null) {
			return;
		}
		
		if (mContext instanceof BaseActivity) {
			BaseActivity activity = (BaseActivity) mContext;
			imageLoader = activity.getImageLoader();
		}
	}
	
	/**
	 * Listener for error response
	 * @return
	 */
	protected Response.ErrorListener getErrorListener() {
		return new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				showProgressBar(false);
				handleOnError(arg0);
			}
		};
	}
	
	/**
	 * Handle attitude when request data got error
	 */
	protected void handleOnError(VolleyError arg0) {
		if (mContext == null) {
			return;
		}

		if (mContext instanceof BaseActivity) {
			BaseActivity activity = (BaseActivity) mContext;
			activity.handleOnError(arg0);
		}
	}
	
	/**
	 * Show page detail for each row is clicked
	 * 
	 * @param fragment
	 */
	protected void goToDetailFragment(Fragment fragment) {
		if (mContext == null) {
			return;
		}

		if (mContext instanceof ContentActivity) {
			ContentActivity activity = (ContentActivity) mContext;
			activity.switchContent(fragment, true);
		}
	}
	
	/**
	 * Change the state of left menu (drawer)
	 * @param isEnable
	 * @param isShowIcon
	 */
	protected void changeStateLeftMenu(boolean isEnable, boolean isShowIcon) {
		if (mContext == null) {
			return;
		}
		
		if (mContext instanceof ContentActivity) {
			ContentActivity activity = (ContentActivity) mContext;
			activity.changeStateActionBarDrawer(isEnable, isShowIcon);
		}
	}
	
	
	/**
	 * Set title for action bar
	 * @param title
	 */
	protected void setActionBarTitle(String title) {
		if (mContext == null) {
			return;
		}
		
		if (mContext instanceof ContentActivity) {
			ContentActivity activity = (ContentActivity) mContext;
			activity.setActionBarTitle(title);
		}
	}
	
	/**
	 * Set tag for fragment
	 * @param tag
	 */
	protected void setFragmentTag(String tag) {
		if (mContext == null) {
			return;
		}
		
		if (mContext instanceof BaseActivity) {
			BaseActivity activity = (BaseActivity) mContext;
			activity.setCurrentFragment(tag);
		}
	}
	
	/**
	 * Show the progress bar
	 * @param visible
	 */
	protected void showProgressBar(boolean visible) {
		if (mContext == null) {
			return;
		}
		
		if (mContext instanceof BaseActivity) {
			BaseActivity activity = (BaseActivity) mContext;
			activity.showProgressBar(visible);
		}
	}
}
