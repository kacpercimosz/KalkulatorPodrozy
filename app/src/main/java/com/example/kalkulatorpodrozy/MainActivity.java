package com.example.kalkulatorpodrozy;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText dystans = findViewById(R.id.dystans);
        EditText spalanie = findViewById(R.id.spalanie);
        EditText pasazerowie = findViewById(R.id.pasazerowie);

        RadioGroup paliwo = findViewById(R.id.paliwo);
        TextView wynik = findViewById(R.id.wynik);
        Button oblicz = findViewById(R.id.oblicz);

        oblicz.setOnClickListener(v -> {

            try {
                double km = Double.parseDouble(
                        dystans.getText().toString().replace(",", "."));

                double litry = Double.parseDouble(
                        spalanie.getText().toString().replace(",", "."));

                int osoby = Integer.parseInt(
                        pasazerowie.getText().toString());

                if (km <= 0 || litry <= 0 || osoby <= 0) {
                    throw new Exception();
                }

                double cena = 6.50;

                int wybor = paliwo.getCheckedRadioButtonId();

                if (wybor == R.id.diesel) {
                    cena = 6.70;
                } else if (wybor == R.id.lpg) {
                    cena = 3.10;
                }

                double koszt = km * litry / 100 * cena;
                double naOsobe = koszt / osoby;

                wynik.setTextColor(0xFF008000);

                wynik.setText(String.format(
                        java.util.Locale.getDefault(),
                        "Całkowity koszt: %.2f zł\nNa osobę: %.2f zł",
                        koszt, naOsobe));

            } catch (Exception e) {

                wynik.setTextColor(0xFFFF0000);
                wynik.setText("Niepoprawne dane wejściowe!");
            }
        });
    }
}
