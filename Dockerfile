FROM jenkins/jenkins:lts
USER root
RUN apt-get update && apt-get install -y maven default-jdk
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN sudo apt install ./google-chrome-stable_current_amd64.deb 
