package com.wrh.IOuse.fileOperate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/12/2 15:33
 */
public class TestYamlOperate {



    @Test
    public void Test11() throws IOException {

        String yaml="apiVersion: apps/v1\n" +
                "kind: Deployment\n" +
                "metadata:\n" +
                "  name: '{K8S_PARAM_APP}'\n" +
                "  namespace: '{K8S_PARAM_NAMESPACE}'\n" +
                "  labels:\n" +
                "    module: '{K8S_PARAM_GROUP_NAME}'\n" +
                "spec:\n" +
                "  selector:\n" +
                "    matchLabels:\n" +
                "      app: '{K8S_PARAM_APP}'\n" +
                "      module: '{K8S_PARAM_GROUP_NAME}'\n" +
                "  replicas: '{K8S_PARAM_REPLICAS}'\n" +
                "  template:\n" +
                "    metadata:\n" +
                "      labels: \n" +
                "        app: '{K8S_PARAM_APP}'\n" +
                "        module: '{K8S_PARAM_GROUP_NAME}'\n" +
                "    spec:\n" +
                "      containers: \n" +
                "      - name: '{K8S_PARAM_APP}'\n" +
                "        image: '{K8S_PARAM_IMAGE}'\n" +
                "        env: \n" +
                "        - name: SENDER_OPTS \n" +
                "          value: --threads {K8S_PARAM_THREAD} --logFile {K8S_PARAM_LOG_FILE} --logLevel {K8S_PARAM_LOG_LEVEL} --dbFile {K8S_PARAM_DB_FILE} --paramsFile {K8S_PARAM_PARAM_FILE} --port {K8S_PARAM_PORT} --nonceByteCount {K8S_PARAM_NONCEBYTECOUNT} --compress\n" +
                "        volumeMounts: \n" +
                "        - name: pir-data \n" +
                "          mountPath: '{K8S_PARAM_DB_PATH}'\n" +
                "      volumes: \n" +
                "      - name: pir-data\n" +
                "        persistentVolumeClaim: \n" +
                "          claimName: '{K8S_PARAM_LONGHORN_NAME}'\n" +
                "      affinity:\n" +
                "        podAntiAffinity:\n" +
                "          preferredDuringSchedulingIgnoredDuringExecution:\n" +
                "          - weight: 100\n" +
                "            podAffinityTerm:\n" +
                "              topologyKey: kubernetes.io/hostname\n" +
                "              labelSelector:\n" +
                "                matchExpressions:\n" +
                "                - key: module\n" +
                "                  operator: In\n" +
                "                  values:\n" +
                "                  - '{K8S_PARAM_GROUP_NAME}'";


        Object obj = new ObjectMapper(new YAMLFactory()).readValue(yaml, Object.class);
        String json = new ObjectMapper().writeValueAsString(obj);
        System.out.println(json);

    }
}
