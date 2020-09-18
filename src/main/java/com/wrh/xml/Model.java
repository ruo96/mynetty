package com.wrh.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname Model
 * @Description TODO
 * @Date 2020/9/18 11:01
 */
public class Model {
    private int supervisorNum;

    private String zkConnectString;

    private int zkTimeout;

    private String superAlarm;

    private String topoAlarm;

    private String nimbusAlarm;

    private List<String> telList;

    private List<String> topoNameList;

    public Model(String path) {
        this.readConfigXML(path);
    }

    public int getSupervisorNum() {
        return supervisorNum;
    }

    public void setSupervisorNum(int supervisorNum) {
        this.supervisorNum = supervisorNum;
    }

    public String getZkConnectString() {
        return zkConnectString;
    }

    public void setZkConnectString(String zkConnectString) {
        this.zkConnectString = zkConnectString;
    }

    public int getZkTimeout() {
        return zkTimeout;
    }

    public void setZkTimeout(int zkTimeout) {
        this.zkTimeout = zkTimeout;
    }

    public String getSuperAlarm() {
        return superAlarm;
    }

    public void setSuperAlarm(String superAlarm) {
        this.superAlarm = superAlarm;
    }

    public String getTopoAlarm() {
        return topoAlarm;
    }

    public void setTopoAlarm(String topoAlarm) {
        this.topoAlarm = topoAlarm;
    }

    public String getNimbusAlarm() {
        return nimbusAlarm;
    }

    public void setNimbusAlarm(String nimbusAlarm) {
        this.nimbusAlarm = nimbusAlarm;
    }

    public List<String> getTelList() {
        return telList;
    }

    public void setTelList(List<String> telList) {
        this.telList = telList;
    }

    public List<String> getTopoNameList() {
        return topoNameList;
    }

    public void setTopoNameList(List<String> topoNameList) {
        this.topoNameList = topoNameList;
    }

    public void readConfigXML(String path) {
        NodeList nodeList = null;
        Element element = null;

        try {
//            String dir = "src/main/resources/";
            String dir = "D:\\pro\\mynetty\\src\\main\\resources\\";
            File file = new File(dir + File.separator + path);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            nodeList = doc.getElementsByTagName("person");
            element = (Element) nodeList.item(0);

            nodeList = element.getElementsByTagName("tel");
            telList = new ArrayList<String>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                telList.add(nodeList.item(i).getFirstChild().getNodeValue());
            }

            nodeList = doc.getElementsByTagName("nimbus");
            element = (Element) nodeList.item(0);

            this.setNimbusAlarm(element.getElementsByTagName("alarm").item(0).getFirstChild().getNodeValue());

            nodeList = doc.getElementsByTagName("Supervisor");
            element = (Element) nodeList.item(0);
            String supervisorSum = element.getElementsByTagName("Supervisor-sum").item(0).getFirstChild().getNodeValue();
            if (null != supervisorSum) {
                this.setSupervisorNum(Integer.valueOf(supervisorSum));
            }

            this.setSuperAlarm(element.getElementsByTagName("alarm").item(0).getFirstChild().getNodeValue());

            nodeList = doc.getElementsByTagName("Topology");
            element = (Element) nodeList.item(0);

            int topoNum = element.getElementsByTagName("topology-name").getLength();
            String topoName = null;
            topoNameList = new ArrayList<String>();

            for (int i = 0; i < topoNum; i++) {
                topoName = element.getElementsByTagName("topology-name").item(i).getFirstChild().getNodeValue();
                if (null != topoName) {
                    topoNameList.add(topoName);
                }
            }

            this.setTopoAlarm(element.getElementsByTagName("alarm").item(0).getFirstChild().getNodeValue());

            nodeList = doc.getElementsByTagName("zookeeper");
            element = (Element) nodeList.item(0);
            String host = element.getElementsByTagName("host").item(0).getFirstChild().getNodeValue();

            if (null != host) {
                this.setZkConnectString(host);
            }

            String timeout = element.getElementsByTagName("timeout").item(0).getFirstChild().getNodeValue();
            if (null != timeout) {
                this.setZkTimeout(Integer.valueOf(timeout));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Model [supervisorNum=" + supervisorNum + ", zkConnectString=" + zkConnectString + ", zkTimeout="
                + zkTimeout + ", superAlarm=" + superAlarm + ", topoAlarm=" + topoAlarm + ", nimbusAlarm=" + nimbusAlarm
                + ", telList=" + telList + ", topoNameList=" + topoNameList + "]";
    }

    public static void main(String[] args) {
        Model model = new Model("storm-monitor-config.xml");
        System.out.println(model.toString());
    }
}
