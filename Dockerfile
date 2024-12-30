FROM node:12.16.3

WORKDIR /code

ENV NODE_VERSION=14.17.0

COPY package.json /code/package.json

RUN npm install

COPY . /code

CMD [ "node", "src/server.js" ]