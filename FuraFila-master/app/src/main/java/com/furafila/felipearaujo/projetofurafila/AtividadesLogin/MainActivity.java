package com.furafila.felipearaujo.projetofurafila.AtividadesLogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.furafila.felipearaujo.projetofurafila.Adicionais.DadosCadastrais_Activity;
import com.furafila.felipearaujo.projetofurafila.Agendamento.ActivityAgendamento;
import com.furafila.felipearaujo.projetofurafila.Agendamento.Consulta_Activity;
import com.furafila.felipearaujo.projetofurafila.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button btnChangePassword, btnRemoveUser,
            changePassword, remove, signOut,btn_agendamento,btn_ConsultaA;
    private TextView email,nome;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseDatabase  firebaseDatabase;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Bem Vindo ao FuraFila");





//firebase auth instance
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        email = (TextView) findViewById(R.id.useremail);
        nome = (TextView)findViewById(R.id.username);
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id  = user.getUid();
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



        setDataToView(user);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {


                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        btn_agendamento = (Button)findViewById(R.id.btn_agendamento);
        btnChangePassword = (Button) findViewById(R.id.change_password_button);
        btn_ConsultaA =(Button)findViewById(R.id.btn_ConsultaA);
        signOut = (Button) findViewById(R.id.sign_out);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }







        databaseReference.child("Pessoa").child(id).child("nome").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nome.setText(String.valueOf(dataSnapshot.getValue()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void setDataToView(FirebaseUser user) {

        email.setText("Email: " + user.getEmail());


    }


    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {


                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            } else {
                setDataToView(user);

            }
        }


    };

    //sign out do firebase
    public void signOut() {
        auth.signOut();



        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {


                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    public void chamaAgenda(View v){
        Intent intent = new Intent(MainActivity.this, ActivityAgendamento.class);
        startActivity(intent);


    }

    public void chamaConsulta(View v){
        Intent intent = new Intent(MainActivity.this, Consulta_Activity.class);
        startActivity(intent);

    }
    public void chamaDados(View v){
        Intent intent = new Intent(MainActivity.this, DadosCadastrais_Activity.class);
        startActivity(intent);

    }
}




