#!/usr/bin/env bash

mvn clean package

cd where-go-server
mvn docker:build

cd ../where-go-data
mvn docker:build

cd ../where-go-email
mvn docker:build

cd ../where-go-map
mvn docker:build


cd ../where-go-order
mvn docker:build

cd ../where-go-search
mvn docker:build

cd ../where-go-user
mvn docker:build

# docker run -d -p 8501:8501 eureka-server:0.0.1-SNAPSHOT
# docker run -d -p 8888:8888 config-server:0.0.1-SNAPSHOT
# docker run -d -p 9411:9411 zipkin-server:0.0.1-SNAPSHOT
# docker run -d -p 9999:9999 api-gateway:0.0.1-SNAPSHOT

# docker run -d -p 8001:8001 user-service-core:0.0.1-SNAPSHOT
# docker run -d -p 8002:8002 score-service-core:0.0.1-SNAPSHOT