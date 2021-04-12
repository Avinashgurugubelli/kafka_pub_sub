# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#using-boot-devtools)

- Application port is comfigured as 5666 by default, it can be changed at any given point by modifying 'server.port' in application.properties.
- swagger UI URL: http://localhost:5666/swagger-ui.html

#### Maven commands:

- To build the solution: ``mvn clean install``
- To run the APP from command prompt (CMD): ``mvn spring-boot:run``
- To Install ojdbc to maven project
  ``mvn install:install-file -Dfile=<file location\JDBC\ojdbc7-12.1.0.2.jar> -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar``

### How to setup Kafka in local?
- setup on windows refer: https://www.goavega.com/install-apache-kafka-on-windows/
- to start zookeeper: <C:\file-location>\kafka_2.12-2.5.0\bin\windows>zookeeper-server-start.bat ..\..\config\zookeeper.properties
- to start kafka: <C:\file-location>\kafka_2.12-2.5.0\bin\windows>kafka-server-start.bat ..\..\config\server.properties

- #### To create topic:
    - refer: https://kafka.apache.org/quickstart
    - <C:\file-location>\kafka_2.12-2.5.0\bin\windows>kafka-topics.bat -create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test

- #### To view created topic list:
    - <C:\file-location>\kafka_2.12-2.5.0\bin\windows>kafka-topics.bat --list --bootstrap-server localhost:9092

- #### To start kafka consumer in console:
    - <C:\file-location>\kafka_2.12-2.5.0\bin\windows>kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --group testGroup  --from-beginning

- #### To start kafka producer in console:
    - <C:\file-location>\kafka_2.12-2.5.0\bin\windows>kafka-console-producer.bat --bootstrap-server localhost:9092 --topic test

