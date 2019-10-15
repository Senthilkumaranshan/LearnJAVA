From java:8
WORKDIR usr/app
copy ./target/ems-data-jpa-0.0.1-SNAPSHOT.jar /usr/app
run sh -c 'touch ems-data-jpa-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","ems-data-jpa-0.0.1-SNAPSHOT.jar"]