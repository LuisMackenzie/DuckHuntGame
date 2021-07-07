package com.mackenzie.duckhuntgame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mackenzie.duckhuntgame.common.Constantes;
import com.mackenzie.duckhuntgame.databinding.ActivityLoginBinding;
import com.mackenzie.duckhuntgame.models.User;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private String nick;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Objetos del View Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());
        View view = binding.getRoot();
        setContentView(view);

        // instanciamos firestore
        db = FirebaseFirestore.getInstance();

        // Ciambiar tipo de fiuiente
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        binding.etNick.setTypeface(typeface);
        binding.buttonStart.setTypeface(typeface);

        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nick = binding.etNick.getText().toString();

                if (nick.isEmpty()) {
                    binding.etNick.setError("El nombre de usuario es obligatorio");
                } else if(nick.length() < 3) {
                    binding.etNick.setError("El nombre de usuario es obligatorio");
                } else {
                    addNickAndStart();
                }
            }
        });
    }

    private void addNickAndStart() {
        db.collection("users")
                .whereEqualTo("nick", nick)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.size() > 0) {
                            binding.etNick.setError("El nombre de usuario no esta disponible");
                        } else {
                            addNickToFirestore();
                        }
                    }
                });


    }

    private void addNickToFirestore() {
        User newUser = new User(nick, 0);
        db.collection("users")
                .add(newUser)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        binding.etNick.setText("");
                        Intent in = new Intent(LoginActivity.this, GameActivity.class);
                        in.putExtra(Constantes.EXTRA_NICK, nick);
                        in.putExtra(Constantes.EXTRA_ID, documentReference.getId());
                        startActivity(in);
                    }
                });


    }
}