package nju;


import nju.constant.AppConfig;
import nju.constant.Values;
import nju.util.Log;
import nju.view.Ground;

import static nju.util.Print.print;


public class Main {

    private static final String TAG = "Main";
    private static final String GAME_TAG = AppConfig.NAME;

    public static void main(String[] args) {
        print(Values.AnsiColor.ANSI_CYAN, "loading...");
        print(Values.AnsiColor.ANSI_CYAN, "Author - " + AppConfig.AUTHOR);
        print(Values.AnsiColor.ANSI_CYAN, "Game's name - " + AppConfig.NAME);
        print(Values.AnsiColor.ANSI_CYAN, "Version - " + AppConfig.VERSION);
//        --------------------------------------------------------------------

        Ground ground = new Ground();
        ground.setVisible(true);


//        --------------------------------------------------------------------

        print(Values.AnsiColor.ANSI_PURPLE, "---------------------------------------------");
        print(Values.AnsiColor.ANSI_CYAN, "------ START ------");
    }

}
