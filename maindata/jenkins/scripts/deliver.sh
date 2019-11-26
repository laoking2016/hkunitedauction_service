#!/usr/bin/env bash

servicename=$1

serviceport=$2

host=$3

hostuser=$4

version=$5

dburl=$6

dbname=$7

dbpassword=$8

basecenterurl=$9

maindatacenterurl=$10

rm -rf /home/$hostuser/$servicename-*.jar

cp ./$servicename/target/$servicename-*.jar /home/$hostuser/

chown $hostuser. /home/$hostuser/$servicename-*.jar

ssh root@$host rm -rf /home/$hostuser/$servicename-*.jar

scp /home/$hostuser/$servicename-*.jar root@$host:/home/$hostuser

ssh root@$host chown $hostuser. /home/$hostuser/$servicename-*.jar

ssh root@$host mv /home/$hostuser/$servicename-$version.jar /home/$hostuser/$servicename-$hostuser-$version.jar

ssh root@$host ps aux | grep $servicename-$hostuser | grep -v 'grep' | awk '{print $2}' > pid

PID=`cat pid` 

if [ -z "$PID" ]
then
	echo Application is already stopped
else
	echo kill $PID

    ssh root@$host kill -9 $PID
fi

ssh root@$host "/usr/jdk-12.0.1/bin/java -jar /home/$hostuser/$servicename-$hostuser-$version.jar --server.port=$serviceport --datasource.rds.url='$dburl' --datasource.rds.username=$dbname --datasource.rds.password=$dbpassword --basecenter.address=$basecenterurl --maindatacenter.address=$maindatacenterurl 1>/home/$hostuser/$servicename.log 2>/home/$hostuser/$servicename_error.log &"
