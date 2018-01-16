package nju.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class HumanTest {
    @Test
    public void getEnemies() throws Exception {
        Field field = new Field();

        for ( Human h: field.getHumans()) {

            ArrayList<Monster> enemies = h.getEnemies();
            String info = "";

            for (int i = 0; i < enemies.size(); i++){
                Assert.assertEquals(enemies.get(i), field.getMonsters().get(i));
            }

            for (Monster m : enemies){
                if ( m  instanceof LouLuo){
                    info += m.getClass().getSimpleName() + ((LouLuo) m).getId() + ", ";
                }
                else{
                    info += m.getClass().getSimpleName() + ", ";
                }
            }
            System.out.println(info);
        }
    }

}