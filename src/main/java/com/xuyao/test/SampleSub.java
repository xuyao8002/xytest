package com.xuyao.test;

public class SampleSub extends Sample{
    public String sub;

    String nameSub;

    private static String genderSub;

    protected static int ageSub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getNameSub() {
        return nameSub;
    }

    public void setNameSub(String nameSub) {
        this.nameSub = nameSub;
    }

    public static String getGenderSub() {
        return genderSub;
    }

    public static void setGenderSub(String genderSub) {
        SampleSub.genderSub = genderSub;
    }

    public static int getAgeSub() {
        return ageSub;
    }

    public static void setAgeSub(int ageSub) {
        SampleSub.ageSub = ageSub;
    }
}