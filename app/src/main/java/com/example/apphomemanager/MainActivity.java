package com.example.apphomemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.example.apphomemanager.GeneralUse.ConstantsApp;
import com.example.apphomemanager.GeneralUse.ConstantsApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener myAuthListener;

    private ProgressBar pBar;
    private EditText login;
    private EditText password;
    private Button bTLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RelativeLayout layoutMain = (RelativeLayout) findViewById(R.id.layoutMain);

        bTLogin = (Button) findViewById(R.id.btEnviarDoor);
        login = (EditText) findViewById(R.id.eTSSID);
        password = (EditText) findViewById(R.id.eTPassword);

        pBar = (ProgressBar) findViewById(R.id.pBarDoor);
        //pBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        //bTSalvar.setOnClickListener(this);

        //TextView tvCadastro = (TextView) findViewById(R.id.tvCadastro);
        //TextView tvNetworkConfig = (TextView) findViewById(R.id.tvNetworkConfig);
/*
        myAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =  firebaseAuth.getCurrentUser();

                if (user != null){
                    Log.d("AUTH", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("AUTH", "onAuthStateChanged:signed_out");
                }
            }
        };
*/
        bTLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //remover
                //Intent it = new Intent(MainActivity.this, DashBoardActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putInt("mode", new ConstantsApp().getCISTERN());
                //Intent it = new Intent(MainActivity.this, WaterTankSetupActivity.class);
                Intent it = new Intent(MainActivity.this, WaterTankActivity.class);
                //it.putExtra("mode", new ConstantsApp().getCISTERN());
                it.putExtra("mode", new ConstantsApp().getWATER_TANK());
                //it.putExtra("mode", 0);
                startActivity(it);

                //habilitar
                //getLogin(login.getText().toString(), password.getText().toString());
            }
        });
/*
        tvCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });

        tvNetworkConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, NetworkConfigurationActivity.class);
                startActivity(it);
            }
        });
        */
        componentControl(true);
    }

    private void componentControl(boolean status){
        pBar.setVisibility(status ? View.INVISIBLE : View.VISIBLE);
        bTLogin.setEnabled(status);
    }

    private void getLogin(String login, String password){

        componentControl(false);

        if (TextUtils.isEmpty(login)) {
            Toast.makeText(getApplicationContext(), R.string.fvEmail, Toast.LENGTH_LONG).show();
            componentControl(true);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.fvSenha, Toast.LENGTH_LONG).show();
            componentControl(true);
            return;
        }

        mAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Log.w("AUTH", "Falha ao efetuar o login: ", task.getException());

                    Toast.makeText(getApplicationContext(), "Uusuário não encontrado", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("AUTH", "Login Efetuado com sucesso!!!");

                    Intent it = new Intent(MainActivity.this, DashBoardActivity.class);
                    startActivity(it);
                }
            }
        });
        componentControl(true);
/*
        Intent it = new Intent(MainActivity.this, DashBoardActivity.class);
        //Intent it = new Intent(MainActivity.this, lightActivity.class);
        startActivity(it);
*/
    }

    @Override
    public void onResume() {
        super.onResume();

        login.setText("");
        password.setText("");
        login.requestFocus();
    }
/*
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(myAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (myAuthListener != null) {
            mAuth.removeAuthStateListener(myAuthListener);
        }
    }
*/
    /*
    @Override
    public void onClick(View view) {


        EditText login = (EditText) findViewById(R.id.eTLogin);
        EditText password = (EditText) findViewById(R.id.eTPassword);

        mAuth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Log.w("AUTH", "Falha ao efetuar o Login: ", task.getException());

                    Context contexto = getApplicationContext();
                    String texto = "Uusuário não encontrado";
                    int duracao = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(contexto, texto,duracao);
                    toast.show();
                }else{
                    Log.d("AUTH", "Login Efetuado com sucesso!!!");

                    Intent it = new Intent(MainActivity.this, lightActivity.class);
                    startActivity(it);
                }
            }
        });
        */

        /*
        if (user != null){
            Intent it = new Intent(MainActivity.this, lightActivity.class);
            startActivity(it);
        }else{
            Context contexto = getApplicationContext();
            String texto = "Uusuário não encontrado";
            int duracao = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(contexto, texto,duracao);
            toast.show();
        }
        */
    //}
}
