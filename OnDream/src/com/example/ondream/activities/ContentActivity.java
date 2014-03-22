package com.example.ondream.activities;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.actionbarsherlock.widget.SearchView.OnCloseListener;
import com.actionbarsherlock.widget.SearchView.OnQueryTextListener;
import com.example.ondream.Constant;
import com.example.ondream.OnDreamApplication;
import com.example.ondream.R;
import com.example.ondream.fragments.FriendsFragment;
import com.example.ondream.fragments.NewsFeedFragment;
import com.example.ondream.fragments.SigninFragment;

/**
 * Content activity for displaying fragments
 * @author May
 *
 */
@SuppressLint("NewApi")
public class ContentActivity extends BaseActivity {
	// TAG
	public static final String TAG = "ContentActivity";
	
	// MAIN CONTENT
	public static final String MAIN_CONTENT = "mContent";
	
	// Pending message
	@SuppressWarnings("unused")
	private String pendingMessageId;
	
	/**
	 * Drawer for left menu
	 */
	private ActionBarDrawerToggle drawerToggle;
	private DrawerLayout drawerLayout;
	
	// List view in left menu
	//private LeftMenuAdapter adapterLeftMenu;
	private ListView lvLeftMenu;
	
	// Current category title
	private int currentCategoryId = 0;
	private String currentTitle;
	
	// For search
	private SearchView searchView;
	private String query;
	
	
	public ContentActivity() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar ab = getActionBar(); 
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#a8b8d0"));     
        ab.setBackgroundDrawable(colorDrawable);
		// Settings
		currentTitle = getString(R.string.app_name);
		setContentView(R.layout.activity_main);
		getOverflowMenu();
		findViews();
		
		if (savedInstanceState != null) {
			mainContent = getSupportFragmentManager().getFragment(savedInstanceState, MAIN_CONTENT);
			setContentForAboveView(mainContent, MAIN_CONTENT);
		} else {
			this.setPendingMessageIdFromIntent(getIntent());
		}
		
		mainContent = new SigninFragment();
		switchContent(mainContent, false, true, MAIN_CONTENT);
		
		// Get data from server
		getLeftMenuData();
	}
	
	/**
	 * Fill all views
	 */
	private void findViews() {
		/**
		 * Find views for drawer
		 */
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		/**
		 * Find views & setting up for left menu
		 */
		lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
		//adapterLeftMenu = new LeftMenuAdapter(getBaseContext(), R.layout.row_left_menu, listItemLeftMenu);
		//lvLeftMenu.setAdapter(adapterLeftMenu);
		//lvLeftMenu.setOnItemClickListener(this);
		
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 
				R.drawable.ic_drawer, 
				R.string.app_name,
				R.string.app_name
		){
			public void onDrawerClosed(View view) {
				actionBar.setTitle(currentTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                //invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
            	//actionBar.setTitle(getString(R.string.choose_category));
                // calling onPrepareOptionsMenu() to hide action bar icons
                // invalidateOptionsMenu();
            }
		};
		drawerLayout.setDrawerListener(drawerToggle);
		drawerLayout.closeDrawer(lvLeftMenu);
	}
	
	/**
	 * Tries to show a message if the pendingMessageId is set. Clears the
	 * pendingMessageId after.
	 */
	/*private void showPendingMessageId() {
		
	}*/
	
	/**
	 * Sets the pending message by looking for an id in the intent's extra with
	 * key <code>RichPushApplication.MESSAGE_ID_RECEIVED_KEY</code>
	 * 
	 * @param intent
	 *            Intent to look for a rich push message id
	 */
	private void setPendingMessageIdFromIntent(Intent intent) {
		pendingMessageId = intent.getStringExtra(OnDreamApplication.MESSAGE_ID_RECEIVED_KEY);
	}
	
	private void setContentForAboveView(Fragment fragment, String tag) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mainContent, tag)
				.commitAllowingStateLoss();

	}
	
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //drawerToggle.syncState();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    	
    	/*searchView = new SearchView(getSupportActionBar().getThemedContext());
    	//searchView.setQueryHint(getString(R.string.hint_search));

    	menu.add(Menu.NONE, Constant.ID_MENU_FEEDBACK, 0, Constant.MENU_FEEDBACK)
    		.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    	
    	menu.add(Menu.NONE, Constant.ID_MENU_TOUR, 0, Constant.MENU_TOUR)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    	
    	menu.add(Menu.NONE, Constant.ID_MENU_SEARCH, 1, Constant.MENU_SEARCH)
        	.setIcon(R.drawable.abs__ic_search)
        	.setActionView(searchView)
        	.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    	
    	*//**
    	 * Listeners for search icon
    	 *//*
    	searchView.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String q) {
				isSearchMode = true;
				hideSoftKeyboard();
				
				query = q;
				//launchListArticlesFragment(true, 0);
				
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
			
		});
    	
    	searchView.setOnCloseListener(new OnCloseListener() {
			
			@Override
			public boolean onClose() {
				isSearchMode = false;
				
				return false;
			}
		});*/

    	return super.onCreateOptionsMenu(menu);
    }

	
	@Override
	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
		/*LogUtility.e(TAG, getCurrentFragment());
		if (item.getItemId() == android.R.id.home) {
			if (getCurrentFragment().contains(Constant.TAG_FRAGMENT_LIST_ARTICLES)) {
		        if (!getCurrentFragment().contains(Constant.ON_SEARCH_MODE)) {
		        	if (drawerLayout.isDrawerOpen(lvLeftMenu)) {
			            drawerLayout.closeDrawer(lvLeftMenu);
			        } else {
			            drawerLayout.openDrawer(lvLeftMenu);
			        }
		        }
			}
	    } else if (item.getItemId() == Constant.ID_MENU_FEEDBACK) {
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.popup_feedback, null, false);

			// launch feedback popup window
			new FeedbackPopupWindow(this,
					WindowManager.LayoutParams.MATCH_PARENT,
					WindowManager.LayoutParams.MATCH_PARENT, view, getWindow().getDecorView().getRootView());
	    } else if (item.getItemId() == Constant.ID_MENU_TOUR) {
	    	if (getCurrentFragment() == null) {
	    		switchContent(new TourFragment(), true, MAIN_CONTENT);
	    	} else {
		    	if (!getCurrentFragment().equals(Constant.TAG_FRAGMENT_TOUR)) {
			    	switchContent(new TourFragment(), true, MAIN_CONTENT);	
		    	}
	    	}

	    }*/

	    return super.onOptionsItemSelected(item);
	}
	
	/***
     * Called when invalidateOptionsMenu() is triggered
     */
    /*@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        //boolean drawerOpen = drawerLayout.isDrawerOpen(lvLeftMenu);
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }*/
	
	/**
	 * Get all categories from server
	 */
	private void getLeftMenuData() {
		/*showProgressBar(true);
		SciencesNewsTodayVolley.getKhoahocClient().getCategories(getLeftMenuListener(), getErrorListener());*/
	}
	
    /**
     * Custom adapter for left menu
     * @author May
     *
     */
    /*private class LeftMenuAdapter extends ArrayAdapter<MItemLeftMenu> {
		
		private Context context;
		private List<MItemLeftMenu> articles;
		private int resource;

		public LeftMenuAdapter(Context context, int resource,
				List<MItemLeftMenu> objects) {
			super(context, resource, objects);
			this.context = context;
			this.articles = objects;
			this.resource = resource;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View row = convertView;
			ItemLeftMenuHolder holder;
			MItemLeftMenu article = articles.get(position);
			
			if (row == null) {
				row = LayoutInflater.from(context).inflate(resource, parent, false);
				
				holder = new ItemLeftMenuHolder();
				
				holder.ivThumbnail = (ImageView) row.findViewById(R.id.iv_thumbnail);
				holder.tvTitle = (TextView) row.findViewById(R.id.tv_title);
				
				holder.tvTitle.setTypeface(FontUtility.get(context, FontUtility.ROBOTO_BOLD));
				
				row.setTag(holder);
			} else {
				holder = (ItemLeftMenuHolder) row.getTag();
			}
			
			imageLoader.displayImage(article.getIconUrl(), holder.ivThumbnail, optionsLeftMenu);
			holder.tvTitle.setText(article.getName());
			
			return row;
		}
		
		public class ItemLeftMenuHolder {
			private ImageView ivThumbnail;
			private TextView tvTitle;
		}
	}*/

	/*@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int oldCategoryId = currentCategoryId;
		
		currentCategoryId = listItemLeftMenu.get((int) arg3).getCategoryId();
		if (oldCategoryId != currentCategoryId) {
			launchListArticlesFragment(false, (int) arg3);
		}
		
		drawerLayout.closeDrawer(lvLeftMenu);
	}*/
	
	/**
	 * Launch list articles fragment with category
	 * @param isSearchMode
	 * @param position
	 */
	/*private void launchListArticlesFragment(boolean isSearchMode, int position) {
		LogUtility.e(TAG, "launchListArticlesFragment");
		if (!isSearchMode) {
			mainContent = new ListArticlesFragment(listItemLeftMenu.get(position));
			switchContent(mainContent, false, true, MAIN_CONTENT);
		} else {
			currentCategoryId = Constant.UNREACHABLE_PAGE;
			mainContent = new ListArticlesFragment(query);
			switchContent(mainContent, true, MAIN_CONTENT);
		}
	}*/
	
	/**
	 * Set the state of action bar drawer (is enable/disable, is show/hide icon)
	 * @param isEnable
	 * @param isShowIcon
	 */
	public void changeStateActionBarDrawer(boolean isEnable, boolean isShowIcon) {
		if (isEnable) {
			drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
			drawerToggle.setDrawerIndicatorEnabled(isShowIcon);
		} else {
			drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
			drawerToggle.setDrawerIndicatorEnabled(false);
		}
	}
	
	/**
	 * Set the title of action bar
	 * @param title
	 */
	public void setActionBarTitle(String title) {
		currentTitle = title;
		actionBar.setTitle(currentTitle);
	}
}
