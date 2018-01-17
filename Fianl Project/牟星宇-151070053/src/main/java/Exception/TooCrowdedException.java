package Exception;

import Hulu.Anno.AuthorAnno;

@AuthorAnno(name = "牟星宇", studentNum = 151070053, department = "信管")
public class TooCrowdedException extends Exception {

    public TooCrowdedException(String message){
        super(message);
    }
}
