FROM node:17-alpine
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
RUN yarn
COPY . ./
EXPOSE 3000
CMD ["yarn", "start"]