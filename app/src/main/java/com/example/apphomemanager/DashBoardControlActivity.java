package com.example.apphomemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DashBoardControlActivity extends AppCompatActivity {

    /*
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    private AlertDialog alerta;

    private ImageView ivBackDBC;

     */
    private ImageView ivLivingRoom;
    private ImageView ivKitchen;
    private ImageView ivBeedRoom1;
    private ImageView ivBeedRoom2;
    private ImageView ivBeedRoom3;
    private ImageView ivBeedRoom4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_control);

        ivLivingRoom = (ImageView) findViewById(R.id.ivSalaDBC);
        ivKitchen = (ImageView) findViewById(R.id.ivCozinhaDBC);
        //ivBackDBC = (ImageView) findViewById(R.id.ivBackDBC);
        ivBeedRoom1 = (ImageView) findViewById(R.id.ivBeedRoom1DBC);
        ivBeedRoom2 = (ImageView) findViewById(R.id.ivBeedRoom2DBC);
        ivBeedRoom3 = (ImageView) findViewById(R.id.ivBeedRoom3DBC);
        ivBeedRoom4 = (ImageView) findViewById(R.id.ivBeedRoom4DBC);

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
                Intent it = new Intent(DashBoardControlActivity.this, KitchenRoomActivity.class);
                startActivity(it);
            }
        });

        ivBeedRoom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardControlActivity.this, BedRoom1Activity.class);
                startActivity(it);
            }
        });

        ivBeedRoom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardControlActivity.this, BedRoom2Activity.class);
                startActivity(it);
            }
        });

        ivBeedRoom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardControlActivity.this, BedRoom3Activity.class);
                startActivity(it);
            }
        });

        ivBeedRoom4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardControlActivity.this, BedRoom4Activity.class);
                startActivity(it);
            }
        });
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
