## Setup Kafka

### Steps
1. Start it
```shell
./zookeeper-server-start.sh ../config/zookeeper.properties
```
And
```shell
./kafka-server-start.sh ../config/server.properties
```

2. Create Topics
```shell
./kafka-topics.sh --bootstrap-server localhost:9092 --create -topic command-topic
./kafka-topics.sh --bootstrap-server localhost:9092 --create -topic event-topic  
```

3. List all topics
```shell
./kafka-topics.sh --bootstrap-server localhost:9092  --list localhost:2181
```

4. Send to topic from file
```shell
more ../../../stateful_example_1.json |  ./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic command-topic
```

5. Read from topic
```shell
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic command-topic
```

## Setup Kafka in Docker

### Steps
1. Start Kafka
```shell
docker compose -f kafka-docker-compose.yml up -d
```
2. Create Topics
```shell
docker exec broker kafka-topics --bootstrap-server broker:9092 --create -topic command-topic
docker exec broker kafka-topics --bootstrap-server broker:9092 --create -topic event-topic  
```
3. List all topics
```shell
 docker exec broker kafka-topics --bootstrap-server broker:9092  --list localhost:2181
```
4. Send messages interactively
```shell
docker exec --interactive --tty broker \
kafka-console-producer --bootstrap-server broker:9092 \
                       --topic command-topic                      
```

5. Send message from file 
```shell
more stateful_example_1.json | docker exec --interactive broker \
kafka-console-producer --bootstrap-server broker:9092 \
                       --topic command-topic -
```

5. Consume messages
```shell
   docker exec --interactive --tty broker \
   kafka-console-consumer --bootstrap-server broker:9092 \
   --topic event-topic \
   --from-beginning
```

6. Stop Kafka
```shell
docker compose -f kafka-docker-compose.yml down
```

### Links
-  [Kafka on Docker](https://developer.confluent.io/quickstart/kafka-docker/)
- [Commands](https://gist.github.com/DevoKun/01b6c9963d5508579f4cbd75d52640a9)


## Setup RabbitMQ 
### Steps
1. Get the image
```shell
docker pull rabbitmq:3-management
```
2. Run it
```shell
docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```
3. Confirm running by going at [http://localhost:15672/](http://localhost:15672/) user/pass is guest/guest

### Links
- [RabbitMQ on Docker](https://www.architect.io/blog/2021-01-19/rabbitmq-docker-tutorial/)


## Kafka Client
This process simulates someone sending commands to and receiving events from the service

Project initialized with 
```shell
java -jar swagger-codegen-cli-3.0.45.jar generate \
-i ./stateful-events.json \
--api-package shovell.api \
--model-package shovell.model \
--invoker-package shovel.invoker \
--group-id shovell \
--artifact-id stateful-events-client \
--artifact-version 0.0.1-SNAPSHOT \
-l java --library resttemplate \
-o stateful-events-client


```
