package com.example.apphomemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ConfigurationActivity extends AppCompatActivity {

    private ImageView ivCadastro;
    private ImageView ivConfiguracao;
    private ImageView ivCardRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        ivCadastro = (ImageView) findViewById(R.id.ivCadastro);
        ivConfiguracao = (ImageView) findViewById(R.id.ivConfiguracaoConf);
        ivCardRecord = (ImageView) findViewById(R.id.ivCardRecord);

        ivCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ConfigurationActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });

        ivConfiguracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ConfigurationActivity.this, NetworkConfigurationActivity.class);
                startActivity(it);
            }
        });

        ivCardRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ConfigurationActivity.this, doorLockActivity.class);
                startActivity(it);
            }
        });
    }
}
