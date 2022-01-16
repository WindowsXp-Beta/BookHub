# BookHub——An E-Book store
> Hw and projects of SE128 Design and development of Internet products and SE3353 Architecture of Applications

## Technology stack

Frontend: [React](https://reactjs.org/)

Backend: [SpringBoot](https://spring.io/projects/spring-boot)

Database: [MySQL](https://www.mysql.com/)

Micro Services: [Eureka](https://spring.io/projects/spring-cloud-netflix)

Cache: [Redis](https://redis.io/)

Containerize: [docker](https://www.docker.com)

Full text search: [Lucene](https://lucene.apache.org/)

MR text analysis: [Hadoop](https://hadoop.apache.org/)

Load balancing: [Nginx](https://www.nginx.com/)

## Getting Started
I strongly recommend you use docker to deploy this App and you may refer to this [report](doc_assets/Clustering%20&%20Docker.md) for a detailed guide.

## Change Log

- v1.0 SE128 course project

  finish all the demands in [互联网开发技术大作业要求](doc_assets/互联网应用开发技术大作业要求-2021-new.pdf).

- v1.1 considerable code refactor including

  BackEnd

  1. RESTful API design.
  2. Fine-grained access control using HttpSession.
  3. Better data manipulation using spring JPA.
  4. All lists use pagination to boost the performance.
  5. Using DTO to transfer data in response and request.
  
- v2.0

  1. Using JMS as a message queue to clip the flow.
  2. A chat room based on webSocket.
  3. Full-text search using Lucene.
  4. Using Redis to cache book-getting requirements.
  5. Spring cloud micro services.
  6. Dockerize.
  7. load banlancing using nginx.
