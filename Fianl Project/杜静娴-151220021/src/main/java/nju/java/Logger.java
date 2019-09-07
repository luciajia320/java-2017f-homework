package nju.java;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Logger {
    Field field;
    static String currentPath = null;
    static ArrayList<String> currentLog = new ArrayList<String>();
    static ArrayList<ArrayList<String>> deal = new ArrayList<ArrayList<String>>();
    static ArrayList<String> current_story = new ArrayList<String>();
    static FileAgent fs;
    static int story_line_index = 0;

    public Logger(Field field) {
        this.field = field;
    }

    public static void setCurrentPath(String currentPath) {
        Logger.currentPath = currentPath;
        dealCurrentLog();
    }

    private static boolean dealCurrentLog() {
        String[]sample = {"Yeye","Huluwa1","Huluwa2","Huluwa3","Huluwa4","Huluwa5","Huluwa6","Huluwa7",
                "Shejing","Xiezi","Louluo1","Louluo2","Louluo3","Louluo4","Louluo5","Louluo6"};

        int index = 0;

        if(currentLog == null)
            return false;
//        File file = new File(currentPath);
//        File file2 = new File(currentPath+".txt");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(currentPath+".txt")));
            String data = null;
            while((data = br.readLine())!=null)
                current_story.add(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(currentPath)));
            String data = null;
            while((data = br2.readLine())!=null)
            {
                if(data.startsWith(sample[index])){
                    if(deal.size() <= index)
                        deal.add(new ArrayList<String>());
                    deal.get(index).add(data);
                }
                else{
                    if(deal.size() <= ++index)
                        deal.add(new ArrayList<String>());
                    deal.get(index).add(data);
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void newLog(String fn) {
        String content = "";
        ArrayList<String> story = field.getNote();
        for (Player v : field.getGoodStaff()) {
            ArrayList<String> list = v.getLog();
            for (String s : list) {
                content += s + "\n";
            }
        }
        for (Player v : field.getBadStaff()) {
            ArrayList<String> list = v.getLog();
            for (String s : list) {
                content += s + "\n";
            }
        }
        FileAgent.fileService(fn, content, story);
    }

    public static String lastLog(){
        setCurrentPath(FileAgent.fileService());
        return currentPath;
    }

    public static boolean load(Field field, ArrayList<Player> list, int i) {
        boolean done = true;
        if(i == 0)
            Logger.story_line_index = 0;
        for(int j = 0; j < 16; j++){
            if(deal.size() <= j) {
                System.err.println("deal not initialized");
                System.exit(-1);
            }
            ArrayList<String> s = deal.get(j);
            if(s != null) {
                int index;
                if(i >= s.size()){
                    index = s.size()-1;
                }else {
                    done =false;
                    index = i;
                }
                String str = s.get(i);
                String[]state = str.split(" ");
                list.get(j).setX(new Integer(state[1]));
                list.get(j).setY(new Integer(state[2]));
                int current_life = new Integer(state[3]);
//                if(current_life < list.get(j).getLife());
//                    field.takeNote(current_story.get(story_line_index++));
                list.get(j).setLife(current_life);
            }
        }
        if(done)
            return false;
        return true;
    }
}

class FileAgent {
    static final String[][] fileENames = {
            { ".log", "日志文件(*.log)"}
    };

    private static JFileChooser fileChooser = new JFileChooser();
    static
    {
        // 设置当前目录
        fileChooser.setCurrentDirectory(new File("src/log/"));
        fileChooser.setAcceptAllFileFilterUsed(false);
    }

    static String fileService() {
        return load();
    }

    public static void fileService(String fn, String content, ArrayList<String> story) {
        saveService(fn, content.getBytes(), story);
    }

    private static String load(){

        // 显示所有文件
        fileChooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File file) {
                if(file.getName().endsWith(".log"))
                    return true;
                return false;
            }

            public String getDescription() {

                return "所有文件(*.*)";
            }
        });

        // 循环添加需要显示的文件
        for (final String[] fileEName : fileENames) {
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File file) {
                    if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
                        return true;
                    }
                    return false;
                }
                public String getDescription() {
                    return fileEName[1];
                }
            });
        }

        fileChooser.showDialog(null, null);
        if(fileChooser.getSelectedFile() != null)
            return fileChooser.getSelectedFile().getPath();
        else
            return null;
    }

    private static void saveService(String fn, byte[] content, ArrayList<String> story) {
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File file) {
                if(file.getName().endsWith(".log"))
                    return true;
                return false;
            }

            public String getDescription() {

                return "所有文件(*.*)";
            }
        });

        int result=fileChooser.showSaveDialog(null);

        if (result==JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            File storyFile = null;
            if (!file.getPath().endsWith(".log")) {
                file = new File(fn);
            }
            System.out.println("file path=" + file.getPath());
            FileOutputStream fos = null;
            FileOutputStream fos2 = null;
            try {
                if (!file.exists()) {//文件不存在 则创建一个
                    file.createNewFile();
                }
                storyFile = new File(file.getPath()+".txt");
                fos = new FileOutputStream(file);
                fos.write(content);
                fos.flush();
                fos2 = new FileOutputStream(storyFile);
                for(String s:story){
                    fos2.write((s+"\n").getBytes());
                }
                fos2.flush();
            } catch (IOException e) {
                System.err.println("文件创建失败：");
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                        fos2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}