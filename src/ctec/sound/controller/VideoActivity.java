package ctec.sound.controller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class VideoActivity extends Activity
{
	private VideoView myPlayer;
	private Button returnButton;
	private MediaController myVideoController;
	private Uri videoLocation;
	private Button newVidButton;
	private Uri newVideoLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);

		myPlayer = (VideoView) findViewById(R.id.myPlayer);
		returnButton = (Button) findViewById(R.id.returnButton);
		newVidButton = (Button) findViewById(R.id.newVidButton);

		videoLocation = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.stocktonyoyo);
		myVideoController = new MediaController(this);
		newVideoLocation = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.stocktonyoyo2);
		
		// prepare the video
		setupMedia();
		setupListeners();

	}

	private void setupMedia()
	{
		myPlayer.setMediaController(myVideoController);
		myPlayer.setVideoURI(videoLocation);
	}

	private void setupListeners()
	{
		returnButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View currentView)
			{
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);
				finish();
			}

		});
		
		newVidButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View currentView)
			{
				
				myPlayer.setVideoURI(newVideoLocation);
			}
		});
	}
}
