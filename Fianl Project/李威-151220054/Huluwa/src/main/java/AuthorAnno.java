public @interface AuthorAnno {
	
    String name();
   
    String functionName() default "method1";
    
    int revision() default 1;
}
