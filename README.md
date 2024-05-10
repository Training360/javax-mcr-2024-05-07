# Spring Boot képzés

* `config-demo` - környezetek adatainak kiszervezése properties állományba
* `employees-cli` - parancssoros Springes alkalmazás
* `employees-polling-demo` - Awaitility használata demó
* `format-demo` - Szám és dátumformázás Thymeleaffel, Remote IP kiírása konzolra
* `hello-spring` - CRUD alkalmazás, listában tárolással
* `hello-spring-jdbcclient` - CRUD alkalmazás JdbcClient használatával
* `hello-spring-jdbctemplate` - CRUD alkalmazás JdbcTemplate használatával
* `hello-spring-springdatajdbc` - CRUD alkalmazás Spring Data JDBC használatával
* `hello-spring-springdatajpa` - CRUD alkalmazás Spring Data JPA használatával
* `jenkins-client` - Jenkins jobok listázása
* `testng-demo` - TestNT teszteset futtatása kódból
* `frontend-technologiak.pdf` - Prezentáció a frontend technológiákról

## Logback linkelt log

Logback esetén is lehet IDEA-ban kattintható logot írni, ehhez a következőt kell beírni
az `application.properties` állományba.

```properties
logging.pattern.console=%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd'T'HH:mm:ss.SSSXXX}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %X{remoteAddress} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m %c.%M\\(%F:%L\\) %n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}
```

Ebből a `%c.%M\\(%F:%L\\)` lényeg. A zárójelet duplán kell escape-elni, egyszer a Logbacknek, egyszer a properties miatt.

Lásd a `format-demo\src\main\resources\application.properties` fájlt.

## Több fájlba írás Logbackkel

Ehhez már xml konfiguráció szükséges, egy példa a `format-demo\src\main\resources\logback-sample.xml` fájlban. Csak át kell nevezni `logback.xml` fájlnak.

Log4J2 esetén valahogy így: https://stackoverflow.com/a/35575102/10905502

