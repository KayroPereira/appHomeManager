package com.example.apphomemanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apphomemanager.Communication.CommFirebase;
import com.example.apphomemanager.GeneralUse.ComponentStatus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KitchenActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    ProgressBar pgBar;
    ImageView ivLightOnOff;

    ImageView ivLight1;
    ImageView ivLight2;
    ImageView ivLight3;

    ImageView ivPower1;
    ImageView ivPower2;
    ImageView ivPower3;
    ImageView ivPower4;
    ImageView ivPower5;
    ImageView ivPower6;
    ImageView ivPower7;
    ImageView ivPower8;
    ImageView ivPower9;

    ComponentStatus statusComponent;// = new ComponentStatus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        pgBar = (ProgressBar) findViewById(R.id.pgBarKitchen);

        pgBar.setVisibility(View.VISIBLE);

        ivLightOnOff = (ImageView) findViewById(R.id.ivKitchenOnOff);

        ivLight1 = (ImageView) findViewById(R.id.ivCadastro);
        ivLight2 = (ImageView) findViewById(R.id.ivConfiguracaoConf);
        ivLight3 = (ImageView) findViewById(R.id.ivKitchenLight3);

        ivPower1 = (ImageView) findViewById(R.id.ivKitchenPower1);
        ivPower2 = (ImageView) findViewById(R.id.ivKitchenPower2);
        ivPower3 = (ImageView) findViewById(R.id.ivKitchenPower3);
        ivPower4 = (ImageView) findViewById(R.id.ivKitchenPower4);
        ivPower5 = (ImageView) findViewById(R.id.ivKitchenPower5);
        ivPower6 = (ImageView) findViewById(R.id.ivKitchenPower6);
        ivPower7 = (ImageView) findViewById(R.id.ivKitchenPower7);
        ivPower8 = (ImageView) findViewById(R.id.ivKitchenPower8);
        ivPower9 = (ImageView) findViewById(R.id.ivKitchenPower9);

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
                    dbOutStatus.child("kitchen").child("lonoff").setValue(action ? 1 : 0);
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
                    dbOutStatus.child("kitchen").child("light").child("out1").setValue(action ? 1 : 0);
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
                    dbOutStatus.child("kitchen").child("light").child("out2").setValue(action ? 1 : 0);
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
                    dbOutStatus.child("kitchen").child("light").child("out3").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(0) == 1 ? false : true;
                    ivPower1.setImageResource(action ? R.drawable.cooker_on1 : R.drawable.cooker1);
                    dbOutStatus.child("kitchen").child("power").child("out1").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(1) == 1 ? false : true;
                    ivPower2.setImageResource(action ? R.drawable.microwave_on1 : R.drawable.microwave1);
                    dbOutStatus.child("kitchen").child("power").child("out2").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(2) == 1 ? false : true;
                    ivPower3.setImageResource(action ? R.drawable.refrigerator_on1 : R.drawable.refrigerator1);
                    dbOutStatus.child("kitchen").child("power").child("out3").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(3) == 1 ? false : true;
                    ivPower4.setImageResource(action ? R.drawable.washingmachine_on1 : R.drawable.washingmachine1);
                    dbOutStatus.child("kitchen").child("power").child("out4").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(4) == 1 ? false : true;
                    ivPower5.setImageResource(action ? R.drawable.coffeemachine_on1 : R.drawable.coffeemachine1);
                    dbOutStatus.child("kitchen").child("power").child("out5").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(5) == 1 ? false : true;
                    ivPower6.setImageResource(action ? R.drawable.liquefier_on1 : R.drawable.liquefier1);
                    dbOutStatus.child("kitchen").child("power").child("out6").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(6) == 1 ? false : true;
                    ivPower7.setImageResource(action ? R.drawable.power_on1 : R.drawable.power1);
                    dbOutStatus.child("kitchen").child("power").child("out7").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(7) == 1 ? false : true;
                    ivPower8.setImageResource(action ? R.drawable.power_on1 : R.drawable.power1);
                    dbOutStatus.child("kitchen").child("power").child("out8").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivPower9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(8) == 1 ? false : true;
                    ivPower9.setImageResource(action ? R.drawable.power_on1 : R.drawable.power1);
                    dbOutStatus.child("kitchen").child("power").child("out9").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dbOutStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                statusComponent = new CommFirebase().getOutPut(dataSnapshot, "kitchen");

                ViewGroup layout = (ViewGroup) findViewById(R.id.layoutKitchen);
                for (int i = 0; i < layout.getChildCount(); i++) {

                    View comp = layout.getChildAt(i);

                    if (comp instanceof ImageView) {

                        switch (comp.getId()){
                            case R.id.ivKitchenOnOff:
                                boolean action = statusComponent.getBtOnOff() == 1 ? false : true;
                                ivLightOnOff.setImageResource(action ? R.drawable.btoff1 : R.drawable.bton1);
                                controlComponent(true, 0);
                                controlComponent(action ? false : true, 1);
                                pgBar.setVisibility(View.INVISIBLE);
                                break;

                            case R.id.ivCadastro:
                                ivLight1.setImageResource(statusComponent.getLoutUn(0) == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
                                break;

                            case R.id.ivConfiguracaoConf:
                                ivLight2.setImageResource(statusComponent.getLoutUn(1) == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
                                break;

                            case R.id.ivKitchenLight3:
                                ivLight3.setImageResource(statusComponent.getLoutUn(2) == 0 ? R.drawable.btlight1 : R.drawable.btlight_on1);
                                break;

                            case R.id.ivKitchenPower1:
                                ivPower1.setImageResource(statusComponent.getPoutUn(0) == 0 ? R.drawable.cooker1 : R.drawable.cooker_on1);
                                break;

                            case R.id.ivKitchenPower2:
                                ivPower2.setImageResource(statusComponent.getPoutUn(1) == 0 ? R.drawable.microwave1 : R.drawable.microwave_on1);
                                break;

                            case R.id.ivKitchenPower3:
                                ivPower3.setImageResource(statusComponent.getPoutUn(2) == 0 ? R.drawable.refrigerator1 : R.drawable.refrigerator_on1);
                                break;

                            case R.id.ivKitchenPower4:
                                ivPower4.setImageResource(statusComponent.getPoutUn(3) == 0 ? R.drawable.washingmachine1 : R.drawable.washingmachine_on1);
                                break;

                            case R.id.ivKitchenPower5:
                                ivPower5.setImageResource(statusComponent.getPoutUn(4) == 0 ? R.drawable.coffeemachine1 : R.drawable.coffeemachine_on1);
                                break;

                            case R.id.ivKitchenPower6:
                                ivPower6.setImageResource(statusComponent.getPoutUn(5) == 0 ? R.drawable.liquefier1 : R.drawable.liquefier_on1);
                                break;

                            case R.id.ivKitchenPower7:
                                ivPower7.setImageResource(statusComponent.getPoutUn(6) == 0 ? R.drawable.power1 : R.drawable.power_on1);
                                break;

                            case R.id.ivKitchenPower8:
                                ivPower8.setImageResource(statusComponent.getPoutUn(7) == 0 ? R.drawable.power1 : R.drawable.power_on1);
                                break;

                            case R.id.ivKitchenPower9:
                                ivPower9.setImageResource(statusComponent.getPoutUn(8) == 0 ? R.drawable.power1 : R.drawable.power_on1);
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

                ivPower1.setEnabled(status);
                ivPower2.setEnabled(status);
                ivPower3.setEnabled(status);
                ivPower4.setEnabled(status);
                ivPower5.setEnabled(status);
                ivPower6.setEnabled(status);
                ivPower7.setEnabled(status);
                ivPower8.setEnabled(status);
                ivPower9.setEnabled(status);
                break;
        }
    }
}
