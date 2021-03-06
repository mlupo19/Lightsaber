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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private boolean on = false;
    private float lastX, lastY, lastZ;
    private SensorManager sensorManager;
    private Sensor sensor;
    public Vibrator v;
    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    private float dxMax = 0;
    private float vibrateThreshold = 0;
    private TextView debugTv;

    private MediaPlayer onMp, offMp, crashMp;


    /*
        This app uses the accelerometer to detect when the phone is swung or suddenly stops.
        To activate/deactivate the lightsaber, press the lightsaber.
        Due to problems with Github, we had to submit the app one night late.

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        onMp = MediaPlayer.create(this, R.raw.saber_on);
        offMp = MediaPlayer.create(this, R.raw.saber_off);
        crashMp = MediaPlayer.create(this, R.raw.saber_crash);
        vibrateThreshold = sensor.getMaximumRange() / 2;
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        debugTv = findViewById(R.id.textView);
        sensorManager.registerListener(this, sensor, 100);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);
        if (on && (deltaX > vibrateThreshold || (deltaY > vibrateThreshold) || (deltaZ > vibrateThreshold))) {
            playCrashSound();
            v.vibrate(50);
        }
        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (deltaX > dxMax)
                    dxMax = deltaX;
                debugTv.setText("X: " + lastX + " Y: " + lastY + " Z: " + lastZ);
            }
        });
    }

    private void playCrashSound() {
        if (crashMp != null) {
            crashMp.stop();
            crashMp.release();
        }
        crashMp = MediaPlayer.create(this, R.raw.saber_crash);
        crashMp.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int change) {}

    public void clickOn(View view) {
        if (on && lastY > 8) {
            playOffSound();
            on = false;
        } else if (!on && lastY > 8) {
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

