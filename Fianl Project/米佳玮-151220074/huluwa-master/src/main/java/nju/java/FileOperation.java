package nju.java;

import java.io.*;

public class FileOperation {
    private static File file =null;
    private static  FileInputStream in=null;
    private static  FileOutputStream out=null;
    private static InputStreamReader isr=null;
    private static OutputStreamWriter osr=null;
    private static  BufferedReader bufr=null;
    public static void creatFile(String fileName) {
        file = new File(fileName);
        System.out.println("creat file");
        try {
            if(!file.exists()) {
         //       System.out.println("file not xists");
                file.createNewFile(); // 创建文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in = new FileInputStream(file);
            isr = new InputStreamReader(in);//装饰器模式
            bufr = new BufferedReader(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void output(String str) {
        if(out==null) {
            try {
                out = new FileOutputStream(file);
                osr = new OutputStreamWriter(out);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            osr.write(str);
            osr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String input(){
        try {
            String line = null;
            if ((line = bufr.readLine())!=null){
                return line;
            }
            return line;
           // bufr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//
    public static void closeFile() {
        try{
            if(file!=null&&osr!=null) {
                osr.flush();
                osr.close();
                osr=null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}