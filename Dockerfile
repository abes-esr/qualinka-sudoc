###
# Image pour la compilation de sudoqual-sudoc
FROM maven:3-jdk-11 as build-image
WORKDIR /build/
# Installation et configuration de la locale FR
RUN apt update && DEBIAN_FRONTEND=noninteractive apt -y install locales
RUN sed -i '/fr_FR.UTF-8/s/^# //g' /etc/locale.gen && \
    locale-gen
ENV LANG fr_FR.UTF-8
ENV LANGUAGE fr_FR:fr
ENV LC_ALL fr_FR.UTF-8
# On lance la compilation
# si on a un .m2 local on peut décommenter la ligne suivante pour
# éviter à maven de retélécharger toutes les dépendances
#COPY ./.m2/    /root/.m2/
COPY ./pom.xml /build/pom.xml
COPY ./linking-scenarios-example/  /build/linking-scenarios-example/
COPY ./linking-scenarios-sudoc/   /build/linking-scenarios-sudoc/
COPY ./linking-scenarios-sudoqual1/    /build/linking-scenarios-sudoqual1/
COPY ./web-services/    /build/web-services/
RUN mvn initialize
RUN mvn --batch-mode \
        -Dmaven.test.skip=false \
        -Duser.timezone=Europe/Paris \
        -Duser.language=fr \
        package


###
# Image pour les services web de sudoqual-sudoc
FROM tomcat:9-jdk11 as web-image
COPY --from=build-image /build/web-services/target/*.war /usr/local/tomcat/webapps/ROOT.war
# Installation et configuration de la locale FR
RUN apt update && DEBIAN_FRONTEND=noninteractive apt -y install locales
RUN sed -i '/fr_FR.UTF-8/s/^# //g' /etc/locale.gen && \
    locale-gen
ENV LANG fr_FR.UTF-8
ENV LANGUAGE fr_FR:fr
ENV LC_ALL fr_FR.UTF-8
# Lancement de tomcat
CMD [ "catalina.sh", "run" ]
