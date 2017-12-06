import java.util.ArrayList;

enum formationID {HEYI, YANXING, HENGE, CHANGSHE, YULIN, FANGYUAN, YANYUE, FENGSHI};

class Formation {
    private int left, right, up, down;
    private Square square;
    Formation(Square square, int left, int right, int up, int down) {
        this.square = square;
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    void setFormation(formationID ID) {
        switch (ID) {
            case HEYI: break;
            case YANXING: break;
            case HENGE: break;
            case CHANGSHE: {
                if(up != down) {   break;}
                for(int i = 0; i <= right - left; i++) {
                    square.positions[up][left + 1] = new ArrayList<>();
                    square.positions[up][left + i].add(new Position(new Soldier(), up, left + i));
                }
                break;
            }
            case YULIN: {
                for(int i = up; i <= down; i++) {
                    for(int j = left; j <= right; j++) {
                        boolean area = (i == up && j == left + 2)
                                || (i == up + 1 && j == left + 3)
                                || (i == up + 2 && j != left && j != right)
                                || (i == up + 3)
                                || (i == down && j == left + 2);
                        if(i == up + 2 && j == left + 2){
                            square.positions[i][j] = new ArrayList<>();
                            square.positions[i][j].add(new Position(new Scorpion(), i, j));
                        }
                        else if(area) {
                            square.positions[i][j] = new ArrayList<>();
                            square.positions[i][j].add(new Position(new Soldier(), i, j));
                        }
                    }
                }
                break;
            }
            case FANGYUAN: break;
            case YANYUE: {
                for(int i = up; i <= down; i++) {
                    for(int j = left; j <= right; j++) {
                        if(i == up + 1 && j == left + 4) {
                            square.positions[i][j] = new ArrayList<>();
                            square.positions[i][j].add(new Position(new Scorpion(), i, j));
                        }
                        else if(((i == up || i == up + 1) && (j >= left+3 && j <= right - 3))
                            || ((i == up + 2||i == up + 3) && (j >= left+2 && j <= right - 2))
                            || ((i == up+3 || i == up + 4) && (j == left + 1 || j == right - 1))
                            || ((i == down) && (j == left || j == right))) {
                            square.positions[i][j] = new ArrayList<>();
                            square.positions[i][j].add(new Position(new Soldier(), i, j));
                        }
                    }
                }
                break;
            }
            case FENGSHI: break;
            default: break;
        }
    }
}
