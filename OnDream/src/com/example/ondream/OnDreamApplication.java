package com.example.ondream;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.example.ondream.Constant.Config;

public class OnDreamApplication extends Application {
	public static final String MESSAGE_ID_RECEIVED_KEY = "com.example.sciencesnewstoday.MESSAGE_ID_RECEIVED";
	
	@SuppressWarnings("unused")
	@Override
	public void onCreate() {
		super.onCreate();
		
		if (Config.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
		}
		
		initNetworking();
		initImageLoader(getApplicationContext());
	}
	
	private void initNetworking() {
		OnDreamVolley.init(this);
	}
	
	public static void initImageLoader(Context context) {
		//L.disableLogging();
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		/*ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);*/
	}

	@Override
	public void onLowMemory() {
		// clear all memory cached images when system is in low memory
		// note that you can configure the max image cache count, see
		// CONFIGURATION
//		ImageLoader.getInstance().clearDiscCache();
//		ImageLoader.getInstance().clearMemoryCache();
	}
}