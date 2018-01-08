import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class record implements Runnable {

    private Field field;

    record(Field field) {

        this.field = field;
    }

    @Override
    public void run() {

        File writeFile = new File("theBeforeOne.txt");

        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));

            while (!field.getCompleted()) {

                for (int i = 0; i < field.getWorld().size(); i++) {

                    Player tempP = field.getWorld().get(i);
                    if(Objects.equals(tempP.getClass().getSimpleName(), "grandFather")) {
                        continue;
                    }

                    out.write(tempP.x() + " " + tempP.y() + " " +tempP.isAlive()  + "\n");
                }

                Thread.sleep(150);
            }

            out.flush();
            out.close();

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
