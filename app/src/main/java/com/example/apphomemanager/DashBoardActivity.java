package com.example.apphomemanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.apphomemanager.Communication.CommFirebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashBoardActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    private AlertDialog alerta;

    private ImageView ivDoorLockDB;
    private ImageView ivControlDB;
    private ImageView ivReservoirDB;
    private ImageView ivSetupDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        ivDoorLockDB = (ImageView) findViewById(R.id.ivDoorLockDB);
        ivControlDB = (ImageView) findViewById(R.id.ivControlDB);
        ivReservoirDB = (ImageView) findViewById(R.id.ivReservoirDB);
        ivSetupDB = (ImageView) findViewById(R.id.ivSetupDB);


        ivControlDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardActivity.this, DashBoardControlActivity.class);
                startActivity(it);
            }
        });

        ivSetupDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardActivity.this, ConfigurationActivity.class);
                startActivity(it);
            }
        });

        ivReservoirDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardActivity.this, DashBoardReservoirActivity.class);
                startActivity(it);
            }
        });

        ivDoorLockDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardActivity.this);
                builder.setTitle("Abrir porta de entrada");
                builder.setMessage("Deseja abrir a porta de entrada?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        try{
                            ivDoorLockDB.setImageResource(R.drawable.btlight_on1);
                            dbOutStatus.child("door").child("d1").setValue(1);
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(DashBoardActivity.this, "Solicitação cancelada", Toast.LENGTH_SHORT).show();
                    }
                });
                alerta = builder.create();
                alerta.show();
            }
        });

        dbOutStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //get status component ivDoorLok in firebase
                ivDoorLockDB.setImageResource(new CommFirebase().getComponentStatus(dataSnapshot, "door", "d1") == 0 ? R.drawable.door : R.drawable.dooron1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
