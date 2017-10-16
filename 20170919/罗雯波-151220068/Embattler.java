// 布阵器
public class Embattler {
    /**
     * positions: 待布阵的位置序列
     * formation: 阵型
     * posCoord: 领头者的位置坐标
     * orientation: 阵型朝向
     */
    public void embattle(Position[] positions, FORMATION formation,
                         PosCoord posCoord, ORIENTATION orientation) {
        if (positions.length <= 0) {
            return;
        }
        switch (formation) {
            case 鹤翼:
                break;
            case 雁行:
                break;
            case 衡轭:
                break;
            case 长蛇:
                embattleChangShe(positions, posCoord, orientation);
                break;
            case 鱼鳞:
                break;
            case 方圆:
                break;
            case 偃月:
                break;
            case 锋矢:
                break;
        }
    }

    private void embattleChangShe(Position[] positions, PosCoord posCoord,
                                  ORIENTATION orientation) {
        positions[0].setPosCoord(posCoord);
        for (int i = 1; i < positions.length; i ++) {
            ORIENTATION backOrientation = orientation.relativeOrientation(DIRECTION.BACK);
            PosCoord tempPC = positions[i - 1].getPosCoord().nextPosCoord(backOrientation);
            positions[i].setPosCoord(tempPC);
        }
    }
}

enum FORMATION {
    鹤翼, 雁行, 衡轭, 长蛇, 鱼鳞, 方圆, 偃月, 锋矢
}

enum ORIENTATION {
    EAST, SOUTH, WEST, NORTH; // 按照顺时针排列

    // 当前朝向转变 direction 方向后的朝向
    public ORIENTATION relativeOrientation(DIRECTION direction) {
        int length = values().length;
        int index = this.ordinal(), rltIndex = -1;
        switch (direction) {
            case LEFT:
                rltIndex = (index - 1 + length) % length;
                break;
            case RIGHT:
                rltIndex = (index + 1) % length;
                break;
            case BACK:
                rltIndex = (index + 2) % length;
                break;
        }
        return values()[rltIndex];
    }
}

enum DIRECTION {
    LEFT, RIGHT, BACK
}