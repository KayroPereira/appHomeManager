package com.example.apphomemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apphomemanager.Communication.CommFirebase;
import com.example.apphomemanager.GeneralUse.ComponentStatus;
import com.example.apphomemanager.GeneralUse.ConstantsApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LivingRoomActivity extends AppCompatActivity {

    private ViewGroup vgPrincipal;
    private ProgressBar pgBar;
    private ImageView ivLightOnOff;

    private ImageView ivBack;

    private ImageView ivLight1;
    private ImageView ivLight2;
    private ImageView ivLight3;
    private ImageView ivLight4;
    private ImageView ivLight5;
    private ImageView ivLight6;

    private ImageView ivPower1;
    private ImageView ivPower2;
    private ImageView ivPower3;
    private ImageView ivPower4;
    private ImageView ivPower5;
    private ImageView ivPower6;

    private ArrayList<ImageView> lightDevice = new ArrayList<>();
    private ArrayList<ImageView> powerDevice = new ArrayList<>();

    private String PATH_DEVICE;
    private int TYPE_DEVICE;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;
    final ConstantsApp constants = new ConstantsApp();

    private ComponentStatus statusComponent;// = new ComponentStatus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);

        //zona de adaptação para outros ambientes
        vgPrincipal = (ViewGroup) findViewById(R.id.ctnlPrincipalLVR);

        pgBar = (ProgressBar) findViewById(R.id.pgBarCommLVR);

        ivLightOnOff = (ImageView) findViewById(R.id.ivLightOnOff);

        ivBack = (ImageView) findViewById(R.id.ivBackLVR);

        ivLight1 = (ImageView) findViewById(R.id.ivLight1LVR);
        ivLight2 = (ImageView) findViewById(R.id.ivLight2LVR);
        ivLight3 = (ImageView) findViewById(R.id.ivLight3LVR);
        ivLight4 = (ImageView) findViewById(R.id.ivLight4LVR);
        ivLight5 = (ImageView) findViewById(R.id.ivLight5LVR);
        ivLight6 = (ImageView) findViewById(R.id.ivLight6LVR);

        ivPower1 = (ImageView) findViewById(R.id.ivPower1LVR);
        ivPower2 = (ImageView) findViewById(R.id.ivPower2LVR);
        ivPower3 = (ImageView) findViewById(R.id.ivPower3LVR);
        ivPower4 = (ImageView) findViewById(R.id.ivPower4LVR);
        ivPower5 = (ImageView) findViewById(R.id.ivPower5LVR);
        ivPower6 = (ImageView) findViewById(R.id.ivPower6LVR);

        TYPE_DEVICE = constants.getLIVING();
        //realizar as modificações até aqui

        pgBar.setVisibility(View.VISIBLE);

        lightDevice.add(ivLight1);
        lightDevice.add(ivLight2);
        lightDevice.add(ivLight3);
        lightDevice.add(ivLight4);
        lightDevice.add(ivLight5);
        lightDevice.add(ivLight6);

        powerDevice.add(ivPower1);
        powerDevice.add(ivPower2);
        powerDevice.add(ivPower3);
        powerDevice.add(ivPower4);
        powerDevice.add(ivPower5);
        powerDevice.add(ivPower6);

        ivLightOnOff.setImageResource(R.drawable.btoff);

        PATH_DEVICE = constants.getPathComodo()[TYPE_DEVICE];

        controlComponent(false, 0);
        controlComponent(false, 1);

        Log.w("Firebase", database.toString());
        Log.w("DataFirebase", reference.toString());
/*
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
 */
/*
        ivLight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(0) == 1 ? false : true;
                    ivLight1.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o1").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });
 */
/*
        ivLight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getLoutUn(1) == 1 ? false : true;
                    ivLight2.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o2").setValue(action ? 1 : 0);
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
                    ivLight3.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o3").setValue(action ? 1 : 0);
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
                    ivLight4.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o4").setValue(action ? 1 : 0);
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
                    ivLight5.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o5").setValue(action ? 1 : 0);
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
                    ivLight6.setImageResource(action ? R.drawable.lampadaon : R.drawable.lampadaoff);
                    dbOutStatus.child("living").child("l").child("o6").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
/*
        ivPower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    boolean action = statusComponent.getPoutUn(0) == 1 ? false : true;
                    ivPower1.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o1").setValue(action ? 1 : 0);
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
                    ivPower2.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o2").setValue(action ? 1 : 0);
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
                    ivPower3.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o3").setValue(action ? 1 : 0);
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
                    ivPower4.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o4").setValue(action ? 1 : 0);
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
                    ivPower5.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o5").setValue(action ? 1 : 0);
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
                    ivPower6.setImageResource(action ? R.drawable.tomadaoff : R.drawable.tomadaon);
                    dbOutStatus.child("living").child("p").child("o6").setValue(action ? 1 : 0);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

 */

        dbOutStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                statusComponent = new CommFirebase().getOutPut(dataSnapshot, PATH_DEVICE);

                //ViewGroup layout = (ViewGroup) findViewById(R.id.layoutLivingRoom);
                //ViewGroup layout = (ViewGroup) findViewById(R.id.ctnlPrincipalLVR);
                for (int i = 0; i < vgPrincipal.getChildCount(); i++) {
                //for (ViewGroup temp : layout)

                    View comp = vgPrincipal.getChildAt(i);

                    if (comp instanceof ImageView) {
                        switch (comp.getId()) {
                            case R.id.ivLightOnOff:
                                boolean action = statusComponent.getBtOnOff() == 1 ? false : true;
                                ivLightOnOff.setImageResource(action ? R.drawable.btoff : R.drawable.bton);
                                controlComponent(true, 0);
                                controlComponent(action ? false : true, 1);
                                pgBar.setVisibility(View.INVISIBLE);
                                updateStatusComponent();
                                break;
                        }
                    }
                }
                    /*

                            case R.id.ivLight1:
                                ivLight1.setImageResource(statusComponent.getLoutUn(0) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight2:
                                ivLight2.setImageResource(statusComponent.getLoutUn(1) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight3:
                                ivLight3.setImageResource(statusComponent.getLoutUn(2) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight4:
                                ivLight4.setImageResource(statusComponent.getLoutUn(3) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight5:
                                ivLight5.setImageResource(statusComponent.getLoutUn(4) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivLight6:
                                ivLight6.setImageResource(statusComponent.getLoutUn(5) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivPower1:
                                ivPower1.setImageResource(statusComponent.getPoutUn(0) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                                break;

                            case R.id.ivPower2:
                                ivPower2.setImageResource(statusComponent.getPoutUn(1) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                                break;

                            case R.id.ivPower3:
                                ivPower3.setImageResource(statusComponent.getPoutUn(2) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                                break;

                            case R.id.ivPower4:
                                ivPower4.setImageResource(statusComponent.getPoutUn(3) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                                break;

                            case R.id.ivPower5:
                                ivPower5.setImageResource(statusComponent.getPoutUn(4) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                                break;

                            case R.id.ivPower6:
                                ivPower6.setImageResource(statusComponent.getPoutUn(5) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                                break;
                        }
                    }
                }

                 */
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
                for (ImageView temp : lightDevice)
                    temp.setEnabled(status);

                for (ImageView temp : powerDevice)
                    temp.setEnabled(status);

                /*
                ivLight1.setEnabled(status);
                ivLight2.setEnabled(status);
                ivLight3.setEnabled(status);
                ivLight4.setEnabled(status);
                ivLight5.setEnabled(status);
                ivLight6.setEnabled(status);

                ivPower1.setEnabled(status);
                ivPower2.setEnabled(status);
                ivPower3.setEnabled(status);
                ivPower4.setEnabled(status);
                ivPower5.setEnabled(status);
                ivPower6.setEnabled(status);
                 */
                break;
        }
    }

    private int[] getTypeDevice(View item){
        int device[] = {0, 0};

        if (item instanceof ImageView) {
            if (item == ivBack)
                return device;

            device[0] = 1;
            if (item == ivLightOnOff)
                return device;

            int i = 0;
            device[0] = 2;
            for (ImageView temp : lightDevice) {
                if (temp == item) {
                    device[1] = i;
                    return device;
                }
                i++;
            }

            i = 0;
            device[0] = 3;
            for (ImageView temp : powerDevice) {
                if (temp == item) {
                    device[1] = i;
                    return device;
                }
                i++;
            }
        }
        device[0] = -1;
        return device;
    }

    public void buttonClickedLVR(View item){
        int device[] = getTypeDevice(item);

        //Toast.makeText(getApplicationContext(), getTypeDevice(item)+"", Toast.LENGTH_SHORT).show();
        //switch (item.getId()){
        switch (device[0]){
            case 0:
                finish();
                break;

            case 1:
                updateStatusDevice(statusComponent.getBtOnOff(), (ImageView) item, constants.getPathImageDeviceButton(), PATH_DEVICE+"/lonoff");
                controlComponent(statusComponent.getBtOnOff() == 1 ? false : true, 1);
                break;

            case 2:
                updateStatusDevice(statusComponent.getLoutUn(device[1]), (ImageView) item, constants.getPathImageDeviceLight(),
                        PATH_DEVICE+constants.getPathComodoOutType()[constants.getLIGHT()]+constants.getPathComodoOut()[device[1]]);
                break;

            case 3:
                updateStatusDevice(statusComponent.getPoutUn(device[1]), (ImageView) item, constants.getPathImageDevicePower(),
                        PATH_DEVICE+constants.getPathComodoOutType()[constants.getPOWER()]+constants.getPathComodoOut()[device[1]]);
                break;
            
            /*
            case R.id.ivBack:
                //Toast.makeText(getApplicationContext(), "btBoxBack On", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.ivLightOnOff:
                updateStatusDevice(statusComponent.getBtOnOff(), ivLightOnOff, constants.getPathImageDeviceButton(), constants.getPathComodo()[constants.getLIVING()]+"/lonoff");
                controlComponent(statusComponent.getBtOnOff() == 1 ? false : true, 1);
                break;

            case R.id.ivLight1:
                device = 0;
                updateStatusDevice(statusComponent.getLoutUn(device), ivLight1, constants.getPathImageDeviceLight(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getLIGHT()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivLight2:
                device = 1;
                updateStatusDevice(statusComponent.getLoutUn(device), ivLight2, constants.getPathImageDeviceLight(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getLIGHT()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivLight3:
                device = 2;
                updateStatusDevice(statusComponent.getLoutUn(device), ivLight3, constants.getPathImageDeviceLight(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getLIGHT()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivLight4:
                device = 3;
                updateStatusDevice(statusComponent.getLoutUn(device), ivLight4, constants.getPathImageDeviceLight(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getLIGHT()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivLight5:
                device = 4;
                updateStatusDevice(statusComponent.getLoutUn(device), ivLight5, constants.getPathImageDeviceLight(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getLIGHT()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivLight6:
                device = 5;
                updateStatusDevice(statusComponent.getLoutUn(device), ivLight6, constants.getPathImageDeviceLight(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getLIGHT()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivPower1:
                device = 0;
                updateStatusDevice(statusComponent.getPoutUn(device), ivPower1, constants.getPathImageDevicePower(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getPOWER()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivPower2:
                device = 1;
                updateStatusDevice(statusComponent.getPoutUn(device), ivPower2, constants.getPathImageDevicePower(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getPOWER()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivPower3:
                device = 2;
                updateStatusDevice(statusComponent.getPoutUn(device), ivPower3, constants.getPathImageDevicePower(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getPOWER()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivPower4:
                device = 3;
                updateStatusDevice(statusComponent.getPoutUn(device), ivPower4, constants.getPathImageDevicePower(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getPOWER()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivPower5:
                device = 4;
                updateStatusDevice(statusComponent.getPoutUn(device), ivPower5, constants.getPathImageDevicePower(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getPOWER()]+constants.getPathComodoOut()[device]);
                break;

            case R.id.ivPower6:
                device = 5;
                updateStatusDevice(statusComponent.getPoutUn(device), ivPower6, constants.getPathImageDevicePower(),
                        constants.getPathComodo()[typeDevice]+constants.getPathComodoOutType()[constants.getPOWER()]+constants.getPathComodoOut()[device]);
                break;
             */
        }
    }

    private void updateStatusDevice(byte statusComponente, ImageView iv, String[] pathImage, String pathFirebase){
        CommFirebase gate = new CommFirebase();
        try{
            boolean action = statusComponente == 1 ? false : true;
            //LivingRoomActivity.this.getResources().getIdentifier(pathImage[action ? 0 : 1], "drawable", LivingRoomActivity.this.getPackageName());
            this.getResources().getIdentifier(pathImage[action ? 0 : 1], "drawable", this.getPackageName());
            gate.sendDataInt(dbOutStatus, pathFirebase, action ? 1 : 0);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Não foi possível obter os dados", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateStatusComponent(){
        if (statusComponent.getBtOnOff() != 0) {
            //for
            //LivingRoomActivity.this.getResources().getIdentifier(pathImage[action ? 0 : 1], "drawable", LivingRoomActivity.this.getPackageName());

            int i = 0;
            for (ImageView temp : lightDevice){
                temp.setImageResource(statusComponent.getLoutUn(i++) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
                //i++;
            }

            i = 0;
            for (ImageView temp : powerDevice){
                temp.setImageResource(statusComponent.getPoutUn(i++) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
                //i++;
            }
/*
            ivLight1.setImageResource(statusComponent.getLoutUn(0) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight2.setImageResource(statusComponent.getLoutUn(1) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight3.setImageResource(statusComponent.getLoutUn(2) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight4.setImageResource(statusComponent.getLoutUn(3) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight5.setImageResource(statusComponent.getLoutUn(4) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivLight6.setImageResource(statusComponent.getLoutUn(5) == 0 ? R.drawable.lampadaoff : R.drawable.lampadaon);
            ivPower1.setImageResource(statusComponent.getPoutUn(0) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower2.setImageResource(statusComponent.getPoutUn(1) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower3.setImageResource(statusComponent.getPoutUn(2) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower4.setImageResource(statusComponent.getPoutUn(3) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower5.setImageResource(statusComponent.getPoutUn(4) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);
            ivPower6.setImageResource(statusComponent.getPoutUn(5) == 0 ? R.drawable.tomadaoff : R.drawable.tomadaon);

 */
        }else{
            /*
            ivLight1.setImageResource(R.drawable.lampadaoff);
            ivLight2.setImageResource(R.drawable.lampadaoff);
            ivLight3.setImageResource(R.drawable.lampadaoff);
            ivLight4.setImageResource(R.drawable.lampadaoff);
            ivLight5.setImageResource(R.drawable.lampadaoff);
            ivLight6.setImageResource(R.drawable.lampadaoff);
            ivPower1.setImageResource(R.drawable.tomadaoff);
            ivPower2.setImageResource(R.drawable.tomadaoff);
            ivPower3.setImageResource(R.drawable.tomadaoff);
            ivPower4.setImageResource(R.drawable.tomadaoff);
            ivPower5.setImageResource(R.drawable.tomadaoff);
            ivPower6.setImageResource(R.drawable.tomadaoff);
             */

            for (ImageView temp : lightDevice)
                temp.setImageResource(R.drawable.lampadaoff);

            for (ImageView temp : powerDevice)
                temp.setImageResource(R.drawable.tomadaoff);
        }
    }
}
