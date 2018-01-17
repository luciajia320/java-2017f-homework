package wz.Creature;

import wz.Creature.*;

public class HuluTree {
    private static String[] names = {"大","二","三","四","五","六","七"};
    private static String[] colors = {"红","橙","黄","绿","青","蓝","紫"};

    public static Huluwa[] createHuluwas(){
        Huluwa[] huluwas = new Huluwa[7];
        for(int i = 0; i < huluwas.length; i++) {
            huluwas[i] = new Huluwa(names[i], colors[i], i+1);
        }
        return huluwas;
    }
}
