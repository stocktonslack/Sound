package ctec.sound.controller;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class SoundActivity extends Activity implements Runnable
{
	private Button playButton;
	private Button stopButton;
	private Button pauseButton;
	private SeekBar soundSeekBar;
	private Button videoButton;
	private Thread soundThread;
	private MediaPlayer soundPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound);

		playButton = (Button) findViewById(R.id.playButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		pauseButton = (Button) findViewById(R.id.pauseButton);
		soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);
		videoButton = (Button) findViewById(R.id.videoButton);

		soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.tequilaremix);

		setupListeners();

		soundThread = new Thread(this);
		soundThread.start();

	}

	private void setupListeners()
	{
		
		/**
		 * When the playButton is clicked the sound/audio file will begin to play. 
		 */
		playButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				soundPlayer.start();

			}
		});

		/**
		 * When the pauseButton is clicked the sound/audio file will pause.
		 */
		pauseButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				soundPlayer.pause();

			}
		});

		/**
		 * When the stopButton is clicked the sound/audio file will stop.
		 */
		stopButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View currentView)
			{
				soundPlayer.stop();
				soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.tequilaremix);
			}
		});

		soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				if (fromUser)
				{
					soundPlayer.seekTo(progress);
				}
			}
		});

		videoButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View currentView)
			{
				Intent otherScreenIntent = new Intent(currentView.getContext(), VideoActivity.class);
				startActivityForResult(otherScreenIntent, 0);
			}

		}

		);
	}

	/**
	 * this is required since we are implementing runnable
	 */
	@Override
	public void run()
	{
		int currentPosition = 0;
		int soundTotal = soundPlayer.getDuration();
		soundSeekBar.setMax(soundTotal);

		while (soundPlayer != null && currentPosition < soundTotal)
		{
			try
			{
				Thread.sleep(50);
				currentPosition = soundPlayer.getCurrentPosition();
			}
			catch (InterruptedException soundException)
			{
				return;
			}
			catch (Exception otherException)
			{
				return;
			}
			soundSeekBar.setProgress(currentPosition);

		}
	}
}
