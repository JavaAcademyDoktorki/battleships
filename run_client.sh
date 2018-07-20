#!/bin/bash
set -e
if [ ! -f client/target/client-$(cat current_version)-jar-with-dependencies.jar ]; then
	mvn clean install
fi
cd client/
mvn exec:java
