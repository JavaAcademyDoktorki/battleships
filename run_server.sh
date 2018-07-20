#!/bin/bash
set -e
if [ ! -f server/target/server-$(cat current_version)-jar-with-dependencies.jar ]; then
	mvn clean install
fi
cd server/
mvn exec:java
