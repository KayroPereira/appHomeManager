package com.example.apphomemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText eTLogin;
    private EditText eTPassword;
    private EditText geteTPasswordConfirm;

    private ProgressBar pBar;

    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();

        eTLogin = (EditText) findViewById(R.id.eTSSID);
        eTPassword = (EditText) findViewById(R.id.eTPassword);
        geteTPasswordConfirm = (EditText) findViewById(R.id.eTPasswordConfirm);

        pBar = (ProgressBar) findViewById(R.id.pBarDoor);

        btCadastrar = (Button) findViewById(R.id.btEnviarDoor);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        componentControl(true);
    }

    private void componentControl(boolean status){
        pBar.setVisibility(status ? View.INVISIBLE : View.VISIBLE);
        btCadastrar.setEnabled(status);
    }

    private void registerUser(){
        componentControl(false);

        if (TextUtils.isEmpty(eTLogin.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.fvEmail, Toast.LENGTH_LONG).show();
            componentControl(true);
            return;
        }

        if (TextUtils.isEmpty(eTPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.fvSenha, Toast.LENGTH_LONG).show();
            componentControl(true);
            return;
        }

        if (TextUtils.isEmpty(geteTPasswordConfirm.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.fvcSenha, Toast.LENGTH_LONG).show();
            componentControl(true);
            return;
        }

        if (!eTPassword.getText().toString().equals(geteTPasswordConfirm.getText().toString())){
            Toast.makeText(getApplicationContext(), R.string.senhaError, Toast.LENGTH_SHORT).show();
            componentControl(true);
            return;
        }

        mAuth.createUserWithEmailAndPassword(eTLogin.getText().toString(), eTPassword.getText().toString())
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), R.string.registroOk, Toast.LENGTH_LONG).show();
                    //progressBar.setVisibility(View.GONE);

                    Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.registroError, Toast.LENGTH_LONG).show();
                    //progressBar.setVisibility(View.GONE);
                }
            }
        });
        componentControl(true);
    }
}
