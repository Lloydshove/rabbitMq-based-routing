## Setup Kafka

### Steps
1. Start Kafka
2. Create Topics
```shell
docker exec broker kafka-topics --bootstrap-server broker:9092 --create -topic command-topic
docker exec broker kafka-topics --bootstrap-server broker:9092 --create -topic event-topic  
```
3. List all topics
```shell
 docker exec broker kafka-topics --bootstrap-server broker:9092  --list localhost:2181
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
