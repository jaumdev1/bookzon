FROM postgres:latest

COPY init.sql /docker-entrypoint-initdb.d/


ENV POSTGRES_DB bookzon
ENV POSTGRES_USER admin
ENV POSTGRES_PASSWORD 1234

EXPOSE 5432