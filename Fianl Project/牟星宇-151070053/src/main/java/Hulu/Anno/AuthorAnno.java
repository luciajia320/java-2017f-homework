package Hulu.Anno;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorAnno {

    //作者信息
    String name();//姓名
    int studentNum();//学号
    String department();//院系
    int revision() default 1;

}
