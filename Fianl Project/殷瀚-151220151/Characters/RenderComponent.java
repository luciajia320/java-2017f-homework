package Characters;

import Types.Vector2;

import java.awt.*;

public class RenderComponent {
    private Creature creatureClient;
    protected Image image;
    protected int imageSizeX, imageSizeY, gestureNum;

    private int gestureCount = 0; // 根据这个数值选择图片中的一部分用来显示，从而显示出动态的图像。
    private Vector2 movingProgressStartCoordinate = new Vector2();
    private int movingProgressDurationTimes = 0;
    private double movingProgress = 1; // 根据这个数值来对图像显示位置进行微调，以显示出连续的位移过程。

    public RenderComponent(Creature creature) {
        creatureClient = creature;
    }

    public void changeToNextMovingGesture() {
        gestureCount = (gestureCount + 1) % gestureNum;
    }

    public void startMovingProgressWithDuration(int times, Vector2 startPoint) {
        movingProgress = 0;
        movingProgressDurationTimes = times;
        movingProgressStartCoordinate = startPoint;
    }

    public void updateMovingProgress() {
        movingProgress += (double)1/movingProgressDurationTimes;
    }


    public void paintInGraphics(Graphics g, int positionWidth, int positionHeight) {
        try {
            int gestureImageWidth = imageSizeX/gestureNum;
            int gestureImageHeight = imageSizeY;
            // 移动过程中的坐标调整
            int offsetX = (int)((1-movingProgress)*(movingProgressStartCoordinate.getY()-creatureClient.position.getY())*positionWidth);
            int offsetY = (int)((1-movingProgress)*(movingProgressStartCoordinate.getX()-creatureClient.position.getX())*positionHeight);
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

            g.drawImage(image,
                    leftTopX, leftTopY,
                    rightBottomX, rightBottomY,
                    sourceLeftTopX, sourceLeftTopY,
                    sourceRightBottomX, sourceRightBottomY,
                    null);

            if(!creatureClient.position.isInSomeField()) {
                g.drawString("线程错误，已被移除", leftTopX, leftTopY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
