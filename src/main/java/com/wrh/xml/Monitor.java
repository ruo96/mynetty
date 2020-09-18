package com.wrh.xml;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname Monitor
 * @Description 监控storm节点数据
 * @Date 2020/9/18 11:48
 */
// 监控zk中storm的节点是否存在；可设置5分钟执行一次
public class Monitor {
    // storm在zookeeper上存储的路径。具体的路径跟storm的版本有关。

    public static final String JSTORM_ROOT_PATH = "/storm";

    public static final String SUPERVISOR_PATH = JSTORM_ROOT_PATH + "/supervisors";

    public static final String TOPO_PATH = JSTORM_ROOT_PATH + "/storms";

    public static final String NIMBUS_PATH = JSTORM_ROOT_PATH + "/nimbus_host";


    public static void main(String[] args) {

        String configFile = null;
        if (0 == args.length) {
            configFile = "storm-monitor-config.xml";
        } else {
            configFile = args[0];
        }

        String alarmString = null;

        Model model = new Model(configFile);
        ZooKeeper zk = null;

        List<String> supervisorList = null;

        int supervisorNum = 0;

        try {
            zk = new ZooKeeper(model.getZkConnectString(), model.getZkTimeout(), new Watcher(){
                @Override
                public void process(WatchedEvent arg0) {
                    //
                }
            });

            // 检查nimbus是否正常
            if (zk.getChildren(NIMBUS_PATH, false).size() < 1) {
                // nimbus挂掉告警。后续可实现发短信的代码。
                System.err.println(model.getNimbusAlarm());

                zk.close();
                System.exit(1);
            }

            // 检查supervisor个数是否正常
            supervisorList = zk.getChildren(SUPERVISOR_PATH, false);
            supervisorNum = supervisorList.size();
            if (supervisorNum != model.getSupervisorNum()) {

                alarmString = model.getSuperAlarm().replace("\\$\\{num}", supervisorNum + "");
                System.err.println(alarmString);

                zk.close();
                System.exit(2);
            }

            // 检查topo是否正常
            List<String> topoList = zk.getChildren(TOPO_PATH, false);
            List<String> topoNameList = new ArrayList<String>();
            if (topoList.size() > 0) {
                for (String topoNameAll : topoList) {
                    topoNameList.add(topoNameAll.split("-")[0]);
                }
            }

            for (String topo : model.getTopoNameList()) {
                if (!topoNameList.contains(topo)) {
                    alarmString = model.getTopoAlarm().replace("\\$\\{name}", topo);

                    System.err.println(alarmString);
                    zk.close();
                    System.exit(3);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
