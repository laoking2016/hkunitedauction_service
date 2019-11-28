#!/usr/bin/env bash

repositoryHost=$0 

servicename=$1

docker build --rm -t local/$servicename

docker tag local/basecenterservice:latest $repositoryHost/library/$servicename:latest

docker push $repositoryHost/library/$servicename:latest
 