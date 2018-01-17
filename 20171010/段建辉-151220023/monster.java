class monster implements creature {
    private int rank;
    private String name;
    private Position position;
    monster(){
        rank = 10;
        name = "MONSTER";
    }

    @Override
    public void doSomething() {
        System.out.println("受死吧！");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public void setPosition(Position pos) {
        position = pos;
        position.setCreature(this);
    }

    @Override
    public Position getPosition(){
        return this.position;
    }
}

class Scorpion extends monster {
    private int rank;
    private String name;
    private Position position;
    Scorpion() {
        rank = 8;
        name = "🦂";
    }
    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void doSomething() {
        System.out.println("受死吧！");
    }
}

class Snake extends monster {
    private int rank;
    private String name;
    private Position position;
    Snake() {
        rank = 9;
        name = "🐍";
    }
    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void doSomething() {
        System.out.println("老公加油！");
    }
}

class Soldier extends monster {
    private int rank;
    private String name;
    private Position position;
    Soldier() {
        rank = 10;
        name = "🕷️🐷";
    }
    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void doSomething() {
        System.out.println("哇呀呀呀呀呀！");
    }
}