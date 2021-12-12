# Flightdata

This project contains configurations and tools to setup a [MQTT](https://mqtt.org/) broker with [mosquitto](https://mosquitto.org/) using [Docker](https://www.docker.com/).

The broker allows anonymous access to a configurable topic and restricted access for a single (configurable) user to a (configurable) restricted topic. Take a look at the  the [env.example](./env.example) file to see how you can configure it.

## Table of Contents

- [Getting started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Source code](#source-code)
  - [Execute with Docker](#execute-with-docker)
- [Information](#configuration)

## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

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

## Information

### Guidelines

Find [here](https://opendatahub.readthedocs.io/en/latest/guidelines.html) guidelines for developers.

### Support

For support, please contact [info@opendatahub.bz.it](mailto:info@opendatahub.bz.it.

### Contributing

If you'd like to contribute, please follow the following instructions:

- Fork the repository.

- Checkout a topic branch from the `development` branch.

- Make sure the tests are passing.

- Create a pull request against the `development` branch.

A more detailed description can be found here: [https://github.com/noi-techpark/documentation/blob/master/contributors.md](https://github.com/noi-techpark/documentation/blob/master/contributors.md).

### Documentation

More documentation can be found at [https://opendatahub.readthedocs.io/en/latest/index.html](https://opendatahub.readthedocs.io/en/latest/index.html).

### Versioning

This project uses [SemVer](https://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/noi-techpark/odh-broker-mqtt/tags).

### License

The code in this project is licensed under the GNU AFFERO GENERAL PUBLIC LICENSE Version 3 license. See the [LICENSE.md](LICENSE.md) file for more information.

### Authors

- **Christian Gapp** - *Initial work* - [gappc](https://github.com/gappc)
