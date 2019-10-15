From java:8
WORKDIR usr/app
copy ./target/sms-ui-0.0.1-SNAPSHOT.jar /usr/app
run sh -c 'touch sms-ui-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","sms-ui-0.0.1-SNAPSHOT.jar"]