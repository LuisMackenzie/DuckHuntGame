package com.mackenzie.duckhuntgame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.mackenzie.duckhuntgame.R;
import com.mackenzie.duckhuntgame.common.Constantes;
import com.mackenzie.duckhuntgame.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recuperamos el nombre y lo poemos en pantalla
        Bundle bun = getIntent().getExtras();
        String nick = bun.getString(Constantes.EXTRA_NICK);
        binding.tvNick.setText(nick);

        // Ciambiar tipo de fiuiente
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        binding.tvCounter.setTypeface(typeface);
        binding.tvTimer.setTypeface(typeface);
        binding.tvNick.setTypeface(typeface);

    }
}