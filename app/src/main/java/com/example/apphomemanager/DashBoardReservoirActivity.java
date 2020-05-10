package com.example.apphomemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apphomemanager.GeneralUse.ConstantsApp;

public class DashBoardReservoirActivity extends AppCompatActivity {

    private ImageView ivCisternDBR;
    private ImageView ivWaterTankDBR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dash_board_reservoir);

        ivCisternDBR = (ImageView) findViewById(R.id.ivCisternDBR);
        ivWaterTankDBR = (ImageView) findViewById(R.id.ivWaterTankDBR);

        ivCisternDBR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardReservoirActivity.this, WaterTankActivity.class);
                it.putExtra("mode", new ConstantsApp().getCISTERN());
                //it.putExtra("mode", new ConstantsApp().getWATER_TANK());
                startActivity(it);
            }
        });

        ivWaterTankDBR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DashBoardReservoirActivity.this, WaterTankActivity.class);
                //it.putExtra("mode", new ConstantsApp().getCISTERN());
                it.putExtra("mode", new ConstantsApp().getWATER_TANK());
                startActivity(it);
            }
        });
    }
}
