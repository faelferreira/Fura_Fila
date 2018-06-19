package com.furafila.felipearaujo.projetofurafila.Agendamento;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.furafila.felipearaujo.projetofurafila.AtividadesLogin.LoginActivity;
import com.furafila.felipearaujo.projetofurafila.AtividadesLogin.MainActivity;
import com.furafila.felipearaujo.projetofurafila.R;
import com.furafila.felipearaujo.projetofurafila.modelo.Agendamento;
import com.furafila.felipearaujo.projetofurafila.modelo.Pessoa;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;


public class ActivityAgendamento extends AppCompatActivity {

    private EditText celular,nome,email,cpf;
    Spinner horarios;
    private String item;
    EditText data;
    int horario;
    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Agendamento agendamento = new Agendamento();
    Pessoa pessoa = new Pessoa();

    RadioButton choje,camanha;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Data;
    LocalDateTime localDateTime;


    final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
    String id  = user1.getUid();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        this.setTitle("Agendamento");

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        celular = (EditText)findViewById(R.id.txt_celular);
        nome = (EditText)findViewById(R.id.txt_exibirnome);
        email = (EditText) findViewById(R.id.txt_exibirEmail);
        cpf = (EditText) findViewById(R.id.txt_exibirCpf);
        data = (EditText)findViewById(R.id.txt_Data);

        choje = (RadioButton)findViewById(R.id.c_hoje);
        camanha = (RadioButton)findViewById(R.id.c_amanha);


// busca dados do usuario,como email
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();




        horarios =(Spinner)findViewById(R.id.id_horarios);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.horarios_atendimento,android.R.layout.simple_spinner_item);
        horarios.setAdapter(adapter);



        //busca te testes
       databaseReference.child("Pessoa").child(id).child("nome").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               nome.setText(String.valueOf(dataSnapshot.getValue()));

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

        databaseReference.child("Pessoa").child(id).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email.setText(String.valueOf(dataSnapshot.getValue()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("Pessoa").child(id).child("cpf").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cpf.setText(String.valueOf(dataSnapshot.getValue()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







        Button agendar = (Button)findViewById(R.id.btn_agendar);

        // salvando atendimento

        agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 item = horarios.getSelectedItem().toString();




                if(choje.isChecked()) {


                    calendar = Calendar.getInstance();

                    simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    Data = simpleDateFormat.format(calendar.getTime());
                    data.setText(Data);

                }
                if(camanha.isChecked()){
                   calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DATE,1);

                    simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    Data = simpleDateFormat.format(calendar.getTime());

                    data.setText(Data);
                }

                agendamento.setNome(nome.getText().toString());
                agendamento.setCpf(cpf.getText().toString());
                agendamento.setEmail(email.getText().toString());
                agendamento.setDt_agendamento(data.getText().toString());
                agendamento.setHr_agendamento(item);
                agendamento.setCelular(celular.getText().toString());
                agendamento.setStatus("Pendente");

                    if (celular.length() < 8) {
                        Toast.makeText(getApplicationContext(), "Digite seu numero !!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //metodos do firebase a baixo


                    try {

                        databaseReference.child("Atendimento").child(id).setValue(agendamento);
                        Toast.makeText(getApplicationContext(), "Seu atendimento foi agendado para as:" + item, Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro ao agendar as :" + item, Toast.LENGTH_LONG).show();
                    }


            }

        });
    }



    }

