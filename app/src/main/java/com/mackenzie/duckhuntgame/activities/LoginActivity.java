package com.mackenzie.duckhuntgame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.mackenzie.duckhuntgame.common.Constantes;
import com.mackenzie.duckhuntgame.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Objetos del View Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());
        View view = binding.getRoot();
        setContentView(view);

        // Ciambiar tipo de fiuiente
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        binding.etNick.setTypeface(typeface);
        binding.buttonStart.setTypeface(typeface);

        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nick = binding.etNick.getText().toString();

                if (nick.isEmpty()) {
                    binding.etNick.setError("el nimbre de usuario es obligatorio");
                } else if(nick.length() < 3) {
                    binding.etNick.setError("el nimbre de usuario es obligatorio");
                } else {
                    binding.etNick.setText("");
                    Intent in = new Intent(LoginActivity.this, GameActivity.class);
                    in.putExtra(Constantes.EXTRA_NICK, nick);
                    startActivity(in);
                }
            }
        });
    }
}