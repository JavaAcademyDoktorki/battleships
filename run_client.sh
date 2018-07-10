#!/bin/bash
set -e
mvn clean install
cd client/
mvn exec:java
