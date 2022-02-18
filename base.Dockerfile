FROM node:17-alpine3.14 as buildstage
WORKDIR /base

COPY ./package*.json ./
RUN npm install --only=prod
RUN npm prune --production
RUN npm install @nrwl/workspace

COPY ./nx.json ./
COPY ./workspace.json ./
COPY ./apps ./apps
COPY ./libs ./libs
COPY ./tsconfig* ./
COPY ./babel* ./

RUN npx nx affected:build --configuration=production --parallel --all

FROM node:17-alpine3.14 as servestage
COPY --from=buildstage /base/dist /base/dist
COPY --from=buildstage /base/libs /base/libs
COPY --from=buildstage /base/node_modules /base/node_modules

ENTRYPOINT ["tail", "-f", "/dev/null"]
