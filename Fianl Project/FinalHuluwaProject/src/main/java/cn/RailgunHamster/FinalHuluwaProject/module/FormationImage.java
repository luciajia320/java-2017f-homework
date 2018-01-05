package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.Materials;
import cn.RailgunHamster.FinalHuluwaProject.gui.Pair;
import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 通过图片计算阵型，Image -> List<面积, 位置>
 */
public class FormationImage {
    private Dimension size;
    private BufferedImage img;

    public FormationImage(String name, Dimension size) {
        this.size = size;
        this.img = new Materials().getBufferedImage(name);
    }

    public List<Pair<Double, Position>> read() {
        List<Pair<Double, Position>> available = new ArrayList<>();
        int height = img.getHeight() / size.height;
        int width = img.getWidth() / size.width;
        for (int k = 0;k < size.width * size.height;k++) {
            int startX = width * (k % size.width);
            int startY = height * (k / size.width);
            double count = 0;
            for (int i = startX;i < startX + width;i++) {
                for (int j = startY;j < startY + height;j++) {
                    int rgb = 0;
                    try {
                        rgb = img.getRGB(i, j);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.print("out");
                    }
                    if (rgb != -1) count++;
                }
            }
            double scale = count / (height * width);
            available.add(new Pair<>(scale, new Position(k / size.width, k % size.width)));
        }
        available.sort(Comparator.comparing((Pair<Double, Position> pair) -> pair.getKey()).reversed());
        return available;
    }
}
