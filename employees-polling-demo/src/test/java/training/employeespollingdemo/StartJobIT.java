package training.employeespollingdemo;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

// NEM EZZEL! import static io.restassured.RestAssured.with;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class StartJobIT {

    @Autowired
    MockMvc mockMvc;


    @BeforeEach
    void init() {
        RestAssuredMockMvc.
                mockMvc(mockMvc);
//        RestAssuredMockMvc.requestSpecification =
//                given()
//                        .contentType(ContentType.JSON)
//                        .accept(ContentType.JSON);
    }


    @Test
    void startJob() throws Exception {
        log.info("Start job test");

        mockMvc.perform(get("/jobs")).andExpect(status().isOk());

        var job = with()
                .post("/jobs")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .response()
                .as(Job.class)
        ;
        System.out.println(job.getId());

        await().untilAsserted(() -> assertThat(
                with()
                        .get("/jobs")
                        .then()
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", Job.class))
                .filteredOn(j -> j.getId().equals(job.getId()))
                .extracting(Job::getJobStatus)
                .containsExactly(Job.JobStatus.COMPLETED)
        );
    }
}
