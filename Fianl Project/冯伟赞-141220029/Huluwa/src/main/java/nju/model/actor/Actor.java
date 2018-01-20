package nju.model.actor;

import com.google.gson.Gson;

import nju.constant.GameParams;
import nju.constant.Values;
import nju.model.*;
import nju.util.Calc;
import nju.util.Log;
import nju.view.Field;

import java.io.File;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Actor extends Thing2D
        implements Runnable, Moveable, Fightable<Actor> {

    private static final String TAG = "Actor";
    private static AtomicInteger ID_AUTO_INCREMENT = new AtomicInteger(0);

    private int id;
    private String name;
    private Field field;
    private int strength = 10;
    private int type = Values.id.NONE;
    private int teamId;

    private boolean alive = true;

    public Actor(int x, int y, Field field) {
        super(x, y);
        setName(this.getClass().getSimpleName());
        this.field = field;
        this.id = ID_AUTO_INCREMENT.incrementAndGet();

    }

    @Override
    public void move(int dx, int dy) {
        int nx = this.getLocation().getX() + dx;
        int ny = this.getLocation().getY() + dy;
        boolean isMoveable = true;
        for (Actor actor : this.field.getActors()) {
            if (actor.isAlive()
                    && actor.getLocation().getY() == ny
                    && actor.getLocation().getX() == nx) {
                isMoveable = false;
            }
        }
        if (isMoveable) {
            this.setLocation(nx, ny);
        }
    }


    @Override
    public void stepUp() {
        move(0, -1);
    }

    @Override
    public void stepDown() {
        move(0, 1);
    }

    @Override
    public void stepLeft() {
        move(-1, 0);
    }

    @Override
    public void stepRight() {
        move(1, 0);
    }


    @Override
    public void setLocation(Location location) {
        location.update(location.getX(), location.getY(),
                0, this.field.getPlat().getWidth(),
                0, this.field.getPlat().getHeight());
        super.setLocation(location);
    }

    @Override
    public void setLocation(int x, int y) {
        this.getLocation().update(x, y,
                0, this.field.getPlat().getWidth() - 1,
                0, this.field.getPlat().getHeight() - 1);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    public Field getField() {
        return field;
    }


    @Override
    public boolean fight(Actor actor) {
        Random rand = new Random(System.currentTimeMillis());
        int strength1 = this.getStrength();
        int strength2 = actor.getStrength();
        int result = rand.nextInt(Math.max(strength1, strength2));
        if (strength1 > strength2) {
            if (result >= strength2) {
                return true;
            } else {
                return rand.nextBoolean();
            }
        } else {
            if (result >= strength1) {
                return false;
            } else {
                return rand.nextBoolean();
            }
        }
    }

    /**
     * Actor thread running method
     * 1. check enemies around;
     * 2. if enemies exist around then decide who to die;
     * 3. choose a direction to move;
     * 4. sleep random seconds;
     * 5. repaint ui;
     */
    @Override
    public void run() {

        Gson gson = new Gson();
        ActionBean startBean = new ActionBean(id, isAlive(), type, true,
                getLocation().getX(), getLocation().getY());
        this.field.getRecorder().add(startBean);
        while (!Thread.interrupted()
                && alive
                && !this.field.isCompleted()) {
            Random rand = new Random(System.currentTimeMillis());

            Location location = this.getLocation();
            int x = location.getX();
            int y = location.getY();

            int start = this.getField().getActors().indexOf(this);
            if (start != -1) {
                for (int i = start + 1; i < this.getField().getActors().size(); i++) {
                    Actor actor = this.getField().getActors().get(i);
                    synchronized (this) {
                        if (!alive) {
                            break;
                        }
                        synchronized (actor) {

                            if (actor.isAlive()
                                    && actor.getTeamId() != this.getTeamId()) {
                                int dis = Calc.distanceSquare(x, y,
                                        actor.getLocation().getX(),
                                        actor.getLocation().getY());
                                if (dis <= 1) {
                                    String info = null;
                                    ActionBean bean = null;
                                    if (this.fight(actor)) {
                                        actor.setAlive(false);
                                        actor.setImageByResourceFile("img/dead.png");
                                        Log.d(TAG, actor.getName() + " dead");
                                        bean = new ActionBean(
                                                actor.getId(),
                                                actor.isAlive(),
                                                actor.getType(),
                                                false,
                                                actor.getLocation().getX(),
                                                actor.getLocation().getY());
                                    } else {
                                        this.setAlive(false);
                                        this.setImageByResourceFile("img/dead.png");
                                        Log.d(TAG, this.getName() + " dead");
                                        bean = new ActionBean(
                                                this.getId(),
                                                this.isAlive(),
                                                this.getType(),
                                                false,
                                                this.getLocation().getX(),
                                                this.getLocation().getY());
                                    }
                                    this.getField().getRecorder().add(bean);
                                    info = gson.toJson(bean);
                                    Log.d(TAG, info);
                                }
                            }
                        }
                    }
                }
            }
            if (!alive) {
                break;
            }

            int l1, l2;
            int w = this.field.getPlat().getWidth();
            int h = this.field.getPlat().getHeight();


            if (x <= w / 2) {
                l1 = 1500;
            } else {
                l1 = 500;
            }

            if (y <= h / 2) {
                l2 = 3500;
            } else {
                l2 = 2500;
            }

            int d = rand.nextInt(4000);
            if (d < l1) {
                this.stepRight();
            } else if (d < 2000) {
                this.stepLeft();
            } else if (d < l2) {
                this.stepDown();
            } else {
                this.stepUp();
            }
//            switch (d % 4) {
//                case 0:
//                    this.stepDown();
//                    break;
//                case 1:
//                    this.stepUp();
//                    break;
//                case 2:
//                    this.stepLeft();
//                    break;
//                case 3:
//                    this.stepRight();
//                    break;
//                default:
//                    break;
//            }
            ActionBean bean = new ActionBean(id, isAlive(), type, false,
                    this.getLocation().getX(),
                    this.getLocation().getY());
            this.field.getRecorder().add(bean);
            String info = gson.toJson(bean);
            Log.d(TAG, info);


            try {
                Thread.sleep(rand.nextInt(400) + 40);
                getField().repaint();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return getId() == actor.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
