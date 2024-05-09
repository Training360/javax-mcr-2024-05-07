package hello;

import hello.employee.EmployeeResourceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from employees")
class EmployeesIT {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void create() {
        webTestClient
                .post()
                .uri("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "name": "John Doe"
                        }
                        """)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().jsonPath("name").isEqualTo("John Doe");

        webTestClient
                .get()
                .uri("/api/employees")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EmployeeResourceDto.class).hasSize(1);

    }
}
