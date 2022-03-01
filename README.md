<div align="center">
  <a href="https://github.com/castanhocorreia/loqui">
    <img src="icon.svg" alt="Logo" width="80" height="80">
  </a>
  <h3 align="center">LOQUI</h2>
  <h6 align="center">Real-time chat application built using Apache Kafka, Java, Spring Boot, SockJS and React</h6>
</div>

### About

LOQUI is a simple real-time chat application that demonstrates how to use Apache Kafka as a message broker along with Java, Spring Boot and React on the front-end

**This project is a case study and should not be used for any real purpose**


### Technologies

- [Apache Kafka](https://kafka.apache.org/)
- [Axios](https://github.com/axios/axios)
- [Docker](https://www.docker.com/)
- [Java](https://www.oracle.com/java/)
- [Project Lombok](https://projectlombok.org/)
- [React](https://reactjs.org/)
- [SockJS](https://github.com/sockjs/sockjs-client)
- [Spring Boot](https://spring.io/)

### Requisites

- Docker version 20 or higher
- docker-compose version 1 or higher
- Ports 3000, 8080, 22181 and 29092

### Instalation

Clone the project, and run the following command in the root directory:

```bash
docker-compose up -d --build
```

The client will be running on localhost, port 3000

### License

All the code on this repository is licensed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html)
