#!/bin/sh

PASSWORD_FILE=/mosquitto/config/conf.d/passwordfile
ACL_FILE=/mosquitto/config/conf.d/aclfile

# Create password file
echo "${AUTH_USER}:${AUTH_PASSWORD}" > "${PASSWORD_FILE}"
mosquitto_passwd -U "${PASSWORD_FILE}"

# Set ACLs
{
    # Allow read / write to anonymous for topic ${TOPIC_ANONYMOUS}. Note
    # that the topic may specify the "#" wildcard which allows access to
    # the topic and all of its subtopics, e.g. "/open/#" allows access to
    # the topic "/open" and its subtopics (like "/open/one")
    echo "topic readwrite ${TOPIC_ANONYMOUS}"

    # Allow read / write to topic ${TOPIC_RESTRICTED} to user ${AUTH_USER}.
    # Note that user ${AUTH_USER} is not allowed to read / write to / from
    # other topics, because it is allowed to read / write to topic
    # ${TOPIC_RESTRICTED} only.
    echo "user ${AUTH_USER}"
    echo "topic readwrite ${TOPIC_RESTRICTED}"
} >> "${ACL_FILE}"

"$@"
