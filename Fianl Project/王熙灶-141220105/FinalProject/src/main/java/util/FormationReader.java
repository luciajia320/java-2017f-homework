package util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class FormationReader {
    public static void main(String[] args) {
        read("DongE.xml");
    }

    public static ArrayList<Point> read(String filename) {
        ArrayList<Point> points = new ArrayList<>();
        SAXReader reader = new SAXReader();
        Document document;
        try {
            URL url = FormationReader.class.getResource("/formations/" + filename);
            document = reader.read(url);
            Element positions = document.getRootElement();

            Iterator iterator = positions.elementIterator();
            while (iterator.hasNext()) {
                Element position = (Element) iterator.next();
                Element x = position.element("x");
                Element y = position.element("y");

                Point point = new Point();
                point.x = Integer.parseInt(x.getText());
                point.y = Integer.parseInt(y.getText());
                points.add(point);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return points;
    }
}