package com.example.apphomemanager.Communication;

import android.util.Log;

import androidx.core.util.LogWriter;

import com.example.apphomemanager.GeneralUse.ComponentStatus;
import com.example.apphomemanager.GeneralUse.WaterTankData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;

public class CommFirebase {

    public ComponentStatus getOutPut(DataSnapshot dataSnapshot, String room){
        ComponentStatus outPuts = new ComponentStatus();

        try {
            outPuts.setBtOnOff(Byte.parseByte(dataSnapshot.child(room).child("lonoff").getValue().toString()));

            for (int i = 0; i < dataSnapshot.child(room).child("l").getChildrenCount()-1; i++) {
                if (outPuts.getBtOnOff() !=0)
                    outPuts.setLoutUn(Byte.parseByte(dataSnapshot.child(room).child("l").child("o" + (i + 1)).getValue().toString()));
                else
                    outPuts.setLoutUn((byte) 0);
            }

            for (int i = 0; i < dataSnapshot.child(room).child("p").getChildrenCount()-1; i++) {
                if (outPuts.getBtOnOff() !=0)
                    outPuts.setPoutUn(Byte.parseByte(dataSnapshot.child(room).child("p").child("o" + (i + 1)).getValue().toString()));
                else
                    outPuts.setPoutUn((byte) 0);
            }
        }catch (Exception e){

        }

        return outPuts;
    }

    public int getComponentStatus(DataSnapshot dataSnapshot, String path, String component){
        int valueComponent = -1;

        try {
            valueComponent = Integer.parseInt(dataSnapshot.child(path).child(component).getValue().toString());
        }catch (Exception e){

        }
        return valueComponent;
    }

    public ArrayList<String> getOutPutOld(DataSnapshot dataSnapshot){
        ArrayList<String> outPuts = new ArrayList<>();

        for (int i = 0; i < dataSnapshot.getChildrenCount(); i++){
            outPuts.add(dataSnapshot.child("out"+(i+1)).child("Valor").getValue().toString());
        }

        return outPuts;
    }

    public WaterTankData getDataWaterTank(DataSnapshot dataSnapshot, String mode){
        WaterTankData data = new WaterTankData();

        //(abx1, abx2, abx30 ou (ci), err, fcp, level, p1s, set{LH, LL}, sp1

        DataSnapshot path = dataSnapshot.child(mode);

        try {
            data.setErr(Integer.parseInt(path.child("err").getValue().toString()));
            data.setFcp(Integer.parseInt(path.child("fcp").getValue().toString()));
            data.setLevel(Integer.parseInt(path.child("level").getValue().toString()));
            data.setLl(Integer.parseInt(path.child("set").child("LL").getValue().toString()));
            data.setLh(Integer.parseInt(path.child("set").child("LH").getValue().toString()));

            switch(mode){
                case "cix1":         //cisterna
                    data.setAddress(new String[]{path.child("abx1").getValue().toString(),
                            path.child("abx2").getValue().toString(),
                            path.child("abx3").getValue().toString()});
                    data.setSx1(Integer.parseInt(path.child("sp1").getValue().toString()));
                    data.setX1s(Integer.parseInt(path.child("p1s").getValue().toString()));
                    break;

                case "cx1":         //caixa
                    data.setAddress(new String[]{path.child("ci").getValue().toString(), "", ""});
                    data.setSx1(Integer.parseInt(path.child("sv1").getValue().toString()));
                    data.setX1s(Integer.parseInt(path.child("v1s").getValue().toString()));
                    break;
            }

        }catch(Exception ex){
            Log.w("Firebase", "Erro no download / conversão dos dados firebase");
        }

        return data;
    }

    public void sendDataInt(DatabaseReference reference, String path, int value){
        //dbOutStatus.child("living").child("power").child("out4").setValue(action ? 1 : 0);
        //dbOutStatus.child("kitchen/l/o1").setValue(8);
        reference.child(path).setValue(value);
    }

    public void sendDataString(DatabaseReference reference, String path, String value){
        reference.child(path).setValue(value);
    }
}
