package com.example.apphomemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashBoardControlActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    private AlertDialog alerta;

    private ImageView ivBackDBC;
    private ImageView ivLivingRoom;
    private ImageView ivKitchen;

    //private ImageView ivConfiguration;
    //private ImageView ivDoorLock;
    //private ImageView ivReservoirDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_control);

        ivLivingRoom = (ImageView) findViewById(R.id.ivSalaDBC);
        ivKitchen = (ImageView) findViewById(R.id.ivCozinhaDBC);
        ivBackDBC = (ImageView) findViewById(R.id.ivBackDBC);

/*
        ivConfiguration = (ImageView) findViewById(R.id.ivSetupDB);
        ivDoorLock = (ImageView) findViewById(R.id.ivDoorLockDB);
        ivReservoirDB = (ImageView) findViewById(R.id.ivReservoirDB);

        ivReservoirDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardControlActivity.this, DashBoardReservoirActivity.class);
                startActivity(it);
            }
        });

 */

        ivLivingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardControlActivity.this, LivingRoomActivity.class);
                startActivity(it);
            }
        });

        ivKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardControlActivity.this, KitchenActivity.class);
                startActivity(it);
            }
        });
/*
        ivConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardControlActivity.this, ConfigurationActivity.class);
                startActivity(it);
            }
        });

 */

        /*
        ivDoorLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardControlActivity.this);
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
                    Toast.makeText(DashBoardControlActivity.this, "Solicitação cancelada", Toast.LENGTH_SHORT).show();
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

         */
    }

    public void buttonClicked(View item){

        switch (item.getId()){
            case R.id.ivBackDBC:
                //Toast.makeText(getApplicationContext(), "btBoxBack On", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
