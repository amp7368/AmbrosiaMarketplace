ARG COMPOSE_NAME
ARG BASE_IMAGE
ARG NGINX_VERSION

FROM ${COMPOSE_NAME}/${BASE_IMAGE} as base
FROM ${NGINX_VERSION} as servestage
ARG WORKINGDIR
ENV PROJECT_DIR ./apps/nginx
WORKDIR ${WORKINGDIR}

COPY --from=base /base/dist/${PROJECT_DIR} ./dist/${PROJECT_DIR}
COPY --from=base /base/node_modules ./node_modules

RUN rm /etc/nginx/conf.d/default.conf
COPY ${PROJECT_DIR}/templates  /etc/nginx/templates
