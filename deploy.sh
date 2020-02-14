#!/bin/bash
echo "Building application by mvn"
mvn clean install
echo "Building image and run container"
docker-compose up -d --build
