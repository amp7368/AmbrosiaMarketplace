ARG NODE_VERSION

FROM node:${NODE_VERSION} as installstage
WORKDIR /base

COPY ./package*.json ./
RUN npm install 

FROM node:${NODE_VERSION} as copystage
WORKDIR /base
COPY ./nx.json ./
COPY ./workspace.json ./
COPY ./apps ./apps
COPY ./libs ./libs
COPY ./tsconfig* ./
COPY ./babel* ./

FROM node:${NODE_VERSION} as buildstage
WORKDIR /base
COPY --from=copystage /base /base
COPY --from=installstage /base /base
RUN npx nx affected:build --configuration=production --parallel --all
# RUN npm prune --production

FROM node:${NODE_VERSION} as servestage
COPY --from=buildstage /base/dist /base/dist
COPY --from=buildstage /base/libs /base/libs
COPY --from=buildstage /base/node_modules /base/node_modules

ENTRYPOINT ["tail", "-f", "/dev/null"]
