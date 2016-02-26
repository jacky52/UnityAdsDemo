package com.ads.gamePintu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.ads.gamePintu.view.GamePintuLayout;
import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.demo.R;
import com.unity3d.ads.android.UnityAds;

public class MainActivity extends Activity {

	GamePintuLayout mGameView;
	private static MainActivity mActivity;
	private static boolean fetchCompleted;
	private static boolean videoCompleted;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// mActivity = new MainActivity();
		mGameView = (GamePintuLayout) findViewById(R.id.id_gameview);
		getInstance();
		try {

			UnityAds.setDebugMode(true);
			UnityAds.setTestMode(true);
			// UnityAds.changeActivity(this);
			UnityAds.init(this, "1040403", new IUnityAdsListener() {

				@Override
				public void onFetchCompleted() {
					// TODO Auto-generated method stub
					System.out.println("there are campaigns available");
					setFetchCompleted(true);
				}

				@Override
				public void onFetchFailed() {
					// TODO Auto-generated method stub
					System.out.println(" no inventory available");
					setFetchCompleted(false);
				}

				@Override
				public void onHide() {
					// TODO Auto-generated method stub
					System.out.println(" Called when the Unity Ads is closed by the user");
				}

				@Override
				public void onShow() {
					// TODO Auto-generated method stub
					System.out.println("Called when the Unity Ads is shown to the user");
				}

				@Override
				public void onVideoCompleted(String itemKey, boolean skipped) {
					// TODO Auto-generated method stub
					System.out.println("onVideoCompleted Called when the video playback is completed. This step also notifies you that the user should be rewarded.");
					System.out.println("onVideoCompleted skipped=" + skipped);
					System.out.println("onVideoCompleted itemKey=" + itemKey);
					System.out.println("onVideoCompleted getDefaultRewardItemKey="
									+ UnityAds.getDefaultRewardItemKey());

					// UnityAds.setRewardItemKey(UnityAds.getDefaultRewardItemKey());
					// UnityAds.setRewardItemKey(itemKey);
//					setVideoCompleted(true);
//					  System.out.println("videoCompleted= "+MainActivity.getVideoCompleted());
					 mGameView.nextLevel();
				}

				@Override
				public void onVideoStarted() {
					// TODO Auto-generated method stub
					System.out.println("Called when video playback is initiated by the user");
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static MainActivity getInstance() {
		if (null == mActivity) {
			mActivity = new MainActivity();
		}
		return mActivity;
	}

	@Override
	public void onResume() {
		super.onResume();
		UnityAds.changeActivity(this);
	}

	public static void setFetchCompleted(boolean completed) {
		fetchCompleted = completed;
	}

	public static boolean getFetchCompleted() {
		return fetchCompleted;
	}
	

	public static void setVideoCompleted(boolean completed) {
		videoCompleted = completed;
	}

	public static boolean getVideoCompleted() {
		return videoCompleted;
	}
}
