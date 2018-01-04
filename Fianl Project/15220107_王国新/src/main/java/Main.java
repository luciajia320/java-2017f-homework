import java.awt.FileDialog;

public class Main {
    public static void main(String[] args) {
        Ground ground = new Ground();
        FileDialog fd = new FileDialog(ground,"选择回放文件",FileDialog.LOAD);
        fd.setLocationRelativeTo(null);
        Field field = new Field();
        field.addFileDialog(fd);
        ground.setSize(field.getBoardWidth() + 50, field.getBoardHeight () + 2*50);
        ground.add(field);
        ground.setVisible(true);
        ground.setLocationRelativeTo(null);
    }
}
