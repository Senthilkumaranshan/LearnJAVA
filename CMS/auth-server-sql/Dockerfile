From java:8
WORKDIR usr/app
copy ./target/auth-server-sql-0.0.1-SNAPSHOT.jar /usr/app
run sh -c 'touch auth-server-sql-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","auth-server-sql-0.0.1-SNAPSHOT.jar"]