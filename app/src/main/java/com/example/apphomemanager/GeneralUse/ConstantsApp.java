package com.example.apphomemanager.GeneralUse;

public class ConstantsApp {

    final int CISTERN = 0;
    final int WATER_TANK = 1;
    final int LOW = 0;
    final int HIGH = 1;
    final int AUTO = 2;
    final int LIVING = 0;
    final int KITCHEN = 1;
    final int LIGHT = 0;
    final int POWER = 1;

    final String pathComodo[] = {"living", "kitchen"};
    final String pathComodoOutType[] = {"/l", "/p"};
    final String pathComodoOut[] = {"/o1", "/o2", "/o3", "/o4", "/o5", "/o6", "/o7", "/o8", "/o9", "/o10", "/o11", "/o12", "/o13", "/o14", "/o15", "/o16" };

    final String pathImageDeviceButton[] = {"btoff", "bton"};
    final String pathImageDeviceLight[] = {"lampadaoff", "lampadaon"};
    final String pathImageDevicePower[] = {"tomadaoff", "tomadaon"};

    final String climaStatus[] = {"storm", "snow", "hail", "rain", "fog", "clear_day", "clear_night", "cloud", "cloudly_day", "cloudly_night", "none_day", "none_night"};
    final String climaImages[] = {"storm", "snow", "snow", "rain", "fog", "clear_day", "clear_night", "cloudly_day", "cloudly_day", "cloudly_night", "none_day", "none_night"};

    final String temperaturaImages[] = {"tlow", "taverage", "thigh"};

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

    public int getLIGHT() {
        return LIGHT;
    }

    public int getPOWER() {
        return POWER;
    }

    public String[] getPathImageDevicePower() {
        return pathImageDevicePower;
    }

    public String[] getPathImageDeviceLight() {
        return pathImageDeviceLight;
    }

    public int getLIVING() {
        return LIVING;
    }

    public int getKITCHEN() {
        return KITCHEN;
    }

    public String[] getPathImageDeviceButton() {
        return pathImageDeviceButton;
    }

    public String[] getTemperaturaImages() {
        return temperaturaImages;
    }

    public String[] getPathComodo() {
        return pathComodo;
    }

    public String[] getPathComodoOutType() {
        return pathComodoOutType;
    }

    public String[] getPathComodoOut() {
        return pathComodoOut;
    }

    public String[] getClimaStatus() {
        return climaStatus;
    }

    public String[] getClimaImages() {
        return climaImages;
    }

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
