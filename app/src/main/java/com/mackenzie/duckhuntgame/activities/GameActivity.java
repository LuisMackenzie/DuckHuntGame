package com.mackenzie.duckhuntgame.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;

import com.mackenzie.duckhuntgame.R;
import com.mackenzie.duckhuntgame.common.Constantes;
import com.mackenzie.duckhuntgame.databinding.ActivityGameBinding;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private int count= 0;
    private int anchoP, altoP;
    private Random aleatorio;
    private boolean gameOver = false;
    private CountDownTimer decimTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Cambiar tipo de fuente
        setUpFonts();
        // Recuperamos el nombre y lo ponemos en pantalla
        Bundle bun = getIntent().getExtras();
        String nick = bun.getString(Constantes.EXTRA_NICK);
        binding.tvNick.setText(nick);
        eventosClick();

        initPantalla();

        initCuentaAtras();

        // moveDuck();

    }

    private void initCuentaAtras() {

        decimTimer = new CountDownTimer(900, 100) {

            public void onTick(long millisUntilFinished) {
                long decimasrestantes = millisUntilFinished / 100;

                binding.tvTimerDecims.setText(decimasrestantes + "s");
            }

            public void onFinish() {
                binding.tvTimerDecims.setText("0s");
                // onTick();
                start();
            }
        }.start();

        new CountDownTimer(10000, 100) {

            public void onTick(long millisUntilFinished) {
                long segRestantes = millisUntilFinished / 1000;

                binding.tvTimerSeg.setText(segRestantes + ".");
                // decimTimer.start();

            }

            public void onFinish() {
                binding.tvTimerSeg.setText("0.");
                binding.tvTimerDecims.setText("0s");
                decimTimer.cancel();
                gameOver = true;
                mostrarGameOver();
            }
        }.start();



    }

    private void mostrarGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Has conseguido cazar " + count + " patos");
        builder.setTitle("Game Over");

        builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                count = 0;
                binding.tvCounter.setText("0");
                gameOver = false;
                initCuentaAtras();
                moveDuck();
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void initPantalla() {
        // Obtener el tamaño de la pantalla del dispositivo
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        anchoP = size.x;
        altoP = size.y;
        // inicializamos el objeto para generar numeros aleatorios
        aleatorio = new Random();

    }

    private void setUpFonts() {
        // Cambiar tipo de fuente
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        binding.tvCounter.setTypeface(typeface);
        binding.tvTimerSeg.setTypeface(typeface);
        binding.tvTimerDecims.setTypeface(typeface);
        binding.tvNick.setTypeface(typeface);
    }

    private void eventosClick() {
        if (!gameOver) {
            binding.ivDuck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count++;
                    binding.tvCounter.setText(String.valueOf(count));
                    binding.ivDuck.setImageResource(R.drawable.duck_clicked);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.ivDuck.setImageResource(R.drawable.duck);
                            moveDuck();
                        }
                    }, 500);

                }
            });
        }

    }

    private void moveDuck() {
        int min = 0;
        int maximoX = anchoP - binding.ivDuck.getWidth();
        int maximoY = altoP - binding.ivDuck.getHeight();
        // Generamos 2 numeros aleatorios aqui
        int randomX = aleatorio.nextInt(((maximoX - min) + 1) + min);
        int randomY = aleatorio.nextInt(((maximoY - min) + 1) + min);
        // utilizamos los numeroa aalñeatoris para mover el pato
        binding.ivDuck.setX(randomX);
        binding.ivDuck.setY(randomY);
    }

}