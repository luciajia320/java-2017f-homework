package common;

public interface Constant {
    // 窗口宽度和长度
    int FRAMEWIDTH = 1800, FRAMEHEIGHT = 960;
    // 窗口初始显示位置
    int LOCATIONX = 0, LOCATIONY = 0;
    // 窗口标题
    String TITLE = "Huluwa Game_151220127";
    // 场地的宽度和高度
    int GROUNDWIDTH = 15, GROUNDHEIGHT = 7;
    // 根据场地计算的最大距离
//    int MAXDIS = GROUNDWIDTH * GROUNDWIDTH + GROUNDHEIGHT * GROUNDHEIGHT;
    int MAXDIS = 274;
    // 角色图像大小(单位:像素)
    int IMGSIZE = 120;

    // 随机数种子
    int SEED = 100;

    // 阵型数据
    int GOOSESWINGWIDTH = 4, GOOSESWINGHEIGHT = 7;
    int XWIDTH = 2, XHEIGHT = 7;

    // 全局状态, 0: 开始界面; 1: 游戏进行中; 2: 暂停; 3: 终止; 4: 读取存档
    enum STATE { READY, GOING, PAUSE, OVER, SAVE };

    String IMGPATH = "src/main/java/images/";
    String SAVEPATH = "src/main/java/save/";
}
