package com.wrh.basicUse;

public interface K8SReqConstant {

    String CONFIG_MAP_CREATE = "{\n" +
            "    \"apiVersion\":\"v1\",\n" +
            "    \"kind\":\"ConfigMap\",\n" +
            "    \"metadata\":{\n" +
            "        \"name\":\"fedx-pir\",\n" +
            "        \"labels\":{\n" +
            "            \"app\":\"fedx-pir\"\n" +
            "        },\n" +
            "        \"namespace\":\"fedx-pir-1000\"\n" +
            "    },\n" +
            "    \"data\":{\n" +
//            "        \"application.yml\":\"ABC\"\n" +
            "        \"application.yml\":\"%s\"\n" +
            "    }\n" +
            "}";

    String CONFIG_MAP_PIR = "{\n" +
            "    \"server\":{\n" +
            "        \"port\":8081\n" +
            "    },\n" +
            "    \"spring\":{\n" +
            "        \"application\":{\n" +
            "            \"name\":\"fedx-pir\"\n" +
            "        },\n" +
            "        \"datasource\":{\n" +
            "            \"mysql\":{\n" +
            "                \"driver-class-name\":\"com.mysql.cj.jdbc.Driver\",\n" +
            "                \"url\":\"jdbc:mysql:\\/\\/mysql.fedx-1000:3306\\/fedx?characterEncoding=UTF-8&useUnicode=true&serverTimezone=Asia\\/Shanghai&useSSL=false&allowPublicKeyRetrieval=true\",\n" +
            "                \"username\":\"root\",\n" +
            "                \"password\":\"Wa@123456\"\n" +
            "            },\n" +
            "            \"clickhouse\":{\n" +
            "                \"driver-class-name\":\"ru.yandex.clickhouse.ClickHouseDriver\",\n" +
            "                \"url\":\"jdbc:clickhouse:\\/\\/clickhouse-cluster.fedx-1000:8123\\/default\",\n" +
            "                \"username\":\"default\",\n" +
            "                \"password\":\"ck@12345\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"jackson\":{\n" +
            "            \"date-format\":\"yyyy-MM-dd HH:mm:ss\",\n" +
            "            \"time-zone\":\"GMT+8\"\n" +
            "        },\n" +
            "        \"servlet\":{\n" +
            "            \"multipart\":{\n" +
            "                \"max-file-size\":\"500MB\",\n" +
            "                \"max-request-size\":\"500MB\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"kafka\":{\n" +
            "            \"producer\":{\n" +
            "                \"acks\":-1,\n" +
            "                \"retries\":3,\n" +
            "                \"batch-size\":16384,\n" +
            "                \"buffer-memory\":33554432,\n" +
            "                \"key-serializer\":\"org.apache.kafka.common.serialization.StringSerializer\",\n" +
            "                \"value-serializer\":\"org.apache.kafka.common.serialization.StringSerializer\"\n" +
            "            },\n" +
            "            \"bootstrap-servers\":\"fedx-proxyName-cluster-ip.fedx-1000:9092\"\n" +
            "        },\n" +
            "        \"redis\":{\n" +
            "            \"host\":\"redis-master.fedx-1000\",\n" +
            "            \"port\":6379,\n" +
            "            \"connect-timeout\":\"10000ms\",\n" +
            "            \"database\":1\n" +
            "        }\n" +
            "    },\n" +
            "    \"pagehelper\":{\n" +
            "        \"helper-dialect\":\"mysql\",\n" +
            "        \"reasonable\":true,\n" +
            "        \"support-methods-arguments\":true,\n" +
            "        \"params\":\"count=countSql\",\n" +
            "        \"page-size-zero\":true\n" +
            "    },\n" +
            "    \"logging\":{\n" +
            "        \"level\":{\n" +
            "            \"com.zetyun.fedx.api.dal.dao\":\"INFO\",\n" +
            "            \"root\":\"INFO\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"swagger\":{\n" +
            "        \"enabled\":true\n" +
            "    },\n" +
            "    \"fedx\":{\n" +
            "        \"job\":{\n" +
            "            \"address\":\"http:\\/\\/fedx-job-api.fedx-1000:8081\"\n" +
            "        },\n" +
            "        \"fateflow\":{\n" +
            "            \"address\":\"http:\\/\\/fedx-proxyName-cluster-ip.fedx-1000:9380\"\n" +
            "        },\n" +
            "        \"fateboard\":{\n" +
            "            \"address\":\"http:\\/\\/fedx-proxyName-cluster-ip.fedx-1000:8280\"\n" +
            "        },\n" +
            "        \"namenode\":{\n" +
            "            \"address\":\"hdfs:\\/\\/fedx-proxyName-cluster-ip.fedx-1000:9000\"\n" +
            "        },\n" +
            "        \"dolphin\":{\n" +
            "            \"address\":\"http:\\/\\/172.20.8.168:12345\"\n" +
            "        },\n" +
            "        \"fedlearn\":{\n" +
            "            \"address\":\"http:\\/\\/172.20.8.110\"\n" +
            "        },\n" +
            "        \"fateserving\":{\n" +
            "            \"address\":\"http:\\/\\/fedx-proxyName-cluster-ip.fedx-1000:8350\"\n" +
            "        },\n" +
            "        \"fateservingproxy\":{\n" +
            "            \"address\":\"http:\\/\\/fedx-proxyName-cluster-ip.fedx-1000:8059\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"model-serving\":{\n" +
            "        \"address\":\"http:\\/\\/172.20.8.110:12345\"\n" +
            "    },\n" +
            "    \"project\":{\n" +
            "        \"role\":{\n" +
            "            \"super\":{\n" +
            "                \"user\":\"SUPER\"\n" +
            "            }\n" +
            "        }\n" +
            "    },\n" +
            "    \"jwt\":{\n" +
            "        \"expiration\":7200000,\n" +
            "        \"header\":\"Authorization\",\n" +
            "        \"secret\":\"NDU0NTY4amhmc3NkeHp6eGNxdzIlMjFAJTIxQCUyM2ZmNQ==\"\n" +
            "    },\n" +
            "    \"file\":{\n" +
            "        \"path\":\"\\/data\\/\"\n" +
            "    },\n" +
            "    \"spark-limit\":{\n" +
            "        \"cpu\":96,\n" +
            "        \"memory\":99\n" +
            "    },\n" +
            "    \"split-path\":\"\\/data\\/\",\n" +
            "    \"k8s\":{\n" +
            "        \"address\":\"https:\\/\\/172.20.8.110:6443\\/\",\n" +
            "        \"token\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IklwVmpVLTUtdHZYUVZ5S0hteVNaaHNMSEtJd0RzT2tnX0FtV2xFLUk5WjgifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi11c2VyLXRva2VuLWI4YnBmIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImFkbWluLXVzZXIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiI1Zjc1NGI1Zi1kMjk0LTRjOTAtODQ2Zi1jOGY1ZTcwMzVlMzQiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZS1zeXN0ZW06YWRtaW4tdXNlciJ9.h2cQFsM5plg4fy6p_IwGJAU2Bx88IYclgKpbc3hEM6TOYX9iyGzFfvr7uXdxUAroN9f3MBRIJyFDOCtAy-HOGTHtsVLCoha6rXSYrLZiALUb99HZJsS1PP_LxmleFxfh7ktcJwzY6mlk96lC0ifGlvrdFRhYDw0WEDor-tyHKrWBaFSjl5rBx72uOFUHMaBAEJQhPY2ng0meme04znmPpl0Kr4orFYd3i_eTWx9-2l2EaFf9UoP7kyF2XgfzCAQ9s3QFoiwlER077H6Wx7lVoC6zjrPYTt3W9RYEs6mY9J7SI8Tysvx9lMONTInHaM38UU3zRe29v0X683ln324gmg\",\n" +
            "        \"image\":\"registry.zet-fl.com\\/fedx\\/develop\\/fedx-pir:latest\",\n" +
            "        \"replicas\":2,\n" +
            "        \"app\":\"fedx-pir\",\n" +
            "        \"mountPath\":\"\\/{%s}\\/application.yml\"\n" +
            "    }\n" +
            "}";


    String DEPLOYMENT_CREATE = "{\n" +
            "    \"apiVersion\": \"apps/v1\",\n" +
            "    \"kind\": \"Deployment\",\n" +
            "    \"metadata\": {\n" +
            "        \"name\": \"{K8S_PARAM_APP}\",\n" +
            "        \"namespace\": \"{K8S_PARAM_NAMESPACE}\"\n" +
            "    },\n" +
            "    \"spec\": {\n" +
            "        \"selector\": {\n" +
            "            \"matchLabels\": {\n" +
            "                \"app\": \"{K8S_PARAM_APP}\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"replicas\": {K8S_PARAM_REPLICAS},\n" +
            "        \"template\": {\n" +
            "            \"metadata\": {\n" +
            "                \"labels\": {\n" +
            "                    \"app\": \"{K8S_PARAM_APP}\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"spec\": {\n" +
            "                \"containers\": [\n" +
            "                    {\n" +
            "                        \"name\": \"{K8S_PARAM_APP}\",\n" +
            "                        \"image\": \"{K8S_PARAM_IMAGE}\",\n" +
            "                        \"env\": [\n" +
            "                            {\n" +
            "                                \"name\": \"SENDER_OPTS\",\n" +
            "                                \"value\": \"--threads {K8S_PARAM_THREAD} --logFile {K8S_PARAM_LOG_FILE} --logLevel {K8S_PARAM_LOG_LEVEL} --dbFile {K8S_PARAM_DB_FILE} --paramsFile {K8S_PARAM_PARAM_FILE} --port {K8S_PARAM_PORT} --nonceByteCount {K8S_PARAM_NONCEBYTECOUNT} --compress\"\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}";

    String DEPLOYMENT_CREATE_V2 = "{\n" +
            "    \"apiVersion\": \"apps/v1\",\n" +
            "    \"kind\": \"Deployment\",\n" +
            "    \"metadata\": {\n" +
            "        \"name\": \"{K8S_PARAM_APP}\",\n" +
            "        \"namespace\": \"{K8S_PARAM_NAMESPACE}\"\n" +
            "    },\n" +
            "    \"spec\": {\n" +
            "        \"selector\": {\n" +
            "            \"matchLabels\": {\n" +
            "                \"app\": \"{K8S_PARAM_APP}\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"replicas\": {K8S_PARAM_REPLICAS},\n" +
            "        \"template\": {\n" +
            "            \"metadata\": {\n" +
            "                \"labels\": {\n" +
            "                    \"app\": \"{K8S_PARAM_APP}\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"spec\": {\n" +
            "                \"containers\": [\n" +
            "                    {\n" +
            "                        \"name\": \"{K8S_PARAM_APP}\",\n" +
            "                        \"image\": \"{K8S_PARAM_IMAGE}\",\n" +
            "                        \"env\": [\n" +
            "                            {\n" +
            "                                \"name\": \"SENDER_OPTS\",\n" +
            "                                \"value\": \"--threads {K8S_PARAM_THREAD} --logFile {K8S_PARAM_LOG_FILE} --logLevel {K8S_PARAM_LOG_LEVEL} --dbFile {K8S_PARAM_DB_FILE} --paramsFile /opt/parameters/100K-1-16.json --port {K8S_PARAM_PORT} --nonceByteCount {K8S_PARAM_NONCEBYTECOUNT} --compress\"\n" +
            "                            }\n" +
            "                        ],\n" +
            "                        \"volumeMounts\": [\n" +
            "                            {\n" +
            "                                \"name\": \"pir-data\",\n" +
            "                                \"mountPath\": \"{K8S_PARAM_DB_PATH}\"\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"volumes\": [\n" +
            "                    {\n" +
            "                        \"name\": \"pir-data\",\n" +
            "                        \"persistentVolumeClaim\": {\n" +
            "                            \"claimName\": \"{K8S_PARAM_LONGHORN_NAME}\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}";

    /** 反亲和性配置*/
    String DEPLOYMENT_CREATE_V3 = "{\n" +
            "    \"apiVersion\": \"apps/v1\",\n" +
            "    \"kind\": \"Deployment\",\n" +
            "    \"metadata\": {\n" +
            "        \"name\": \"{K8S_PARAM_APP}\",\n" +
            "        \"namespace\": \"{K8S_PARAM_NAMESPACE}\",\n" +
            "        \"labels\": {\n" +
            "            \"model\": \"{K8S_PARAM_GROUP_NAME}\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"spec\": {\n" +
            "        \"selector\": {\n" +
            "            \"matchLabels\": {\n" +
            "                \"app\": \"{K8S_PARAM_APP}\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"replicas\": {K8S_PARAM_REPLICAS},\n" +
            "        \"template\": {\n" +
            "            \"metadata\": {\n" +
            "                \"labels\": {\n" +
            "                    \"app\": \"{K8S_PARAM_APP}\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"spec\": {\n" +
            "                \"containers\": [\n" +
            "                    {\n" +
            "                        \"name\": \"{K8S_PARAM_APP}\",\n" +
            "                        \"image\": \"{K8S_PARAM_IMAGE}\",\n" +
            "                        \"env\": [\n" +
            "                            {\n" +
            "                                \"name\": \"SENDER_OPTS\",\n" +
            "                                \"value\": \"--threads {K8S_PARAM_THREAD} --logFile {K8S_PARAM_LOG_FILE} --logLevel {K8S_PARAM_LOG_LEVEL} --dbFile {K8S_PARAM_DB_FILE} --paramsFile {K8S_PARAM_PARAM_FILE} --port {K8S_PARAM_PORT} --nonceByteCount {K8S_PARAM_NONCEBYTECOUNT} --compress\"\n" +
            "                            }\n" +
            "                        ],\n" +
            "                        \"volumeMounts\": [\n" +
            "                            {\n" +
            "                                \"name\": \"pir-data\",\n" +
            "                                \"mountPath\": \"{K8S_PARAM_DB_PATH}\"\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"volumes\": [\n" +
            "                    {\n" +
            "                        \"name\": \"pir-data\",\n" +
            "                        \"persistentVolumeClaim\": {\n" +
            "                            \"claimName\": \"{K8S_PARAM_LONGHORN_NAME}\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        },\n" +
            "        \"affinity\": {\n" +
            "            \"podAntiAffinity\": {\n" +
            "                \"preferredDuringSchedulingIgnoredDuringExecution\": [\n" +
            "                    {\n" +
            "                        \"weight\": 100,\n" +
            "                        \"podAffinityTerm\": {\n" +
            "                            \"topologyKey\": \"kubernetes.io/hostname\",\n" +
            "                            \"labelSelector\": {\n" +
            "                                \"matchLabels\": {\n" +
            "                                    \"module\": \"{K8S_PARAM_GROUP_NAME}\"\n" +
            "                                }\n" +
            "                            }\n" +
            "                        }\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}";

    String SERVICE_CREATE = "{\n" +
            "    \"apiVersion\": \"v1\",\n" +
            "    \"kind\": \"Service\",\n" +
            "    \"metadata\": {\n" +
            "        \"name\": \"{K8S_PARAM_NAME}\",\n" +
            "        \"namespace\": \"{K8S_PARAM_NAMESPACE}\",\n" +
            "        \"labels\": {\n" +
            "            \"app\": \"{K8S_PARAM_APP}\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"spec\": {\n" +
            "        \"type\": \"NodePort\",\n" +
            "        \"ports\": [\n" +
            "            {\n" +
            "                \"port\": {K8S_PARAM_PORT},\n" +
            "                \"targetPort\": {K8S_PARAM_PORT},\n" +
            "            }\n" +
            "        ],\n" +
            "        \"selector\": {\n" +
            "            \"app\": \"{K8S_PARAM_APP}\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
}
