package ctec.sound.controller;

import android.app.Activity;
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
		playButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				soundPlayer.start();

			}
		});

		pauseButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				soundPlayer.pause();

			}
		});

		stopButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				soundPlayer.stop();

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
	}

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
				currentPosition = soundPlayer.getDuration();
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
