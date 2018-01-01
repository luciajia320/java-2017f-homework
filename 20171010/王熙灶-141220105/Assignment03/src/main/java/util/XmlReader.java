package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

@Deprecated
public class XmlReader {
    @Deprecated
    public static ArrayList<Point> read(String filename) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        ArrayList<Point> points = new ArrayList<>();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            InputStream in = XmlReader.class.getClassLoader().getResourceAsStream(filename);
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            if (root == null) return points;

            NodeList positionNodes = root.getChildNodes();
            if (positionNodes == null) return points;

            for(int i = 0; i < positionNodes.getLength(); i++) {
                Node ps = positionNodes.item(i);
                if (ps != null && ps.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList xyNodes = ps.getChildNodes();
                    if (xyNodes == null) continue;
                    Point point = new Point();
                    for (int j = 0; j < xyNodes.getLength(); j++) {
                        Node xy = xyNodes.item(j);
                        if (xy != null && xy.getNodeType() == Node.ELEMENT_NODE) {
                            if(xy.getNodeName().equals("x"))
                                point.x = Integer.parseInt(xy.getTextContent());
                            else {
                                point.y = Integer.parseInt(xy.getTextContent());
                            }
                        }
                    }
                    points.add(point);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return points;
    }
}