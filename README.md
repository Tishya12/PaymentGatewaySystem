# PaymentGatewaySystem

Designed a payment gateway system(e-wallet) from scratch which supports these features:

- User registeration on e-wallet.
- Merchant registration on e-wallet
- Payment acceptance by any merchant. 
- Creating user wallet for user registered and enabling it in payments flow.
- Create merchant wallet here where payment money will be credited from one wallet to another.

Following APIs implemented:
1) POST user/register (Take user details as input)
2) POST user/add/money (add amount to user wallet)
3) POST merchant/register
4) POST transaction/initiate -- it should fail if user wallet doesn't have enough balance
5) GET transaction/status -- to be used by transaction id.

Maintained a database for storing user,merchant data,and transaction information.
Used OOPs concepts(inheritance, polymorphism) as much as you can to implement this and try to build this system keeping data security/concurrency issues in mind.

Tech Stack used:
- Java
- MySql
- Spring Boot
- Elastic search
- Kafka


Detailed description-
Created REST application of user management using Spring Boot and Spring Data. This application will perform basic CRUD(Create, Read , Update , Delete) operations on the User table.
1.API which will create a user in the user table.
url:http://localhost:8080/user
METHOD : POST
input:userName , firstName, lastName, mobileNumber , emailID, address
Validations : same email id, userName or phone number (user already exists)

2.API which will read data from the database.
url:http://localhost:8080/user?userId=<userID>
METHOD: GET

3.Update API
url:http://localhost:8080/user
requestParam: userID
METHOD:(PUT)
validation: user should exist

4.API which will delete data in the user table.
url:http://localhost:8080/user
requestParam: userID
validation: user should exist


Wallet Management
1. Create Wallet: API which will create wallet for a user
url:http://localhost:8080/wallet
METHOD : POST
input: phone number
Validations : phone number should exist , only one wallet for a user.

2.API to transfer money from one wallet to another wallet (p2p).
url:http://localhost:8080/transaction
METHOD : POST
input:{payer_phone_number,payee_phone_number,amount}
Validations : payer and payee both should exist, payer should have sufficient balance.

3.Transaction Summary API
url:http://localhost:8080/transactions
METHOD: GET
Note : this api should return in a pagination way.

4.Transaction Status
url:http://localhost:8080/transaction?txnId=<txnID>
Method :GET
Validation: TransactionId should exists

Serve Traffic through load Balancer i.e Nginx.
##nginx

All above apis should have JWT authentication
##jwt


Ingest Data in elasticSearch through Kafka.
Serve transaction through ElasticSearch.

Elastic Search
-Download elastic search
-Run sudo systemctl start/stop elasticsearch
-to check status run sudo systemctl status elasticsearch
-or u can check on localhost:9200

Kafka
-download zookeeper and kafka
-go to kafka/bin folder in terminal and rum
-START THE ZOOKEEPER
./zookeeper-server-start.sh ../config/zookeeper.properties
-START THE KAFKA BROKER
./kafka-server-start.sh ../config/server.properties
-CREATE KAFKA TOPIC
./kafka-topics.sh --create --topic test-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 4/./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic txn_by_id
-start producer
./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic
-start consumer
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning

https://www.getpostman.com/collections/349205d05a236c523677 (for apis)
