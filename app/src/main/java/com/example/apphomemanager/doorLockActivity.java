package com.example.apphomemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphomemanager.Communication.HttpService;
import com.example.apphomemanager.Communication.OnEventListener;
import com.example.apphomemanager.Model.dataNetWork;

import java.util.Timer;
import java.util.TimerTask;

public class doorLockActivity extends AppCompatActivity {

    private TextView tvSSIDDoor;

    private ProgressBar pBarDoor;

    private Button btEnviarDoor;

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
        setContentView(R.layout.activity_door_lock);

        context = this;

        pBarDoor = (ProgressBar) findViewById(R.id.pBarDoor);
        btEnviarDoor = (Button) findViewById(R.id.btEnviarDoor);

        tvSSIDDoor = (TextView) findViewById(R.id.tvSSIDDoor);

        componentControl(true, 3);

        if (timer == null){
            timer = new Timer();
        }

        Timer();

        btEnviarDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardRecord();
            }
        });
    }

    private void cardRecord(){
        componentControl(false, 3);
        HttpService httpService = new HttpService(new dataNetWork(URL_BOARD,"cardRecord", "",
                "", "", ""), new OnEventListener<dataNetWork>() {
            @Override
            public void onSuccess(dataNetWork object) {
                try {
                    Toast.makeText(getApplicationContext(), R.string.networkOk, Toast.LENGTH_LONG).show();

                    //Toast.makeText(getApplicationContext(), R.string.networkError, Toast.LENGTH_LONG).show();
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

    private void componentControl (boolean status, int mode){

        switch(mode){
            case 1:
                pBarDoor.setVisibility(status ? View.VISIBLE : View.INVISIBLE);
                break;

            case 2:
                btEnviarDoor.setEnabled(status);
                break;

            case 3:
                componentControl(!status, 1);
                componentControl(status, 2);
                break;
        }
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

    public void Timer(){
        doorLockActivity.Task task = new doorLockActivity.Task();

        timer.schedule(task, 0, 1000);
    }

    class Task extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String network = getConnection();

                    if (!network.equals(tvSSIDDoor.getText().toString().replace(getString(R.string.ssid), "").trim())) {
                        if (network.equals(getString(R.string.networkName)))
                            tvSSIDDoor.setTextColor(Color.BLUE);
                        else
                            tvSSIDDoor.setTextColor(Color.RED);

                        tvSSIDDoor.setText(getString(R.string.ssid) + " " + getConnection());

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
