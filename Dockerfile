FROM openjdk:8
VOLUME /tmp 
ADD ./target/created-account-profile-0.0.1-SNAPSHOT.jar service-create-accound-profile.jar
ENTRYPOINT [ "java", "-jar","./service-create-accound-profile.jar" ]