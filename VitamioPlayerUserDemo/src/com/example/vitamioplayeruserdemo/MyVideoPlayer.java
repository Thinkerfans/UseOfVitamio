package com.example.vitamioplayeruserdemo;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;



public class MyVideoPlayer extends Activity {
	
	private String path = null;
	private VideoView mVideoView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
			return;
		
		path = getIntent().getExtras().getString("filepath");
	
		
		setContentView(R.layout.vitamio_videoview);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		mVideoView.setVideoPath(path);
		mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub				
				MyVideoPlayer.this.finish();
				
				MyVideoPlayer.this.overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
			}
		});
		
	}
	
	

	@Override
	protected void onPause() {
		System.out.println("onConfigurationChanged ");
		super.onPause();
	}



	public void onConfigurationChanged(Configuration newConfig) {
		System.out.println("onConfigurationChanged ");
		if (mVideoView != null)
			mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
		super.onConfigurationChanged(newConfig);
	}
}
