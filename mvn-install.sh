#!/bin/sh

mvn install:install-file -DgroupId=org.mongodb -DartifactId=mongo-hadoop-core_cdh3u3 -Dversion=1.0.0-cascading-rc1-SNAPSHOT -Dpackaging=jar -Dfile=core/target/mongo-hadoop-core_cdh3u3-1.0.0-cascading-rc1-SNAPSHOT.jar

