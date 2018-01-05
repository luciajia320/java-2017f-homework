package cn.RailgunHamster.FinalHuluwaProject.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.*;
import java.net.URL;

public class Materials {
    /**
     * 地图行数
     */
    public static final int row = 8;
    /**
     * 地图列数
     */
    public static final int col = 16;
    /**
     * 帧数
     */
    public static final int frames = 120;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static Dimension defaultSize = new Dimension(screenSize.width * 8 / 9, screenSize.height * 8 / 9);

    ImageIcon getImageIcon(String name) {
        return this.__getImageIcon(name);
    }

    ImageIcon getImageIcon(String name, Dimension d) {
        ImageIcon imageIcon = this.__getImageIcon(name);
        if (imageIcon == null) {
            return null;
        }
        return new ImageIcon(this.getScaledImage(imageIcon.getImage(), new Rectangle(d)));
    }

    public Image getImage(String name) {
        ImageIcon imageIcon =  this.getImageIcon(name);
        if (imageIcon == null) {
            return null;
        }
        return imageIcon.getImage();
    }

    /**
     * @param name 资源名称
     * @param d 图片大小需求
     * @return 返回d大小的图片name
     */
    public Image getImage(String name, Dimension d) {
        ImageIcon imageIcon =  this.getImageIcon(name, d);
        if (imageIcon == null) {
            return null;
        }
        return imageIcon.getImage();
    }

    public BufferedImage getBufferedImage(String name) {
        try {
            return ImageIO.read(fileURL(name));
        } catch (IOException ioe) {
            return null;
        }
    }

    /**
     * @param srcImg 源图片
     * @param rectangle 目标大小
     * @return 返回经过resize的图片
     */
    Image getScaledImage(Image srcImg, Rectangle rectangle){
        BufferedImage resizedImg = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
        g2.dispose();

        return resizedImg;
    }

    public Image getBlackImage(Image srcImg) {
        BufferedImage resizedImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, srcImg.getWidth(null), srcImg.getHeight(null), null);
        g2.dispose();

        new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(resizedImg, resizedImg);

        return resizedImg;
    }

    public Image getImage(Class _class, Dimension d) {
        return this.getImage(_class.getSimpleName(), d);
    }

    public Image getImage(Class _class) {
        return this.getImage(_class.getSimpleName());
    }

    private ImageIcon __getImageIcon(String name) {
        URL location = fileURL(name);
        if (location == null) {
            return null;
        }
        return new ImageIcon(location);
    }

    private URL fileURL(String name) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL location = classLoader.getResource(name + ".jpg");
        if (location == null) {
            location = classLoader.getResource(name + ".png");
        }
        if (location == null) {
            location = classLoader.getResource("Null.png");
        }
        return location;
    }

    private final static String recordPath = "record.save";

    public ObjectOutputStream recordSave() {
        try {
            return new ObjectOutputStream(new FileOutputStream(new File(recordPath)));
        } catch (IOException ioe) {
            return null;
        }
    }

    public ObjectInputStream recordRead() {
        try {
            return new ObjectInputStream(new FileInputStream(new File(recordPath)));
        } catch (IOException ioe) {
            return null;
        }
    }

    /**
     * @return 获取地图的单位大小
     */
    public static Dimension getUnitSize() {
        return App.getApp().getFrame().getGround().getUnitSize();
    }

    /**
     * @return 获取地图的空闲区域大小
     */
    public static Dimension getSpace() {
        return App.getApp().getFrame().getGround().getSpace();
    }

    /**
     * @return 获取单位间隔
     */
    public static Dimension getUnitSpace() {
        return App.getApp().getFrame().getGround().getUnitSpace();
    }
}
