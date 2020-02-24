package gov.unsc.lupo.lightsaber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }

    private void playOffSound() {

    }

}
