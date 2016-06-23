package ava.shadesofme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void displayCurrentTime(String currentTime) {
        TextView timeDisplay = (TextView) findViewById(R.id.text_time);
        if (timeDisplay != null) {
            timeDisplay.setText(currentTime);
        }
    }

    public void displayMaxSatiety(int maxSatiety) {
        TextView maxSatietyDisplay = (TextView) findViewById(R.id.text_max_satiety);
        if (maxSatietyDisplay != null) {
            maxSatietyDisplay.setText("/" + maxSatiety);
        }
    }

    public void displayMaxEnergy(int maxEnergy) {
        TextView maxEnergyDisplay = (TextView) findViewById(R.id.text_max_energy);
        if (maxEnergyDisplay != null) {
            maxEnergyDisplay.setText("/" + maxEnergy);
        }
    }

    public void displayMaxHealth(int maxHealth) {
        TextView maxHealthDisplay = (TextView) findViewById(R.id.text_max_health);
        if (maxHealthDisplay != null) {
            maxHealthDisplay.setText("/" + maxHealth);
        }
    }

    public void displayCurrentSatiety(int currentSatiety) {
        TextView currentSatietyDisplay = (TextView) findViewById(R.id.text_current_satiety);
        if (currentSatietyDisplay != null) {
            currentSatietyDisplay.setText("Satiety: " + currentSatiety);
        }
    }

    public void displayCurrentEnergy(int currentEnergy) {
        TextView currentEnergyDisplay = (TextView) findViewById(R.id.text_current_energy);
        if (currentEnergyDisplay != null) {
            currentEnergyDisplay.setText("Energy: " + currentEnergy);
        }
    }

    public void displayCurrentHealth(int currentHealth) {
        TextView currentHealthDisplay = (TextView) findViewById(R.id.text_current_health);
        if (currentHealthDisplay != null) {
            currentHealthDisplay.setText("Health: " + currentHealth);
        }
    }

}
