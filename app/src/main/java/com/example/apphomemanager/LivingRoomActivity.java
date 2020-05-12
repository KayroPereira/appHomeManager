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

    ProgressBar pgBar;
    ImageView ivLightOnOff;

    ImageView ivBackLVR;

    ImageView ivLight1LVR;
    ImageView ivLight2LVR;
    ImageView ivLight3LVR;
    ImageView ivLight4LVR;
    ImageView ivLight5LVR;
    ImageView ivLight6LVR;

    ImageView ivPower1LVR;
    ImageView ivPower2LVR;
    ImageView ivPower3LVR;
    ImageView ivPower4LVR;
    ImageView ivPower5LVR;
    ImageView ivPower6LVR;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    ComponentStatus statusComponent;// = new ComponentStatus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);

        pgBar = (ProgressBar) findViewById(R.id.pgBarCommLVR);

        pgBar.setVisibility(View.VISIBLE);

        ivLightOnOff = (ImageView) findViewById(R.id.ivLightOnOff);

        ivBackLVR = (ImageView) findViewById(R.id.ivBackLVR);

        ivLight1LVR = (ImageView) findViewById(R.id.ivLight1LVR);
        ivLight2LVR = (ImageView) findViewById(R.id.ivLight2LVR);
        ivLight3LVR = (ImageView) findViewById(R.id.ivLight3LVR);
        ivLight4LVR = (ImageView) findViewById(R.id.ivLight4LVR);
        ivLight5LVR = (ImageView) findViewById(R.id.ivLight5LVR);
        ivLight6LVR = (ImageView) findViewById(R.id.ivLight6LVR);

        ivPower1LVR = (ImageView) findViewById(R.id.ivPower1LVR);
        ivPower2LVR = (ImageView) findViewById(R.id.ivPower2LVR);
        ivPower3LVR = (ImageView) findViewById(R.id.ivPower3LVR);
        ivPower4LVR = (ImageView) findViewById(R.id.ivPower4LVR);
        ivPower5LVR = (ImageView) findViewById(R.id.ivPower5LVR);
        ivPower6LVR = (ImageView) findViewById(R.id.ivPower6LVR);

        ivLightOnOff.setImageResource(R.drawable.btoff);
        controlComponent(false, 0);
        controlComponent(false, 1);

        Log.w("Firebase", database.toString());
        Log.w("DataFirebase", reference.toString());

        ivLightOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getBtOnOff() == 1 ? false : true;

                    ivLightOnOff.setImageResource(action ? R.drawable.btoff : R.drawable.bton);
                    dbOutStatus.child("living").child("lonoff").setValue(action ? 1 : 0);
                    controlComponent(action ? false : true, 1);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight1LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(0) == 1 ? false : true;
                    ivLight1LVR.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o1").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight2LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(1) == 1 ? false : true;
                    ivLight2LVR.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o2").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight3LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(2) == 1 ? false : true;
                    ivLight3LVR.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o3").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight4LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(3) == 1 ? false : true;
                    ivLight4LVR.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o4").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight5LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(4) == 1 ? false : true;
                    ivLight5LVR.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o5").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLight6LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(5) == 1 ? false : true;
                    ivLight6LVR.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o6").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower1LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(0) == 1 ? false : true;
                    ivPower1LVR.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o1").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower2LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(1) == 1 ? false : true;
                    ivPower2LVR.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o2").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower3LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(2) == 1 ? false : true;
                    ivPower3LVR.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o3").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower4LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(3) == 1 ? false : true;
                    ivPower4LVR.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o4").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower5LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(4) == 1 ? false : true;
                    ivPower5LVR.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o5").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower6LVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(5) == 1 ? false : true;
                    ivPower6LVR.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o6").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dbOutStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                statusComponent = new CommFirebase().getOutPut(dataSnapshot, "living");

                //ViewGroup layout = (ViewGroup) findViewById(R.id.layoutLivingRoom);
                ViewGroup layout = (ViewGroup) findViewById(R.id.ctnlPrincipalLVR);
                for (int i = 0; i < layout.getChildCount(); i++) {

                    View comp = layout.getChildAt(i);

                    if (comp instanceof ImageView) {

                        switch (comp.getId()){
                            case R.id.ivLightOnOff:
                                boolean action = statusComponent.getBtOnOff() == 1 ? false : true;
                                ivLightOnOff.setImageResource(action ? R.drawable.btoff : R.drawable.bton);
                                controlComponent(true, 0);
                                controlComponent(action ? false : true, 1);
                                pgBar.setVisibility(View.INVISIBLE);
                                updateStatusComponent();
                                break;

                            case R.id.ivLight1LVR:
                                ivLight1LVR.setImageResource(statusComponent.getLoutUn(0) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight2LVR:
                                ivLight2LVR.setImageResource(statusComponent.getLoutUn(1) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight3LVR:
                                ivLight3LVR.setImageResource(statusComponent.getLoutUn(2) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight4LVR:
                                ivLight4LVR.setImageResource(statusComponent.getLoutUn(3) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight5LVR:
                                ivLight5LVR.setImageResource(statusComponent.getLoutUn(4) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight6LVR:
                                ivLight6LVR.setImageResource(statusComponent.getLoutUn(5) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivPower1LVR:
                                ivPower1LVR.setImageResource(statusComponent.getPoutUn(0) == 0 ? R.drawable.tv1 : R.drawable.tv_on1);
                                break;

                            case R.id.ivPower2LVR:
                                ivPower2LVR.setImageResource(statusComponent.getPoutUn(1) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                                break;

                            case R.id.ivPower3LVR:
                                ivPower3LVR.setImageResource(statusComponent.getPoutUn(2) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                                break;

                            case R.id.ivPower4LVR:
                                ivPower4LVR.setImageResource(statusComponent.getPoutUn(3) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                                break;

                            case R.id.ivPower5LVR:
                                ivPower5LVR.setImageResource(statusComponent.getPoutUn(4) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                                break;

                            case R.id.ivPower6LVR:
                                ivPower6LVR.setImageResource(statusComponent.getPoutUn(5) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
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
                ivLight1LVR.setEnabled(status);
                ivLight2LVR.setEnabled(status);
                ivLight3LVR.setEnabled(status);
                ivLight4LVR.setEnabled(status);
                ivLight5LVR.setEnabled(status);
                ivLight6LVR.setEnabled(status);

                ivPower1LVR.setEnabled(status);
                ivPower2LVR.setEnabled(status);
                ivPower3LVR.setEnabled(status);
                ivPower4LVR.setEnabled(status);
                ivPower5LVR.setEnabled(status);
                ivPower6LVR.setEnabled(status);
                break;
        }
    }

    public void buttonClickedLVR(View item){

        switch (item.getId()){
            case R.id.ivBackLVR:
                //Toast.makeText(getApplicationContext(), "btBoxBack On", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    public void updateStatusComponent(){
        if (statusComponent.getBtOnOff() != 0) {
            ivLight1LVR.setImageResource(statusComponent.getLoutUn(0) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight2LVR.setImageResource(statusComponent.getLoutUn(1) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight3LVR.setImageResource(statusComponent.getLoutUn(2) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight4LVR.setImageResource(statusComponent.getLoutUn(3) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight5LVR.setImageResource(statusComponent.getLoutUn(4) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight6LVR.setImageResource(statusComponent.getLoutUn(5) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivPower1LVR.setImageResource(statusComponent.getPoutUn(0) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower2LVR.setImageResource(statusComponent.getPoutUn(1) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower3LVR.setImageResource(statusComponent.getPoutUn(2) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower4LVR.setImageResource(statusComponent.getPoutUn(3) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower5LVR.setImageResource(statusComponent.getPoutUn(4) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower6LVR.setImageResource(statusComponent.getPoutUn(5) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
        }else{
            ivLight1LVR.setImageResource(R.drawable.lampadaoff);
            ivLight2LVR.setImageResource(R.drawable.lampadaoff);
            ivLight3LVR.setImageResource(R.drawable.lampadaoff);
            ivLight4LVR.setImageResource(R.drawable.lampadaoff);
            ivLight5LVR.setImageResource(R.drawable.lampadaoff);
            ivLight6LVR.setImageResource(R.drawable.lampadaoff);
            ivPower1LVR.setImageResource(R.drawable.tomadaoff);
            ivPower2LVR.setImageResource(R.drawable.tomadaoff);
            ivPower3LVR.setImageResource(R.drawable.tomadaoff);
            ivPower4LVR.setImageResource(R.drawable.tomadaoff);
            ivPower5LVR.setImageResource(R.drawable.tomadaoff);
            ivPower6LVR.setImageResource(R.drawable.tomadaoff);
        }
    }
}
