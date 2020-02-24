package gov.unsc.lupo.lightsaber;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean on = false;
    private MediaPlayer onMp, offMp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onMp = MediaPlayer.create(this, R.raw.saber_on);
        offMp = MediaPlayer.create(this, R.raw.saber_crash);
    }

    public void clickOn(View view) {
        if (on) {
            playOffSound();
            on = false;
        } else {
            playOnSound();
            on = true;
        }
    }

    private void playOnSound() {
        onMp.start();
    }

    private void playOffSound() {
        offMp.start();
    }

}
