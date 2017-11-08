FROM java:8-alpine
#MAINTAINER Your Name <your@email.tld>

EXPOSE 8080

RUN mkdir -p /app
COPY target/scala-$scalaMajMin$/*.jar /app/

WORKDIR /app

CMD java -jar ./$name;format="Camel"$.jar
# override CMD from your run command, or k8s yaml, or marathon json, etc...

