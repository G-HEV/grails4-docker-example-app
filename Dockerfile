FROM debian:sid

RUN set -eux; \
	apt-get update; \
	apt-get install -y --no-install-recommends \
		xz-utils \
        locales \
        openjdk-11-jre-dcevm \
# utilities for keeping Debian and OpenJDK CA certificates in sync
		ca-certificates p11-kit \
		fontconfig libfreetype6

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8