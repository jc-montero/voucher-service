FROM postgres

MAINTAINER jc.montero.bonilla@gmail.com

ENV POSTGRES_PASSWORD admin

ENV POSTGRES_DB mrjeff

ADD create-db.sql /docker-entrypoint-initdb.d/create-db.sql