FROM centos:7

#Systemd is now included in both the centos:7 and centos:latest base containers, but it is not active by default. 
#In order to use systemd, you will need to include text similar to the example Dockerfile below:

ENV container docker

RUN (cd /lib/systemd/system/sysinit.target.wants/; for i in *; do [ $i == \
systemd-tmpfiles-setup.service ] || rm -f $i; done); \
rm -f /lib/systemd/system/multi-user.target.wants/*;\
rm -f /etc/systemd/system/*.wants/*;\
rm -f /lib/systemd/system/local-fs.target.wants/*; \
rm -f /lib/systemd/system/sockets.target.wants/*udev*; \
rm -f /lib/systemd/system/sockets.target.wants/*initctl*; \
rm -f /lib/systemd/system/basic.target.wants/*;\
rm -f /lib/systemd/system/anaconda.target.wants/*;

RUN /usr/sbin/init

VOLUME [ "/sys/fs/cgroup" ]

RUN yum install -y  tar curl bind-utils unzip wget vim nano sudo sshpass net-tools iputils lsof \
												krb5-workstation openldap-clients nss-pam-ldapd chrony initscripts \
    										&& yum clean all
    										
RUN rm -rf /var/cache/yum; \
    echo "NETWORKING=yes" >/etc/sysconfig/network; \
		rm -rf /tmp/*; \
		echo "export $(cat /proc/1/environ |tr '\0' '\n' | xargs)" >> /etc/profile; \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime;
		
ENV JAVA_HOME=/usr/jdk64

RUN chmod 777 /usr/jdk64/bin/*

ENV JRE_HOME=$JAVA_HOME/jre

COPY /usr/jdk64 /usr/jdk64

ENV PATH=.:$PATH:$JAVA_HOME/bin:$JRE_HOME/bin

COPY /home/test/basecenter-1.0.jar /root/basecenter-1.0.jar

CMD ["java -jar /root/basecenter-1.0.jar"]