package nju.java;

enum QueueType {
    鹤翼(7), 雁行(5), 冲轭(6), 长蛇(7), 鱼鳞(10), 方门(8), 偃月(19), 锋矢(12);

    public int num;

    private QueueType(int num) {
        this.num = num;
    }

    static private int[][] getRawTable(QueueType queueType, int x, int y) {
        int[][] table = null;

        switch (queueType) {
            case 偃月:
                //creatures.length should be 19
                table = new int[][]{
                        {x, y},
                        {x - 1, y - 1},
                        {x - 1, y - 3},
                        {x - 2, y - 2},
                        {x - 2, y - 4},
                        {x - 3, y - 3},
                        {x - 3, y - 5},
                        {x - 3, y - 7},
                        {x - 4, y - 3},
                        {x - 4, y - 5},
                        {x - 4, y - 7},
                        {x - 5, y - 3},
                        {x - 5, y - 5},
                        {x - 5, y - 7},
                        {x - 6, y - 2},
                        {x - 6, y - 4},
                        {x - 7, y - 1},
                        {x - 7, y - 3},
                        {x - 8, y},
                };
                break;
            case 冲轭:
                //creatures.length should be 6
                table = new int[][]{
                        {x, y},
                        {x - 1, y + 1},
                        {x - 2, y},
                        {x - 3, y + 1},
                        {x - 4, y},
                        {x - 5, y + 1}
                };
                break;
            case 方门:
                //creatures.length should be 8
                table = new int[][]{
                        {x, y},
                        {x - 1, y - 1},
                        {x - 1, y + 1},
                        {x - 2, y - 2},
                        {x - 2, y + 2},
                        {x - 3, y - 1},
                        {x - 3, y + 1},
                        {x - 4, y},
                };
                break;
            case 锋矢:
                //creatures.length should be 12
                table = new int[][]{
                        {x, y},
                        {x - 2, y},
                        {x - 4, y},
                        {x - 5, y - 4},
                        {x - 5, y + 4},
                        {x - 6, y},
                        {x - 7, y - 3},
                        {x - 7, y + 3},
                        {x - 8, y},
                        {x - 9, y - 2},
                        {x - 9, y + 2},
                        {x - 10, y},
                };
                break;
            case 长蛇:
                //creatures.length should be 7
                table = new int[][]{
                        {x, y},
                        {x - 1, y},
                        {x - 2, y},
                        {x - 3, y},
                        {x - 4, y},
                        {x - 5, y},
                        {x - 6, y}
                };
                break;
            case 雁行:
                //creatures.length should be 5
                table = new int[][]{
                        {x, y},
                        {x - 1, y + 1},
                        {x - 2, y + 2},
                        {x - 3, y + 3},
                        {x - 4, y + 4}
                };
                break;
            case 鱼鳞:
                //creatures.length should be 10
                table = new int[][]{
                        {x, y},
                        {x - 1, y - 3},
                        {x - 1, y - 1},
                        {x - 1, y + 1},
                        {x - 1, y + 3},
                        {x - 2, y - 2},
                        {x - 2, y},
                        {x - 2, y + 2},
                        {x - 3, y + 1},
                        {x - 4, y}
                };
                break;
            case 鹤翼:
                //creatures.length should be 7
                table = new int[][]{
                        {x, y},
                        {x - 1, y - 1},
                        {x - 1, y + 1},
                        {x - 2, y - 2},
                        {x - 2, y + 2},
                        {x - 3, y - 3},
                        {x - 3, y + 3}
                };
                break;
            default:
                break;
        }

        return table;

    }

    static public int[][] getTable(QueueType queueType, int x, int y){
        int[][] table = getRawTable(queueType, x, y);
        for(int i = 0; i < table.length; i++){
            int tmp = table[i][1];
            table[i][1] = -table[i][0];
            table[i][0] = tmp;
        }
        return table;
    }
}

abstract class PlayersFactory {

    protected QueueType queueType;
    int [][] table;
    Creature[] players;

    public PlayersFactory(QueueType queueType) {
        this.queueType = queueType;
        table = QueueType.getTable(queueType, 0, 0);
        players = new Creature[queueType.num];
    }

    abstract  Creature[] create(Field field, Thing2D start, int space);

}




