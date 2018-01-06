package util;

import archive.CreatureArchived;
import archive.TimePoint;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArchiveIO {
    public static void main(String[] args) {
        ArrayList<TimePoint> timePoints = read("sample.acv");
        System.out.println(timePoints);
        write(timePoints, "sss.acv");
    }

    public static ArrayList<TimePoint> read(String filename) {
        ArrayList<TimePoint> timeSeries = new ArrayList<>();
        SAXReader reader = new SAXReader();
        Document document;
        try {
            URL url = FormationReader.class.getResource("/archives/" + filename);
            document = reader.read(url);
            Element archive = document.getRootElement();

            Iterator tp_iterator = archive.elementIterator();
            while (tp_iterator.hasNext()) {
                TimePoint timePoint = new TimePoint();
                Element timepoint = (Element) tp_iterator.next();

                Iterator ct_iterator = timepoint.elementIterator();
                while (ct_iterator.hasNext()) {
                    CreatureArchived ca = new CreatureArchived();
                    Element creature = (Element) ct_iterator.next();
                    Element image = creature.element("image");
                    ca.image = image.getText();
                    Element state = creature.element("state");
                    ca.state = State.valueOf(state.getText());
                    Element x = creature.element("x");
                    ca.x = Integer.parseInt(x.getText());
                    Element y = creature.element("y");
                    ca.y = Integer.parseInt(y.getText());
                    timePoint.creatures.add(ca);
                }
                timeSeries.add(timePoint);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return timeSeries;
    }

    public static void write(List<TimePoint> timeSeries, String filename) {
        Document document = DocumentHelper.createDocument();
        Element archive = document.addElement("archive");

        for(TimePoint tp: timeSeries) {
            Element timepoint = archive.addElement("timepoint");
            for(CreatureArchived ca: tp.creatures) {
                Element creature = timepoint.addElement("creature");
                creature.addElement("image").addText(ca.image);
                creature.addElement("state").addText(ca.state.toString());
                creature.addElement("x").addText(Integer.toString(ca.x));
                creature.addElement("y").addText(Integer.toString(ca.y));
            }
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        try {
            if(!filename.endsWith(".cav")) {
                filename += ".cav";
            }
            FileWriter fileWriter = new FileWriter("src/main/resources/archives/" + filename);
            XMLWriter writer = new XMLWriter(fileWriter, format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
