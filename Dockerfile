FROM openjdk:17-bullseye

WORKDIR /usr/lib/home-services

RUN groupadd spring && adduser --disabled-password --gecos ""  --ingroup spring spring && \
	chown -R spring:spring /usr/lib/home-services && \
	mkdir -p /opt/newrelic && \
	chown -R spring:spring /opt/newrelic
	
RUN curl -fsSL https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-agent.jar > /opt/newrelic/newrelic.jar && \
	chmod +x /opt/newrelic/newrelic.jar

COPY ./target/services-*.jar app.jar
COPY ./newrelic.yml /opt/newrelic/newrelic.yml

USER spring:spring

ENTRYPOINT [ "sleep", "3600" ]