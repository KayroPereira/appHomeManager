package com.example.apphomemanager.GeneralUse;

public class ConstantsApp {

    final int CISTERN = 0;
    final int WATER_TANK = 1;
    final int LOW = 0;
    final int HIGH = 1;
    final int AUTO = 2;

    final String pathReservoir[] = {"cix1", "cx1"};
    final String pathStatusDevice[] = {"/sp1", "/sv1"};
    final String pathDeviceSet[] = {"/p1s", "/v1s"};
    final String pathLevelH = "/set/LH";
    final String pathLevelL = "/set/LL";
    final String pathFcp = "/fcp";
    final String pathErr = "/err";
    final String pathLevel = "/level";

    final String imagePath[] = {"ct", "wt"};
    final int imageLength = 20;

    final String erros[] = {"erro1", "erro2", "erro3"};

    public String[] getImagePath() {
        return imagePath;
    }

    public int getImageLength() {
        return imageLength;
    }

    public int getCISTERN() {
        return CISTERN;
    }

    public int getWATER_TANK() {
        return WATER_TANK;
    }

    public int getLOW() {
        return LOW;
    }

    public int getHIGH() {
        return HIGH;
    }

    public int getAUTO() {
        return AUTO;
    }

    public String[] getPathReservoir() {
        return pathReservoir;
    }

    public String[] getPathStatusDevice() {
        return pathStatusDevice;
    }

    public String[] getPathDeviceSet() {
        return pathDeviceSet;
    }

    public String getPathLevelH() {
        return pathLevelH;
    }

    public String getPathLevelL() {
        return pathLevelL;
    }

    public String getPathFcp() {
        return pathFcp;
    }

    public String getPathErr() {
        return pathErr;
    }

    public String getPathLevel() {
        return pathLevel;
    }

    public String[] getErros() {
        return erros;
    }
}
