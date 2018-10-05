#!/usr/bin/env bash

java -jar spring-cloud-dataflow-server-local-1.6.3.RELEASE.jar \
    --spring.datasource.url=jdbc:mysql://localhost:3306/scdf \
    --spring.datasource.username=root \
    --spring.datasource.password=dataflow \
    --spring.datasource.driver-class-name=org.mariadb.jdbc.Driver \
    --spring.rabbitmq.host=127.0.0.1 \
    --spring.rabbitmq.port=5672 \
    --spring.rabbitmq.username=guest \
    --spring.rabbitmq.password=guest
