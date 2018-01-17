import java.lang.Exception;

// this Exception is just a small demo to override Exception.

class FormationException extends Exception {
    String msg;
    FormationException(int i) {
        msg = MSG(i);
    }
    private String MSG(int ID) {
        String a = null;
        switch (ID){
            case 3:
                 a =  "CHANGSHE formation objects should be in one row.";
                break;
            case 4:
                a =  "YULIN formation's up is over down, please input again.";
                break;
            case 6:
                a = "YANYUE formation's up is over down, please input again.";
                break;
            case 0: case 1: case 2: case 5: case 7: default:
                break;
        }
        return a;
    }

    @Override
    public String toString() {
        return msg;
    }
}

