package com.example.apphomemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apphomemanager.Communication.CommFirebase;
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

    private ImageView ivLivingRoom;
    private ImageView ivKitchen;
    private ImageView ivConfiguration;
    private ImageView ivDoorLock;
    private ImageView ivReservoirDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        ivLivingRoom = (ImageView) findViewById(R.id.ivLivingRoom);
        ivKitchen = (ImageView) findViewById(R.id.ivKitchen);
        ivConfiguration = (ImageView) findViewById(R.id.ivConfigurationDash);
        ivDoorLock = (ImageView) findViewById(R.id.ivDoorLockDash);
        ivReservoirDB = (ImageView) findViewById(R.id.ivReservoirDB);

        ivReservoirDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardActivity.this, DashBoardReservoirActivity.class);
                startActivity(it);
            }
        });

        ivLivingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardActivity.this, LivingRoomActivity.class);
                startActivity(it);
            }
        });

        ivKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardActivity.this, KitchenActivity.class);
                startActivity(it);
            }
        });

        ivConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardActivity.this, ConfigurationActivity.class);
                startActivity(it);
            }
        });

        ivDoorLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardActivity.this);
                builder.setTitle("Abrir porta de entrada");
                builder.setMessage("Deseja abrir a porta de entrada?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        try{
                            ivDoorLock.setImageResource(R.drawable.btlight_on1);
                            dbOutStatus.child("door").child("d1").setValue(1);
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Nã0", new DialogInterface.OnClickListener() {
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
                ivDoorLock.setImageResource(new CommFirebase().getComponentStatus(dataSnapshot, "door", "d1") == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
