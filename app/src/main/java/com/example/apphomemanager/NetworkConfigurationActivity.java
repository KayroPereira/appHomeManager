package com.example.apphomemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apphomemanager.Communication.HttpService;
import com.example.apphomemanager.Communication.OnEventListener;
import com.example.apphomemanager.Model.dataNetWork;

import java.util.Timer;
import java.util.TimerTask;

public class NetworkConfigurationActivity extends AppCompatActivity {

    private EditText eTSSID;
    private EditText eTPassword;
    private TextView tvSSID;

    private ProgressBar pBar;

    private Button btEnviar;

    private Context context;

    private Timer timer;

    private static String URL_BOARD = "http://172.42.1.42/";

    @Override
    protected void onResume() {
        super.onResume();

        if (timer == null){
            timer = new Timer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networkconfiguration);

        context = this;

        eTSSID = (EditText) findViewById(R.id.eTSSID);
        eTPassword = (EditText) findViewById(R.id.eTPassword);

        pBar = (ProgressBar) findViewById(R.id.pBar);
        btEnviar = (Button) findViewById(R.id.btEnviar);

        tvSSID = (TextView) findViewById(R.id.tvSSID);

        componentControl(true, 3);

        if (timer == null){
            timer = new Timer();
        }

        Timer();

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void componentControl (boolean status, int mode){

        switch(mode){
            case 0:
                eTSSID.setEnabled(status);
                eTPassword.setEnabled(status);
                break;

            case 1:
                pBar.setVisibility(status ? View.VISIBLE : View.INVISIBLE);
                break;

            case 2:
                btEnviar.setEnabled(status);
                break;

            case 3:
                componentControl(status, 0);
                componentControl(!status, 1);
                componentControl(status, 2);
                break;
        }
    }

    private void registerUser(){

        if (TextUtils.isEmpty(eTSSID.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.fvssid, Toast.LENGTH_LONG).show();
            return;
        }

        if (eTSSID.getText().toString().length() > 35){
            Toast.makeText(getApplicationContext(), R.string.ssidErrorLength, Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(eTPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.fvpassnetword, Toast.LENGTH_LONG).show();
            return;
        }

        if (eTPassword.getText().toString().length() > 35){
            Toast.makeText(getApplicationContext(), R.string.passwordErrorLength, Toast.LENGTH_LONG).show();
            return;
        }

        componentControl(false, 3);
        HttpService httpService = new HttpService(new dataNetWork(URL_BOARD, "configWifi?ssid=", eTSSID.getText().toString(),
                "&password=", eTPassword.getText().toString(), ""), new OnEventListener<dataNetWork>() {

            @Override
            public void onSuccess(dataNetWork object) {
                try {
                    dataNetWork data = filterDate(object.getMessage());
                    if (eTSSID.getText().toString().equals(data.getParamm2()) && eTPassword.getText().toString().equals(data.getParamm4())) {
                        clearComponent();
                        Toast.makeText(getApplicationContext(), R.string.networkOk, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.networkError, Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){

                }
                componentControl(true, 3);
            }

            @Override
            public void onFailure(Exception e) {
                componentControl(true, 3);
                Toast.makeText(getApplicationContext(), R.string.networkError, Toast.LENGTH_LONG).show();
            }
        });
        httpService.execute();
    }

    void clearComponent(){
        eTSSID.setText("");
        eTPassword.setText("");
        eTSSID.requestFocus();
    }

    private String getConnection(){
        String connection = getString(R.string.unidentified);

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo;

        wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
            connection = wifiInfo.getSSID();
        }

        if (!connection.equals("<unknown ssid>"))
            return connection.replaceAll("\"", "");
        else
            return getString(R.string.unidentified);
    }

    public dataNetWork filterDate(String data){
        if (!data.equals("")) {
            return new dataNetWork("", "", data.substring(data.indexOf("SSID:") + 5, data.indexOf("Password:")),
                    "", data.substring(data.indexOf("Password:") + 9), "");
        }
        return new dataNetWork("","","","", "", "");
    }

    public void Timer(){
        Task task = new Task();

        timer.schedule(task, 0, 1000);
    }

    class Task extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String network = getConnection();

                    if (!network.equals(tvSSID.getText().toString().replace(getString(R.string.ssid), "").trim())) {
                        if (network.equals(getString(R.string.networkName)))
                            tvSSID.setTextColor(Color.BLUE);
                        else
                            tvSSID.setTextColor(Color.RED);

                        tvSSID.setText(getString(R.string.ssid) + " " + getConnection());

                        if (!network.equals(getString(R.string.networkName))) {
                            componentControl(false, 2);
                            new AlertDialog.Builder(context)
                                    .setTitle("Conexão")
                                    .setMessage("Favor conectar na rede " + getString(R.string.networkName) + " para configurar o dispositivo")
                                    .show();
                        }else
                            componentControl(true, 2);
                    }
                }
            });
        }
    }
}