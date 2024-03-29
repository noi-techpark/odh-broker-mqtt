# Flightdata

This project contains configurations and tools to setup a
[MQTT](https://mqtt.org/) broker with [mosquitto](https://mosquitto.org/) using
[Docker](https://www.docker.com/).

By default, the broker allows anonymous access to a configurable topic and
restricted access for a single (configurable) user to a (configurable)
restricted topic. Take a look at the  the [env.example](./env.example) file to
see how you can configure it.

If you want to provide a custom MQTT configuration (e.g. authentication /
authorization), take a look at [Custom MQTT
configuration](#custom-mqtt-configuration) section.

**Table of Contents**
- [Flightdata](#flightdata)
  - [Getting started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Source code](#source-code)
    - [Execute with Docker](#execute-with-docker)
    - [Custom MQTT configuration](#custom-mqtt-configuration)
      - [Config Files](#config-files)
  - [FAQ](#faq)
    - [Why do I not get feedback when publishing to a topic](#why-do-i-not-get-feedback-when-publishing-to-a-topic)
    - [Default topics](#default-topics)
    - [Testing](#testing)
      - [How can I check if publishing succeeded?](#how-can-i-check-if-publishing-succeeded)
      - [How do I publish / subscribe to the MQTT broker?](#how-do-i-publish--subscribe-to-the-mqtt-broker)
  - [Information](#information)
    - [Guidelines](#guidelines)
    - [Support](#support)
    - [Contributing](#contributing)
    - [Documentation](#documentation)
    - [Versioning](#versioning)
    - [License](#license)
    - [Authors](#authors)

## Getting started

These instructions will get you a copy of the project up and running on your
local machine for development and testing purposes.

### Prerequisites

To build the project, the following prerequisites must be met:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### Source code

Get a copy of the repository:

```bash
git clone https://github.com/noi-techpark/odh-broker-mqtt.git
```

Change directory:

```bash
cd odh-broker-mqtt/
```

### Execute with Docker

Copy the file `.env.example` to `.env` and adjust the configuration parameters.

```bash
cp env.example .env
```

Then you can start the MQTT broker using the following command:

```bash
docker-compose up
```

The service will be available at localhost and your specified server port.

### Custom MQTT configuration

If you want to provide a custom MQTT configuration, you have to do three things:

- target the base image of the provided Dockerfile
- provide your [config files](#config-files) when you start Docker Compose
- change the Docker Compose command to start mosquitto with your configuration

> Note: if you provide your own config files, the settings in a `.env` file
> becomes irrelevant.

The best way to provide your own config is to override the relevant parts in a
`docker-compose.override.yml` file (take a look at
[https://docs.docker.com/compose/extends](https://docs.docker.com/compose/extends)
for more info on Docker compose config override).

This project provides a
[docker-compose.override.yml.example](./docker-compose.override.yml.example)
file that you can use as base.

Copy the file `docker-compose.override.yml.example` to
`docker-compose.override.yml` and adjust the configuration parameters.

```bash
cp docker-compose.override.yml.example docker-compose.override.yml
```

provide your config files inside the `./config` folder. Finally, start the MQTT
broker using the following command:

```bash
docker-compose up
```

> Note: just modify the `docker-compose.yml` file if you don't want to handle an
> additional `docker-compose.override.yml` file.

#### Config Files

Rename `example-config` to `config`, and mount that folder, when you start the
container. Files are as follows:
- `aclfile`: See [Default topics](#default-topics) for some explanations
- `passwordfile`: Each configured username and encrypted password (use
  `mosquitto_passwd` to create new ones)
- `mosquitto.conf`: The main config file 

For more information look into [Mosquitto
Docs](https://mosquitto.org/documentation/).

## FAQ

### Why do I not get feedback when publishing to a topic

In general, if you want to know if publishing to a topic succeeded, subscribe to
that topic. All of your published messages should show up in your subscription.

For topics that need authentication, this is not always true. You may be allowed
to subscribe to a topic as anonymous, but due to access control restrictions on
that topic you may not see your published messages. You may even not notice that
publishing did not succeed when you are not allowed to.

If in doubt, please first double check that you use the correct topic and
credentials (where necessary). If you still have troubles, please contact our
[support](#support).

### Default topics

Given that you don't provide your own mosquitto config (see [Custom MQTT
configuration](#custom-mqtt-configuration) on how to do this), mosquitto defines
two topics:

- `/open/#`: anyone can subscribe / publish to this topic (anonymous access)
- `flightdata`: special topic with authentication to publish / subscribe
  flightdata (see
  [https://github.com/noi-techpark/odh-datacollector-flightdata](https://github.com/noi-techpark/odh-datacollector-flightdata)).

### Testing

#### How can I check if publishing succeeded?

In general, if you want to know if publishing to a topic succeeded, subscribe to
that topic. All of your published messages should show up in your subscription.

For topics that need authentication, this is not always true. You may be allowed
to subscribe to a topic as anonymous, but due to access control restrictions on
that topic you may not see your published messages. You may even not notice that
publishing did not succeed when you are not allowed to.

If in doubt, please first double check that you use the correct topic and
credentials (where necessary). If you still have troubles, please contact our
[support](#support).

#### How do I publish / subscribe to the MQTT broker?

You can use any MQTT client you want to publish messages to the MQTT broker,
e.g. [MQTT Explorer](http://mqtt-explorer.com/) or [MQTTX](https://mqttx.app/). 

You can also use the mosquitto publish / subscribe features provided by
mosquitto. 

Use the following command to subscribe to the topic `/open/test` on localhost:

```bash
# Subscribe to the topic /open/test using the MQTT instance on localhost

docker run -it --rm --name mosquitto-sub --network="host" eclipse-mosquitto:2.0.12 sh -c "mosquitto_sub -t '/open/test' -h localhost"
```

Using the Docker version of mosquitto, you can publish a message to topic
`/open/test` on localhost using the following command:

```bash
# Publish the message "some" (= valid JSON string) to the topic /open/test using the MQTT instance on localhost
# Please make sure that you correctly escape the double ticks in the message in order to provide valid JSON. 

docker run -it --rm --name mosquitto-pub --network="host" eclipse-mosquitto:2.0.12 sh -c "mosquitto_pub -t '/open/test' -h localhost -m '\"some\"'"
```

No message arrives? Check your permissions when you publish or subscribe to a
certain topic.


## Information

### Guidelines

Find [here](https://opendatahub.readthedocs.io/en/latest/guidelines.html)
guidelines for developers.

### Support

For support, please contact
[help@opendatahub.com](mailto:help@opendatahub.com).

### Contributing

If you'd like to contribute, please follow the following instructions:

- Fork the repository.

- Checkout a topic branch from the `development` branch.

- Make sure the tests are passing.

- Create a pull request against the `development` branch.

A more detailed description can be found here:
[https://github.com/noi-techpark/documentation/blob/master/contributors.md](https://github.com/noi-techpark/documentation/blob/master/contributors.md).

### Documentation

More documentation can be found at
[https://opendatahub.readthedocs.io/en/latest/index.html](https://opendatahub.readthedocs.io/en/latest/index.html).

### Versioning

This project uses [SemVer](https://semver.org/) for versioning. For the versions
available, see the [tags on this
repository](https://github.com/noi-techpark/odh-broker-mqtt/tags).

### License

The code in this project is licensed under the GNU AFFERO GENERAL PUBLIC LICENSE
Version 3 license. See the [LICENSE.md](LICENSE.md) file for more information.

### Authors

- **Christian Gapp** - *Initial work* - [gappc](https://github.com/gappc)
