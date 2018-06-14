FROM chinapopin.com:18443/k8s/openjdk:8u131-jre-alpine
MAINTAINER ZhouBo 125639751@qq.com

RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g" /etc/apk/repositories && apk add --update && apk add --upgrade
ENV TIME_ZONE Asia/Shanghai
RUN apk add --no-cache tzdata bash \
&& echo "${TIME_ZONE}" > /etc/timezone \
&& ln -sf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime

ENV APP_HOME /popin
RUN mkdir $APP_HOME
RUN mkdir $APP_HOME/template
WORKDIR $APP_HOME


ADD start.sh /bin/start
RUN chmod +x /bin/start
RUN chmod u+x /$APP_HOME/template

LABEL popin whz

ADD demo-*.jar $APP_HOME/demo-web.jar
ADD simsun.ttc $APP_HOME/template
ADD 1.pdf $APP_HOME/template
ADD 2.pdf $APP_HOME/template
ADD 3.pdf $APP_HOME/template
ADD 4.pdf $APP_HOME/template



EXPOSE 80/tcp 8081/tcp 9091/tcp

#CMD ["/bin/bash","-c","start"]

ENTRYPOINT ["/bin/bash","-c","start"]