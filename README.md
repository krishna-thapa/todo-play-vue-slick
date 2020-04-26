# Simple seed for Scala as back-end service and Vue.js as front-end service

Simple demo web-based application using Scala and play framework to create API service as in back-end service that can be connect to database. Where as Vue.js framework is used in the front-end that request and response through API service.

## Used framework and languages:
- [Scala 2.13.1](https://www.scala-lang.org/)
- [sbt 1.3.8] (https://www.scala-sbt.org/)
- [Play 2.8.0](https://www.playframework.com/)
- [Vue.js](https://vuejs.org/)

## Prerequisites
- Scala and sbt with Java 8 and higher
- [npm and node.js](https://nodejs.org/en/) and vue

## Why?
- Both back-end and front-end in a single project, in a totally integrated workflow and single unified console.
- Deliver perfect development experience without [CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS) hassle
- Build both frontend and backend in the same project: Use play static routes to serve frontend and communicate with backend using the REST interface.

## Pros:
- Play activator associated commands (run, build, publish, etc..) to triggers frontend tasks associated (no need to worry how things get wired up).
- Painless development experience (with play run hooks). Watch task with live reload and what not.
- No more CORS hassle, secure out of the box.
- Clear separation between frontend and backend code.
- Frontend code is served via the same server instance which is serving the backend.
- Easy CI/CD integration.

## Cons:
- Need to write proxy API’s to access micro services to ensure security (which is not a bad thing anyway).
- Frontend cannot be deployed in a [CDN](https://en.wikipedia.org/wiki/Content_delivery_network) directly (It is technically feasible using a [reverse proxy](https://en.wikipedia.org/wiki/Reverse_proxy)).

## How to use
- git clone the project and open the project as a sbt project using any IDE
- All the front-end related codes are inside front-end folder, project is not using any scala play views
- 

## Resources used:
- [React with Play Framework 2.7.x](https://blog.usejournal.com/react-with-play-framework-2-6-x-a6e15c0b7bd)
- [Scala Play Vue Seed](https://github.com/duncannevin/scala-play-vue-seed)
- [Running Frontend and Backend Development Servers Together](https://vsupalov.com/combine-frontend-and-backend-development-servers/)