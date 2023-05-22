# Builds and configures Cloud Beaver image

FROM dbeaver/cloudbeaver:latest
ENV con_conf_path=.data-sources.json
ENV admin_conf_path=.cloudbeaver.auto.conf

COPY ${con_conf_path} ./conf/initial-data-sources.conf
ADD ${admin_conf_path} ./conf/.cloudbeaver.auto.conf
