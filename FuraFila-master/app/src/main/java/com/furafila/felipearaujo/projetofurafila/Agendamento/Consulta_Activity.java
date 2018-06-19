package com.furafila.felipearaujo.projetofurafila.Agendamento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.furafila.felipearaujo.projetofurafila.AtividadesLogin.SignupActivity;
import com.furafila.felipearaujo.projetofurafila.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Consulta_Activity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String recebe;
    Button Consultar;
    EditText cpf,data,horario,email,status;

    final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
    String id  = user1.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



        Consultar =(Button)findViewById(R.id.btn_Consulta1);
        data = (EditText)findViewById(R.id.txt_dataC);
        horario = (EditText)findViewById(R.id.txtHorarioC);
        cpf = (EditText)findViewById(R.id.txtCpfC);
        email =(EditText)findViewById(R.id.txt_emailC);
        status = (EditText)findViewById(R.id.txt_statusC);

        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Atendimento").child(id).child("email").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        email.setText(String.valueOf(dataSnapshot.getValue()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



                databaseReference.child("Atendimento").child(id).child("cpf").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       cpf.setText(String.valueOf(dataSnapshot.getValue()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                databaseReference.child("Atendimento").child(id).child("dt_agendamento").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        data.setText(String.valueOf(dataSnapshot.getValue()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                       data.setText("ERRO");
                    }
                });

                databaseReference.child("Atendimento").child(id).child("hr_agendamento").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        horario.setText(String.valueOf(dataSnapshot.getValue()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                databaseReference.child("Atendimento").child(id).child("status").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        status.setText(String.valueOf(dataSnapshot.getValue()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        }


    });


}
}

