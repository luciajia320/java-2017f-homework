package main.myenum;

/**
 * Created by qin on 18.1.5.
 */
public enum STATUS {
    大娃,二娃,三娃,四娃,五娃,六娃,七娃,爷爷,蝎子,蛇精,青蛙,草地;

    public STATUS xAt(int i) {
        for(STATUS s:STATUS.values())
            if(s.getIndex()==i)
                return s;
        return STATUS.草地;
    }
    private int getIndex(){
        return this.ordinal();
    }
}
