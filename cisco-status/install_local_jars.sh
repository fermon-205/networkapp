#!/usr/bin/env bash

mvn install:install-file -Dfile="ncs-4.1.1.1.jar" -DgroupId=com.ncs -DartifactId=ncs-api -Dversion=1.0 -Dpackaging=jar;
mvn install:install-file -Dfile="conf-api-6.1.1.jar" -DgroupId=com.ncs -DartifactId=conf-api -Dversion=1.0 -Dpackaging=jar;