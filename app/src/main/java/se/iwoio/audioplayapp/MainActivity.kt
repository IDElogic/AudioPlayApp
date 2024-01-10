package se.iwoio.audioplayapp

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import se.iwoio.audioplayapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        binding.seekBar.max = maxVolume

        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        binding.seekBar.progress = currentVolume

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        binding.imgPlay.setOnClickListener {
            mediaPlayer = MediaPlayer.create(
                this@MainActivity,
                R.raw.demo3
            )

            mediaPlayer.setOnPreparedListener(this@MainActivity)
        }

        binding.imgStop.setOnClickListener {
            mediaPlayer.stop()
        }

        binding.imgFastForward.setOnClickListener {
            mediaPlayer.seekTo(40000)
        }

        binding.imgForward.setOnClickListener {
            mediaPlayer.deselectTrack(1)

        }

    }

    override fun onPrepared(mp: MediaPlayer) {
            mediaPlayer.start()
    }

        override fun onStop() {
        mediaPlayer.stop()
        super.onStop()
    }
}


