package com.example.apphomemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apphomemanager.Communication.CommFirebase;
import com.example.apphomemanager.GeneralUse.WaterTankData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaterTankActivity extends AppCompatActivity {

    private TextView tvBox1;
    private TextView tvBox2;
    private TextView tvBox3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tank);

        tvBox1 = (TextView) findViewById(R.id.tvBox1);
        tvBox2 = (TextView) findViewById(R.id.tvBox2);
        tvBox3 = (TextView) findViewById(R.id.tvBox3);

        tvBox1.setText(getString(R.string.caixa) + " 1");
        tvBox2.setText(getString(R.string.caixa) + " 2");
        tvBox3.setText(getString(R.string.caixa) + " 3");

        dbOutStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                WaterTankData data = new CommFirebase().getDataWaterTank(dataSnapshot, "cix1");
                WaterTankData data1 = new CommFirebase().getDataWaterTank(dataSnapshot, "cx1");

                Log.w("Firebase", database.toString());
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
}
