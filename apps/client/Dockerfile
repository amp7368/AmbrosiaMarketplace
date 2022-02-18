ARG COMPOSE_NAME
ARG BASE_IMAGE
FROM ${COMPOSE_NAME}/${BASE_IMAGE} as base

# serve
FROM nginx:1.21.6-alpine as servestage
ARG WORKINGDIR
WORKDIR ${WORKINGDIR}/final
ENV PROJECT_DIR ./apps/client

RUN rm /etc/nginx/conf.d/default.conf
COPY --from=base /base/dist ./dist
COPY ${PROJECT_DIR}/*.template  /etc/nginx/templates/