// 布阵器
public class Embattler {
    /**
     * queue: 待布阵的队列
     * formation: 阵型
     * posCoord: 领头者的位置坐标
     * orientation: 阵型朝向
     */
    public void embattle(Queue queue, FORMATION formation,
                         PosCoord posCoord, ORIENTATION orientation) {
        embattle(queue.getPositions(), formation, posCoord, orientation);
    }

    private void embattle(Position[] positions, FORMATION formation,
                         PosCoord posCoord, ORIENTATION orientation) {
        if (positions.length <= 0) {
            return;
        }
        positions[0].setPosCoord(posCoord);
        switch (formation) {
            case 鹤翼:
                embattleHeYi(positions, posCoord, orientation);
                break;
            case 雁行:
                embattleYanXing(positions, posCoord, orientation);
                break;
            case 衡轭:
                embattleHengE(positions, posCoord, orientation);
                break;
            case 长蛇:
                embattleChangShe(positions, posCoord, orientation);
                break;
            case 鱼鳞:
                embattleYuLin(positions, posCoord, orientation);
                break;
            case 方圆:
                embattleFangYuan(positions, posCoord, orientation);
                break;
            case 偃月:
                embattleYanYue(positions, posCoord, orientation);
                break;
            case 锋矢:
                embattleFengShi(positions, posCoord, orientation);
                break;
        }
    }

    private void embattleHengE(Position[] positions, PosCoord posCoord,
                               ORIENTATION orientation) {
        ORIENTATION backOrientation = orientation.relativeOrientation(DIRECTION.BACK),
                leftOrientation = orientation.relativeOrientation(DIRECTION.LEFT),
                rightOrientation = orientation.relativeOrientation(DIRECTION.RIGHT);
        PosCoord prePC = positions[0].getPosCoord();
        for (int i = 1; i < positions.length; i ++) {
            PosCoord tempPC = prePC.nextPosCoord(backOrientation).nextPosCoord(leftOrientation);
            prePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = prePC.nextPosCoord(backOrientation).nextPosCoord(rightOrientation);
            prePC = tempPC;
            positions[i].setPosCoord(tempPC);
        }
    }

    private void embattleYuLin(Position[] positions, PosCoord posCoord,
                               ORIENTATION orientation) {
        ORIENTATION backOrientation = orientation.relativeOrientation(DIRECTION.BACK),
                leftOrientation = orientation.relativeOrientation(DIRECTION.LEFT),
                rightOrientation = orientation.relativeOrientation(DIRECTION.RIGHT);
        PosCoord frontPrePC = positions[0].getPosCoord(),
                leftPrePC = positions[0].getPosCoord().nextPosCoord(leftOrientation),
                rightPrePC = positions[0].getPosCoord().nextPosCoord(rightOrientation);
        for (int i = 1; i < positions.length; i ++) {
            PosCoord tempPC = leftPrePC.nextPosCoord(backOrientation).nextPosCoord(leftOrientation);
            leftPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = frontPrePC.nextPosCoord(backOrientation);
            frontPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = rightPrePC.nextPosCoord(backOrientation).nextPosCoord(rightOrientation);
            positions[i].setPosCoord(tempPC);

            rightPrePC = leftPrePC;
            leftPrePC = tempPC;
        }
    }

    private void embattleYanYue(Position[] positions, PosCoord posCoord,
                                ORIENTATION orientation) {
        ORIENTATION backOrientation = orientation.relativeOrientation(DIRECTION.BACK),
                leftOrientation = orientation.relativeOrientation(DIRECTION.LEFT),
                rightOrientation = orientation.relativeOrientation(DIRECTION.RIGHT);
        if (positions.length <= 1) {
            return;
        }
        positions[1].setPosCoord(positions[0].getPosCoord().nextPosCoord(leftOrientation));
        if (positions.length <= 2) {
            return;
        }
        positions[2].setPosCoord(positions[0].getPosCoord().nextPosCoord(rightOrientation));

        PosCoord leftPrePC = positions[1].getPosCoord(), rightPrePC = positions[2].getPosCoord();
        for (int i = 3; i < positions.length; i ++) {
            PosCoord tempPC = leftPrePC.nextPosCoord(backOrientation).nextPosCoord(leftOrientation);
            leftPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = rightPrePC.nextPosCoord(backOrientation).nextPosCoord(rightOrientation);
            rightPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
        }
    }

    private void embattleFangYuan(Position[] positions, PosCoord posCoord,
                                  ORIENTATION orientation) {
        ORIENTATION backOrientation = orientation.relativeOrientation(DIRECTION.BACK),
                leftOrientation = orientation.relativeOrientation(DIRECTION.LEFT),
                rightOrientation = orientation.relativeOrientation(DIRECTION.RIGHT);
        PosCoord leftPrePC = positions[0].getPosCoord(), rightPrePC = leftPrePC;
        int i;
        for (i = 1; i <= positions.length / 2; i ++) {
            PosCoord tempPC = leftPrePC.nextPosCoord(backOrientation).nextPosCoord(leftOrientation);
            leftPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = rightPrePC.nextPosCoord(backOrientation).nextPosCoord(rightOrientation);
            rightPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
        }
        for (; i < positions.length; i ++) {
            PosCoord tempPC = leftPrePC.nextPosCoord(backOrientation).nextPosCoord(rightOrientation);
            leftPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = rightPrePC.nextPosCoord(backOrientation).nextPosCoord(leftOrientation);
            rightPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
        }
    }

    private void embattleFengShi(Position[] positions, PosCoord posCoord,
                                 ORIENTATION orientation) {
        ORIENTATION backOrientation = orientation.relativeOrientation(DIRECTION.BACK),
                leftOrientation = orientation.relativeOrientation(DIRECTION.LEFT),
                rightOrientation = orientation.relativeOrientation(DIRECTION.RIGHT);
        PosCoord leftPrePC = positions[0].getPosCoord(), rightPrePC = leftPrePC, frontPrePC = leftPrePC;
        for (int i = 1; i < positions.length; i ++) {
            PosCoord tempPC = leftPrePC.nextPosCoord(backOrientation).nextPosCoord(leftOrientation);
            leftPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = frontPrePC.nextPosCoord(backOrientation);
            frontPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = rightPrePC.nextPosCoord(backOrientation).nextPosCoord(rightOrientation);
            rightPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
        }
    }

    private void embattleYanXing(Position[] positions, PosCoord posCoord,
                                 ORIENTATION orientation) {
        ORIENTATION backOrientation = orientation.relativeOrientation(DIRECTION.BACK),
                leftOrientation = orientation.relativeOrientation(DIRECTION.LEFT),
                rightOrientation = orientation.relativeOrientation(DIRECTION.RIGHT);
        PosCoord leftPrePC = positions[0].getPosCoord(), rightPrePC = leftPrePC;
        for (int i = 1; i < positions.length; i ++) {
            PosCoord tempPC = leftPrePC.nextPosCoord(orientation).nextPosCoord(leftOrientation);
            leftPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = rightPrePC.nextPosCoord(backOrientation).nextPosCoord(rightOrientation);
            rightPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
        }
    }

    private void embattleHeYi(Position[] positions, PosCoord posCoord,
                              ORIENTATION orientation) {
        ORIENTATION backOrientation = orientation.relativeOrientation(DIRECTION.BACK),
                leftOrientation = orientation.relativeOrientation(DIRECTION.LEFT),
                rightOrientation = orientation.relativeOrientation(DIRECTION.RIGHT);
        PosCoord leftPrePC = positions[0].getPosCoord(), rightPrePC = leftPrePC;
        for (int i = 1; i < positions.length; i ++) {
            PosCoord tempPC = leftPrePC.nextPosCoord(backOrientation).nextPosCoord(leftOrientation);
            leftPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
            if (++ i >= positions.length) {
                break;
            }
            tempPC = rightPrePC.nextPosCoord(backOrientation).nextPosCoord(rightOrientation);
            rightPrePC = tempPC;
            positions[i].setPosCoord(tempPC);
        }
    }

    private void embattleChangShe(Position[] positions, PosCoord posCoord,
                                  ORIENTATION orientation) {
        ORIENTATION backOrientation = orientation.relativeOrientation(DIRECTION.BACK);
        PosCoord prePC = positions[0].getPosCoord();
        for (int i = 1; i < positions.length; i ++) {
            PosCoord tempPC = prePC.nextPosCoord(backOrientation);
            prePC = tempPC;
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