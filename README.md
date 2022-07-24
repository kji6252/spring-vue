# Project Setup


## Common [gradle/](gradle/)
1. [gradle-node-plugin](https://github.com/node-gradle/gradle-node-plugin/blob/3.4.0/docs/usage.md) - Spring Boot + Vue 동시 빌드 [src/main/resources/static](src/main/resources/static) 에 Vue Static Resource 생성

## Backend [build.gradle](build.gradle)
1. [Spring Initializr](https://start.spring.io) - 프로젝트 생성 및 스프링 디펜던시 활용
2. [Spring Security](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html) - 기본 로그인 Basic Auth 사용
3. [Mapstruct](https://mapstruct.org/documentation/stable/reference/html/) - DTO, VO, Entity, VM 컨버팅을 위해 사용
4. [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign) HttpClient, 사용하기 쉬운 설정, FallBack 처리를 위해 추가
5. [Caffeine Cache](https://github.com/ben-manes/caffeine) - Local Cache
6. [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator) Cache Hit 확인 및 Metric 확인용
   1. [Prometheus](https://prometheus.io/)

## Frontend [frontend/package.json](frontend/package.json)
1. [Vue CLI](https://cli.vuejs.org/) - 프론트엔드 페이지 구성
2. [axios](https://axios-http.com/kr/docs/intro) - HttpClient
3. [Vue vuelidate](https://github.com/vuelidate/vuelidate) - Vue 에서 회원 가입시 벨리데이션 체크

# 링크
* [구글 드라이브 링크](https://drive.google.com/file/d/1eQiCtmahb3y6SRzcuvN4N2Vc-oVpq_7O/view?usp=drivesdk)
* [Executable jar](spring-vue-0.0.1-SNAPSHOT.jar)
