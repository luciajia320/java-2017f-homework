package nju.java;

import nju.java.common.GLOBAL;
import nju.java.field.Ground;

public class Main {
    public static void main(String[] args){
        GLOBAL.readConfigFile();
        Ground ground=new Ground();
    }
}
