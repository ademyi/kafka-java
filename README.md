### Apache Kafka samples with Java

##### Prerequisites
- install Apache Kafka
- run Zookeeper
 ###### Windows
 ```sh
    cd C:\opt\kafka\bin\windows
    zookeeper-server-start.bat  ..\..\config\zookeeper.properties
```
 ######  MacOS & Linux
 ```sh
    cd /Users/adem/dev/kafka_2.12-2.0.0/bin
    ./zookeeper-server-start.sh ../config/zookeeper.properties
```

- run Kafka
 ###### Windows
 ```sh
    kafka-server-start.bat ..\..\config\server.properties
```
 ######  MacOS & Linux
 ```sh
 ./kafka-server-start.sh ../config/server.properties
 ```
- create topic 

 ```sh
.\kafka-topics.bat --zookeeper localhost:2181 --topic first_topic --create --partitions 3 --replication-factor 1
```

##### Summary

 
| Module      | Description |
| --------- | -----|
| kafka-code  | Basic Consumer & Producer API |
| kafka-twitter-producer  | Read data from Twitter through Hosebird Client streaming library  |