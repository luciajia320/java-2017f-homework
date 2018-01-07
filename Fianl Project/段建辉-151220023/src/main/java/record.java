import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class record implements Runnable {
    private Field field;

    record(Field field) {
        this.field = field;
    }
    @Override
    public void run() {
        File writeFile = new File("output.txt");

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));

            while (!field.getCompleted()) {
                //System.out.println("here");
                for (int i = 0; i < field.getWorld().size(); i++) {
                    out.write(field.getWorld().get(i).x() + " " + field.getWorld().get(i).y() + "\n");
                }
                Thread.sleep(100);
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
