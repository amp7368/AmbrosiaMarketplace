ARG COMPOSE_NAME
ARG BASE_IMAGE
ARG NGINX_VERSION

FROM ${COMPOSE_NAME}/${BASE_IMAGE} as base
FROM ${NGINX_VERSION} as servestage
ARG WORKINGDIR
WORKDIR ${WORKINGDIR}
ENV PROJECT_DIR ./apps/website

# COPY --from=base /base/node_modules ./node_modules
# COPY --from=base /base/dist/libs ./dist/libs
COPY --from=base /base/dist/${PROJECT_DIR} ./dist/${PROJECT_DIR}

RUN rm /etc/nginx/conf.d/default.conf
COPY ${PROJECT_DIR}/*.template  /etc/nginx/templates/
