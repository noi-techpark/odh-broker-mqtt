# The base image is just a thin wrapper around mosquitto
# It dose not include any additional config. If you want
# to provide your own config files, mount them during container
# startup.

# Note: eclipse mosquitto uses Alpine Linux
FROM eclipse-mosquitto:2.0.12 as base

ARG JENKINS_GROUP_ID=1000
ARG JENKINS_USER_ID=1012

RUN addgroup --gid $JENKINS_GROUP_ID jenkins && \
   adduser -u 1000 -G jenkins -g jenkins -H -D jenkins

# This image provides MQTT auth that is configured by
# the project env variables AUTH_USER, AUTH_PASSWORD,
# TOPIC_ANONYMOUS and TOPIC_RESTRICTED
FROM base as configured

RUN mkdir -p /mosquitto/config/conf.d

RUN echo "listener 1883" >> /mosquitto-with-auth.conf && \
   echo "user mosquitto" >> /mosquitto-with-auth.conf && \
   echo "allow_anonymous true" >> /mosquitto-with-auth.conf && \
   echo "password_file /mosquitto/config/conf.d/passwordfile" >> /mosquitto-with-auth.conf && \
   echo "acl_file /mosquitto/config/conf.d/aclfile" >> /mosquitto-with-auth.conf

COPY infrastructure/docker/entrypoint.sh /entrypoint.sh
ENTRYPOINT [ "/entrypoint.sh" ]
