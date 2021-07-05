package com.mackenzie.duckhuntgame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mackenzie.duckhuntgame.common.Constantes;
import com.mackenzie.duckhuntgame.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    // private EditText etNick;
    // private Button btnStart;
    private String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Objetos del View Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());
        View view = binding.getRoot();
        setContentView(view);


        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nick = binding.etNick.getText().toString();

                if (nick.isEmpty()) {
                    binding.etNick.setError("el niombre de usuario es obligatorio");
                } else if(nick.length() < 3) {
                    binding.etNick.setError("el niombre de usuario es obligatorio");
                } else {
                    Intent in = new Intent(LoginActivity.this, GameActivity.class);
                    in.putExtra(Constantes.EXTRA_NICK, nick);
                    startActivity(in);
                }
            }
        });
    }
}