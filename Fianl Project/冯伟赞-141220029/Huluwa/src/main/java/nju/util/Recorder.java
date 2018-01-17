package nju.util;

import com.google.gson.GsonBuilder;
import nju.model.ActionBean;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Recorder {

    private BufferedWriter writer;
    private List<ActionBean> beans = new ArrayList<>();

    public Recorder(String recordFile) throws IOException {
        open(recordFile);
    }

    public void open(String recordFile) throws IOException {
        writer = new BufferedWriter(new FileWriter(recordFile));
    }

    public void record(String content) throws IOException {
        writer.write(content);
    }

    public void add(ActionBean bean) {
        beans.add(bean);
    }

    public void recordAllBeans() throws IOException {
        writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(beans));
    }

    public void close() {
        if (writer != null) {
            try {
                writer.close();
                writer = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
