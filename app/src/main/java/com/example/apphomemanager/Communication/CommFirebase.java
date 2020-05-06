package com.example.apphomemanager.Communication;

import com.example.apphomemanager.GeneralUse.ComponentStatus;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class CommFirebase {

    public ComponentStatus getOutPut(DataSnapshot dataSnapshot, String room){
        ComponentStatus outPuts = new ComponentStatus();

        try {
            outPuts.setBtOnOff(Byte.parseByte(dataSnapshot.child(room).child("lonoff").getValue().toString()));

            for (int i = 0; i < dataSnapshot.child(room).child("light").getChildrenCount()-1; i++) {
                if (outPuts.getBtOnOff() !=0)
                    outPuts.setLoutUn(Byte.parseByte(dataSnapshot.child(room).child("light").child("out" + (i + 1)).getValue().toString()));
                else
                    outPuts.setLoutUn((byte) 0);
            }

            for (int i = 0; i < dataSnapshot.child(room).child("power").getChildrenCount()-1; i++) {
                if (outPuts.getBtOnOff() !=0)
                    outPuts.setPoutUn(Byte.parseByte(dataSnapshot.child(room).child("power").child("out" + (i + 1)).getValue().toString()));
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
}
