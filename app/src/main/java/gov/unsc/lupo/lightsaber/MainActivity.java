package gov.unsc.lupo.lightsaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private boolean on = false;
    private float lastX, lastY, lastZ;
    private SensorManager sensorManager;
    private Sensor sensor;
    public Vibrator v;
    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    private float vibrateThreshold = 0;

    private MediaPlayer onMp, offMp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        onMp = MediaPlayer.create(this, R.raw.saber_on);
        offMp = MediaPlayer.create(this, R.raw.saber_crash);
        vibrateThreshold = sensor.getMaximumRange() / 2;
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onSensorChanged(SensorEvent event)
    {
        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);
        if (deltaX < 2)
            deltaX = 0;
        if (deltaY < 2)
            deltaY = 0;
        if (deltaX > vibrateThreshold || (deltaY > vibrateThreshold) || (deltaZ > vibrateThreshold)) {
            v.vibrate(50);
        }
            lastX = event.values[0];
            lastY = event.values[1];
            lastZ = event.values[2];
    }
        public void onAccuracyChanged(Sensor sensor, int change)
    {

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
