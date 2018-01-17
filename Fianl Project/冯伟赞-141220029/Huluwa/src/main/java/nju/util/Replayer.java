package nju.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.model.ActionBean;
import nju.model.actor.Actor;
import nju.model.actor.ActorFactory;
import nju.view.Field;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Replayer {
    private static final String TAG = "Replayer";
    private List<ActionBean> beans = new ArrayList<>();
    private Field field;

    public Replayer(Field field) {
        this.field = field;
    }

    public void load(File file) throws FileNotFoundException {
        FileReader fr = new FileReader(file);
        Log.d(TAG, file.getAbsolutePath());
        Type type = new TypeToken<List<ActionBean>>(){}.getType();
        beans = new Gson().fromJson(fr, type);
        Log.d(TAG, "action length: " + beans.size() + "");
        field.getActors().clear();
        if (beans != null && beans.size() > 0) {
            ActorFactory factory = new ActorFactory();
            for (int i = 0; i < beans.size() && i < 50; i++) {
                ActionBean bean = beans.get(i);
                if (bean.isStarted()) {
                    Actor actor = factory.createActor(
                            bean.getType(), bean.getX(), bean.getY(), field);
                    if (!field.getActors().contains(actor)) {
                        actor.setId(bean.getId());
                        field.getActors().add(actor);
                    }
                }
            }
        }
        Log.d(TAG, "Actor size: " + field.getActors().size());

        this.field.repaint();

    }

    public void replay() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Random rand = new Random(System.currentTimeMillis());
                int count = 0;
                for (ActionBean bean : beans) {
                    if (bean == null) {
                        continue;
                    }
                    int id = bean.getId();
                    Actor actor = field.findActorById(id);

                    if (actor != null) {
                        actor.setLocation(bean.getX(), bean.getY());
                        actor.setAlive(bean.isAlive());
                        if (!actor.isAlive()) {
                            actor.setImageByResourceFile("img/dead.png");
                        }
                    }
                    if (count == 5) {
                        field.repaint();
//                        Log.d(TAG, "repaint");
                        try {
                            Thread.sleep(rand.nextInt(400) + 40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    count = (count + 1) % 10;
                }
            }
        }).start();

    }


}
