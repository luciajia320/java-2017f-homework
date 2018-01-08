import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class rePlay implements Runnable{
    Field field;
    rePlay(Field field) {
        this.field = field;
    }

    @Override
    public void run() {
            File directory = new File("");
            JFileChooser jfc=new JFileChooser(directory.getAbsolutePath());
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showDialog(new JLabel(), "选择");
            File file = jfc.getSelectedFile();

            InputStreamReader reader;
            try {
                reader = new InputStreamReader(new FileInputStream(file));
                BufferedReader br = new BufferedReader(reader);
                String line;

                while ((line = br.readLine()) != null) {

                    for(Player w : field.getWorld()) {

                        if(Objects.equals(w.getClass().getSimpleName(), "grandFather")) {
                            continue;
                        }

                        String[] pos = line.split(" ");
                        int nX = Integer.parseInt(pos[0]);
                        int nY = Integer.parseInt(pos[1]);

                        if(Objects.equals(pos[2], "false")) {
                            w.setDeath();
                        }
                        else {
                            w.setX(nX);
                            w.setY(nY);
                        }
                        this.field.repaint();
                        Thread.sleep(1);

                        if((line = br.readLine()) != null) { continue; }
                        else { break; }
                    }
                }
                br.close();
                reader.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
