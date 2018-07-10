#!/bin/bash
set -e
mvn clean install
cd server/
mvn exec:java
