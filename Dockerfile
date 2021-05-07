FROM java:8
COPY build/libs/CurriculumService-1.0-SNAPSHOT.jar .
EXPOSE 80
CMD java -jar CurriculumService-1.0-SNAPSHOT.jar
