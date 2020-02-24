package gov.unsc.lupo.lightsaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean on = false;
    private SensorManager sensorManager;
    private Sensor sensor;

    private MediaPlayer onMp, offMp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
