package com.example.apphomemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apphomemanager.Communication.CommFirebase;
import com.example.apphomemanager.GeneralUse.ComponentStatus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LivingRoomActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    ProgressBar pgBar;
    ImageView ivLightOnOff;

    ImageView ivLight1;
    ImageView ivLight2;
    ImageView ivLight3;
    ImageView ivLight4;
    ImageView ivLight5;
    ImageView ivLight6;

    ImageView ivLightPower1;
    ImageView ivLightPower2;
    ImageView ivLightPower3;
    ImageView ivLightPower4;
    ImageView ivLightPower5;
    ImageView ivLightPower6;

    ComponentStatus statusComponent;// = new ComponentStatus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);

        pgBar = (ProgressBar) findViewById(R.id.pgBarComm);

        pgBar.setVisibility(View.VISIBLE);

        ivLightOnOff = (ImageView) findViewById(R.id.ivLightOnOff);

        ivLight1 = (ImageView) findViewById(R.id.ivLight1);
        ivLight2 = (ImageView) findViewById(R.id.ivLight2);
        ivLight3 = (ImageView) findViewById(R.id.ivLight3);
        ivLight4 = (ImageView) findViewById(R.id.ivLight4);
        ivLight5 = (ImageView) findViewById(R.id.ivLight5);
        ivLight6 = (ImageView) findViewById(R.id.ivLight6);

        ivLightPower1 = (ImageView) findViewById(R.id.ivLightPower1);
        ivLightPower2 = (ImageView) findViewById(R.id.ivLightPower2);
        ivLightPower3 = (ImageView) findViewById(R.id.ivLightPower3);
        ivLightPower4 = (ImageView) findViewById(R.id.ivLightPower4);
        ivLightPower5 = (ImageView) findViewById(R.id.ivLightPower5);
        ivLightPower6 = (ImageView) findViewById(R.id.ivLightPower6);

        ivLightOnOff.setImageResource(R.drawable.btoff1);
        controlComponent(false, 0);
        controlComponent(false, 1);

        Log.w("Firebase", database.toString());
        Log.w("DataFirebase", reference.toString());

        ivLightOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getBtOnOff() == 1 ? false : true;

                    ivLightOnOff.setImageResource(action ? R.drawable.btoff1 : R.drawable.bton1);
                    dbOutStatus.child("living").child("lonoff").setValue(action ? 1 : 0);
                    controlComponent(action ? false : true, 1);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(0) == 1 ? false : true;
                    ivLight1.setImageResource(action ? R.drawable.btlight_on1 : R.drawable.btlight1);
                    dbOutStatus.child("living").child("light").child("out1").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(1) == 1 ? false : true;
                    ivLight2.setImageResource(action ? R.drawable.btlight_on1 : R.drawable.btlight1);
                    dbOutStatus.child("living").child("light").child("out2").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(2) == 1 ? false : true;
                    ivLight3.setImageResource(action ? R.drawable.btlight_on1 : R.drawable.btlight1);
                    dbOutStatus.child("living").child("light").child("out3").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(3) == 1 ? false : true;
                    ivLight4.setImageResource(action ? R.drawable.btlight_on1 : R.drawable.btlight1);
                    dbOutStatus.child("living").child("light").child("out4").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(4) == 1 ? false : true;
                    ivLight5.setImageResource(action ? R.drawable.btlight_on1 : R.drawable.btlight1);
                    dbOutStatus.child("living").child("light").child("out5").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(5) == 1 ? false : true;
                    ivLight6.setImageResource(action ? R.drawable.btlight_on1 : R.drawable.btlight1);
                    dbOutStatus.child("living").child("light").child("out6").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLightPower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(0) == 1 ? false : true;
                    ivLightPower1.setImageResource(action ? R.drawable.tv_on1 : R.drawable.tv1);
                    dbOutStatus.child("living").child("power").child("out1").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLightPower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(1) == 1 ? false : true;
                    ivLightPower2.setImageResource(action ? R.drawable.sky_on1 : R.drawable.sky1);
                    dbOutStatus.child("living").child("power").child("out2").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLightPower3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(2) == 1 ? false : true;
                    ivLightPower3.setImageResource(action ? R.drawable.som_on1 : R.drawable.som1);
                    dbOutStatus.child("living").child("power").child("out3").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLightPower4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(3) == 1 ? false : true;
                    ivLightPower4.setImageResource(action ? R.drawable.power_on1 : R.drawable.power1);
                    dbOutStatus.child("living").child("power").child("out4").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLightPower5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(4) == 1 ? false : true;
                    ivLightPower5.setImageResource(action ? R.drawable.power_on1 : R.drawable.power1);
                    dbOutStatus.child("living").child("power").child("out5").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLightPower6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(5) == 1 ? false : true;
                    ivLightPower6.setImageResource(action ? R.drawable.power_on1 : R.drawable.power1);
                    dbOutStatus.child("living").child("power").child("out6").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dbOutStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                statusComponent = new CommFirebase().getOutPut(dataSnapshot, "living");

                ViewGroup layout = (ViewGroup) findViewById(R.id.layoutLivingRoom);
                for (int i = 0; i < layout.getChildCount(); i++) {

                    View comp = layout.getChildAt(i);

                    if (comp instanceof ImageView) {

                        switch (comp.getId()){
                            case R.id.ivLightOnOff:
                                boolean action = statusComponent.getBtOnOff() == 1 ? false : true;
                                ivLightOnOff.setImageResource(action ? R.drawable.btoff1 : R.drawable.bton1);
                                controlComponent(true, 0);
                                controlComponent(action ? false : true, 1);
                                pgBar.setVisibility(View.INVISIBLE);
                                break;

                            case R.id.ivLight1:
                                    ivLight1.setImageResource(statusComponent.getLoutUn(0) == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
                                break;

                            case R.id.ivLight2:
                                ivLight2.setImageResource(statusComponent.getLoutUn(1) == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
                                break;

                            case R.id.ivLight3:
                                ivLight3.setImageResource(statusComponent.getLoutUn(2) == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
                                break;

                            case R.id.ivLight4:
                                ivLight4.setImageResource(statusComponent.getLoutUn(3) == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
                                break;

                            case R.id.ivLight5:
                                ivLight5.setImageResource(statusComponent.getLoutUn(4) == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
                                break;

                            case R.id.ivLight6:
                                ivLight6.setImageResource(statusComponent.getLoutUn(5) == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
                                break;

                            case R.id.ivLightPower1:
                                ivLightPower1.setImageResource(statusComponent.getPoutUn(0) == 0 ? R.drawable.tv1 : R.drawable.tv_on1);
                                break;

                            case R.id.ivLightPower2:
                                ivLightPower2.setImageResource(statusComponent.getPoutUn(1) == 0 ? R.drawable.sky1 : R.drawable.sky_on1);
                                break;

                            case R.id.ivLightPower3:
                                ivLightPower3.setImageResource(statusComponent.getPoutUn(2) == 0 ? R.drawable.som1 : R.drawable.som_on1);
                                break;

                            case R.id.ivLightPower4:
                                ivLightPower4.setImageResource(statusComponent.getPoutUn(3) == 0 ? R.drawable.power1 : R.drawable.power_on1);
                                break;

                            case R.id.ivLightPower5:
                                ivLightPower5.setImageResource(statusComponent.getPoutUn(4) == 0 ? R.drawable.power1 : R.drawable.power_on1);
                                break;

                            case R.id.ivLightPower6:
                                ivLightPower6.setImageResource(statusComponent.getPoutUn(5) == 0 ? R.drawable.power1 : R.drawable.power_on1);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void controlComponent(boolean status, int mode){

        switch(mode) {
            case 0:
                ivLightOnOff.setEnabled(status);
                break;

            case 1:
                ivLight1.setEnabled(status);
                ivLight2.setEnabled(status);
                ivLight3.setEnabled(status);
                ivLight4.setEnabled(status);
                ivLight5.setEnabled(status);
                ivLight6.setEnabled(status);

                ivLightPower1.setEnabled(status);
                ivLightPower2.setEnabled(status);
                ivLightPower3.setEnabled(status);
                ivLightPower4.setEnabled(status);
                ivLightPower5.setEnabled(status);
                ivLightPower6.setEnabled(status);
                break;
        }
    }
}
