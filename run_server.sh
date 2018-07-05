#!/bin/bash
mvn clean install
java -jar server/target/server-0.0.1-jar-with-dependencies.jar
