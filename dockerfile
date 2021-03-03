# Download base image ubuntu 20.04
FROM ubuntu:20.04

# Update Ubuntu Software repository
RUN apt-get update && apt-get upgrade -y --no-install-recommends apt-utils

# arguments variables
ARG MAVEN_VERSION=3.6.0
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

# enviroment variables
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
ENV MAVEN_HOME /opt/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
ENV DEBIAN_FRONTEND noninteractive

# Install gnup2
RUN apt-get install -y gnupg2

# Install curl
RUN apt-get install -y curl

# Install wget
RUN apt-get install -y wget \
 && rm -rf /var/lib/apt/lists/*

# Install dbus-x11
RUN apt-get update && apt-get install -y dbus-x11

# Install systemd
RUN apt-get update && apt-get install -y systemd

# Install Xvfb
RUN apt-get update && apt-get install -y xvfb \
  && rm -rf /var/lib/apt/lists/* /var/cache/apt/*

# Install Java
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get clean;

# Install maven
RUN apt-get update && apt-get install -y maven
RUN mvn -version

# Install git
RUN apt-get update && apt-get install -y git

# Install chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \ 
  && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list
RUN apt-get update && apt-get -y install google-chrome-stable    

# Generate Machine Id
RUN systemd-machine-id-setup

# Expose Port for the Application 
EXPOSE 80 443