#!/usr/bin/env bash
mvn -B -s settings.xml -DskipTests=true clean install
java -Dspring.profiles.active="datajpa,heroku" -DDATABASE_URL="postgres://java:password@localhost:5432/topjava" -jar target/dependency/webapp-runner.jar target/*.war
