FROM nginx:1.21.6-alpine as servestage

RUN rm /etc/nginx/conf.d/default.conf
COPY ./apps/nginx/*.template  /etc/nginx/templates/
