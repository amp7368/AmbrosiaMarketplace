ARG COMPOSE_NAME
ARG BASE_IMAGE
ARG NODE_VERSION

FROM ${COMPOSE_NAME}/${BASE_IMAGE} as base
FROM ${NODE_VERSION} as servestage
ARG WORKINGDIR
WORKDIR ${WORKINGDIR}
ENV PROJECT_DIR ./apps/api

COPY --from=base /base/node_modules ./node_modules
COPY --from=base /base/dist/libs ./dist/libs
COPY --from=base /base/dist/${PROJECT_DIR} ./dist/${PROJECT_DIR}

ENTRYPOINT "node" "./dist/${PROJECT_DIR}/main.js"