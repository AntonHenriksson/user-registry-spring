#!/bin/bash
cd /home/ec2-user

docker-compose down

docker system prune -f

docker-compose pull app

docker-compose up -d app