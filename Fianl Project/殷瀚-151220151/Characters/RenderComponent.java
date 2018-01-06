package Characters;

import Types.Vector2;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RenderComponent extends Component {
    private ImageInfo defaultImageInfo = new ImageInfo("Image/none.png");

    private class ImageInfo {
        private Image image;
        private int imageWidth, imageHeight, gestureNum = 1;

        ImageInfo(Image image, int imageWidth, int imageHeight, int gestureNum ) {
            this.image = image;
            this.imageWidth = imageWidth;
            this.imageHeight = imageHeight;
            this.gestureNum = gestureNum;
        }

        ImageInfo(String location) {
            URL loc = this.getClass().getClassLoader().getResource(location);
            ImageIcon imageIcon = new ImageIcon(loc);
            image = imageIcon.getImage();
            imageWidth = imageIcon.getIconWidth();
            imageHeight = imageIcon.getIconHeight();
            gestureNum = 1;
        }

    }
    private Map<ImageType, ImageInfo> images = new HashMap<ImageType, ImageInfo>() {{
        for (ImageType imageType: ImageType.values()) {
            put(imageType, defaultImageInfo);
        }
    }};

    private int gestureCount = 0, currentGestureNum = 1; // 根据这个数值选择图片中的一部分用来显示，从而显示出动态的图像。
    private Vector2 animationProgressStartCoordinate = new Vector2();
    private int animationProgressDurationTimes = 0;
    private double animationProgress = 1; // 根据这个数值来对图像显示位置进行微调，以显示出连续的位移过程。

    public RenderComponent(Creature creature) {
        creatureClient = creature;
    }

    public void changeToNextGesture() {
        gestureCount = (gestureCount + 1) % currentGestureNum;
    }

    public void startAnimationProgressWithDuration(int times, ImageType type, Vector2 startPoint) {
        animationProgress = 0;
        animationProgressDurationTimes = times;
        animationProgressStartCoordinate = startPoint;
        currentGestureNum = images.get(type).gestureNum;
    }

    public void updateMovingProgress() {
        animationProgress += (double)1/ animationProgressDurationTimes;
        if (animationProgress >= 1) {
            gestureCount = 0;
        }
    }

    public void resetMovingProgress() {
        animationProgress = 1;
        gestureCount = 0;
    }

    public void prepare(ImageType imageType, String location, int gestureNumber) {
        try {
            URL loc = this.getClass().getClassLoader().getResource(location);
            ImageIcon imageIcon = new ImageIcon(loc);
            Image image = imageIcon.getImage();
            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            images.put(imageType, new ImageInfo(image, width, height, gestureNumber));
        } catch (NullPointerException e) {
            //e.printStackTrace();
            System.out.println("图片的路径名可能有错！");
        } catch (Exception e) {
            System.out.println("为 " + creatureClient + " 初始化Image失败！");
        }

    }

    public void paintInGraphics(Graphics g, int positionWidth, int positionHeight) {
        try {
            ImageInfo imageInfo;
            if (!creatureClient.alive) {
                imageInfo = images.get(ImageType.TOMB);
            } else {
                switch (creatureClient.state) {
                    case MOVING: imageInfo = images.get(ImageType.MOVING); break;
                    case ATTACKING: imageInfo = images.get(ImageType.ATTACKING); break;
                    case IDLE: imageInfo = images.get(ImageType.IDLE); break;
                    default: imageInfo = images.get(ImageType.IDLE); break;
                }
            }
            int gestureImageWidth = imageInfo.imageWidth / imageInfo.gestureNum;
            int gestureImageHeight = imageInfo.imageHeight;
            // 移动过程中的坐标调整
            int offsetX = (int)((1- animationProgress)*(animationProgressStartCoordinate.getY()-creatureClient.position.getY())*positionWidth);
            int offsetY = (int)((1- animationProgress)*(animationProgressStartCoordinate.getX()-creatureClient.position.getX())*positionHeight);
            // 先计算图片右下角在field中的坐标，这样可以确定生物在图中“站立”的位置
            int rightBottomX = (int)((creatureClient.position.getY()+0.5)*positionWidth + 0.5*gestureImageWidth) + offsetX;
            int rightBottomY = (creatureClient.position.getX()+1)*positionHeight + offsetY;
            int leftTopX = rightBottomX - gestureImageWidth;
            int leftTopY = rightBottomY - gestureImageHeight;
            // 计算在源图片中截取部分的坐标
            int sourceLeftTopX = gestureCount*gestureImageWidth;
            int sourceLeftTopY = 0;
            int sourceRightBottomX = (gestureCount+1)*gestureImageWidth;
            int sourceRightBottomY = gestureImageHeight;

            // 血条
            if (creatureClient.alive) {
                g.drawImage(images.get(ImageType.HEALTH_BAR).image, leftTopX, leftTopY, positionWidth, 10, null);
                g.drawImage(images.get(ImageType.HEALTH_BAR_FILL).image, leftTopX, leftTopY, positionWidth * creatureClient.currentHealth / creatureClient.maxHealth, 10, null);
            }

            switch (creatureClient.faceDirection) {
                case LEFT: // 如果朝向左边，交换左上角和右下角的x坐标，使得显示的是水平翻转后的图片
                    g.drawImage(imageInfo.image,
                            rightBottomX, leftTopY,
                            leftTopX, rightBottomY,
                            sourceLeftTopX, sourceLeftTopY,
                            sourceRightBottomX, sourceRightBottomY,
                            null);
                    break;
                case RIGHT:
                    g.drawImage(imageInfo.image,
                            leftTopX, leftTopY,
                            rightBottomX, rightBottomY,
                            sourceLeftTopX, sourceLeftTopY,
                            sourceRightBottomX, sourceRightBottomY,
                            null);
                    break;
                default:
                    if (creatureClient instanceof Huluwa)
                        System.out.println("there");
                    break;
            }



            if(!creatureClient.position.isInSomeField()) {
                // 说明这个生物已经不在field中，可能出现了线程不安全的问题
                g.drawString("出现错误，已被移除", leftTopX, leftTopY);
            }
            //throw new Exception();
        } catch (NullPointerException e) {
            // 有未设置的图片
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum ImageType {
        ATTACKING, MOVING, IDLE, HEALTH_BAR, HEALTH_BAR_FILL, TOMB
    }
}
