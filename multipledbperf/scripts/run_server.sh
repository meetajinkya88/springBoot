#!/usr/bin/env bash

echo 'starting Customer Persitence in S3 script'

ARGV="$@"

export JAVA_HOME="/opt/jdk1.8.0_144/"
export BASE="./"
export CURRENT_TIME=`date +%y%m%d-%H%M%S`

export OPTS="-Dserver.port=20443 -Xmx1024m -Dspring.profiles.active=dev \
-Dlogging.level.root=INFO \
-Dlogging.level.org.-Dspringframework=INFO \
-Dlogging.level.com.alacriti=INFO \
-Dspring.main.banner-mode=off \
-Dspring.datasource.url=jdbc:mysql://192.168.35.103:3315/op_dev?useSSL=false \
-Dspring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver \
-Dspring.datasource.username=op_dev \
-Dspring.datasource.password=op_dev \
-Dspring.datasource.tomcat.max-wait=20000 \
-Dspring.datasource.tomcat.max-active=50 \
-Dspring.datasource.tomcat.max-idle=20 \
-Dspring.datasource.tomcat.min-idle=15  \
-Dspring.datasource.hikari.connection-timeout=60000 \
-Dspring.datasource.hikari.maximum-pool-size=20 \
-Dcom.alacriti.aws.s3.bucketname=alac-amf-lazy-load \
-Dcom.alacriti.aws.s3.region=us-west-1 \
-Dspring.data.mongodb.authentication-database=admin \
-Dspring.data.mongodb.username=admin \
-Dspring.data.mongodb.password=manager \
-Dspring.data.mongodb.database=admin \
-Dspring.data.mongodb.port=27017 \
-Dspring.data.mongodb.host=192.168.32.163
"

#-Dspring.datasource.url=jdbc:mysql://192.168.35.103:3315/op_dev?useSSL=false \
#-Dspring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#-Dspring.datasource.url=jdbc:postgresql://192.168.32.163:5432/op_dev \
#-Dspring.datasource.driver-class-name=org.postgresql.Driver

#-Dspring.data.mongodb.host=192.168.35.189 \
#-Dspring.data.mongodb.port=27017 \
#-Dspring.data.mongodb.database=op_dev

echo "Starting Process ..."
echo "$CURRENT_TIME Starting Process ...." >>$BASE

echo "$JAVA_HOME"

echo "Java Propertie are: $OPTS"
echo "Starting process. Using  JAVA_HOME $JAVA_HOME"
$JAVA_HOME/bin/java $OPTS -jar ./multipledbperf-0.0.1-SNAPSHOT.jar
echo "Successfully started process with PID `cat $PID`"





