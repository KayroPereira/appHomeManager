package com.example.apphomemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apphomemanager.Communication.CommFirebase;
import com.example.apphomemanager.GeneralUse.ConstantsApp;
import com.example.apphomemanager.GeneralUse.WaterTankData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaterTankActivity extends AppCompatActivity {

    private TextView tvBoxReservoirWT;
    private TextView tvBoxStatusWT;
    private TextView tvBoxErrWT;
    private TextView tvBoxStatusValueWT;


    private EditText etBox1;
    private EditText etBox2;
    private EditText etBox3;
    private EditText etBoxLH;
    private EditText etBoxLL;

    private Button btBoxSetupWT;
    private Button btBoxBackWT;

    private SeekBar skbModeWT;

    private int mode;
    private WaterTankData datasReservoir = new WaterTankData();

    final ConstantsApp constants = new ConstantsApp();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tank);

        tvBoxReservoirWT = (TextView) findViewById(R.id.tvBoxReservoirWT);
        tvBoxStatusWT = (TextView) findViewById(R.id.tvBoxStatusWT);
        tvBoxErrWT = (TextView) findViewById(R.id.tvBoxErrWT);
        tvBoxStatusValueWT = (TextView) findViewById(R.id.tvBoxStatusValueWT);

        etBox1 = (EditText) findViewById(R.id.etBox1);
        etBox2 = (EditText) findViewById(R.id.etBox2);
        etBox3 = (EditText) findViewById(R.id.etBox3);
        etBoxLH = (EditText) findViewById(R.id.etBoxLH);
        etBoxLL = (EditText) findViewById(R.id.etBoxLL);

        btBoxSetupWT = (Button) findViewById(R.id.btBoxSetupWT);
        btBoxBackWT = (Button) findViewById(R.id.btBoxBackWT);

        skbModeWT = (SeekBar) findViewById(R.id.skbModeWT);

        mode = getIntent().getExtras().getInt("mode");
        startComponents(mode);

        //Toast.makeText(getApplicationContext(), "Inicio", Toast.LENGTH_SHORT).show();

        skbModeWT.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                new CommFirebase().sendDataInt(dbOutStatus, constants.getPathReservoir()[mode]+constants.getPathDeviceSet()[mode],
                                                i == 2 ? constants.getHIGH() : i == 1 ? constants.getAUTO() : constants.getLOW());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        dbOutStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WaterTankData tempData = new CommFirebase().getDataWaterTank(dataSnapshot, constants.getPathReservoir()[mode]);

                if (isUpdateData(tempData))
                    startComponents(mode);

                //Toast.makeText(getApplicationContext(), "btBoxSend On", Toast.LENGTH_SHORT).show();

                //Log.w("Firebase", database.toString());
                /*
                statusComponent = new CommFirebase().getOutPut(dataSnapshot, "cix1");

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
                */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean isUpdateData(WaterTankData data){

        if ((data.getFcp() != datasReservoir.getFcp()) || (data.getErr() != datasReservoir.getErr()) ||
            (data.getLevel() != datasReservoir.getLevel()) || (data.getSx1() != datasReservoir.getSx1()) ||
            (data.getX1s() != datasReservoir.getX1s())) {
            datasReservoir = data;
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void buttonClickedWT(View item){

        switch (item.getId()){
            case R.id.btBoxBackWT:
                //Toast.makeText(getApplicationContext(), "btBoxBack On", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.btBoxSetupWT:
                //Toast.makeText(getApplicationContext(), "btBoxSend On", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(this, WaterTankSetupActivity.class);
                //it.putExtra("mode", new ConstantsApp().getCISTERN());
                it.putExtra("mode", new ConstantsApp().getWATER_TANK());
                startActivity(it);
                break;
        }
    }

    void updateData (WaterTankData data, int mode){
        String tempS[] = new String[data.getAddress().length];

        etBox1.setText(data.getAddress()[0]);
        etBoxLH.setText(data.getLh()+"");
        etBoxLL.setText(data.getLl()+"");

        switch(mode){
            case 0:
                etBox2.setText(data.getAddress()[1]);
                etBox3.setText(data.getAddress()[2]);
                break;

            case 1:
                break;
        }

        datasReservoir.setLh(data.getLh());
        datasReservoir.setLl(data.getLl());

        for (int i = 0; i < datasReservoir.getAddress().length; i++){
            tempS[i] = data.getAddress()[i];
        }
        datasReservoir.setAddress(tempS);
    }

    void startComponents(int mode){

        tvBoxReservoirWT.setText(mode == 0 ? getString(R.string.cisterna) : getString(R.string.caixa));
        tvBoxStatusWT.setText(datasReservoir.getFcp() == 0 ? getString(R.string.onLine) : getString(R.string.offLine));

        if (datasReservoir.getErr() == 0) {
            tvBoxErrWT.setVisibility(View.INVISIBLE);
        }else{
            tvBoxErrWT.setVisibility(View.VISIBLE);
            tvBoxErrWT.setText(constants.getErros()[datasReservoir.getErr()]);
        }
    }
}