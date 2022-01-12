package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Path;

@Testcontainers
@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
class CourseCRUDTest {
    @Container
    static DockerComposeContainer<?> compose = new DockerComposeContainer<>(
            Path.of("docker-compose.yml").toFile()
    );
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldPerformCRUD() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/courses/getAll")
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                //language=JSON
                                """
                                        {
                                          "courses": [
                                            {
                                              "id": 1,
                                              "name": "Java developer",
                                              "price": 20000,
                                              "image": "037646e7-1b56-4df9-8fe3-6c0bd9937cf0.jpg",
                                              "numberseats": 10
                                            },
                                            {
                                              "id": 2,
                                              "name": "Phyton developer",
                                              "price": 15000,
                                              "image": "74392782-9a11-4fe5-a433-abaf7b06dfe6.jpg",
                                              "numberseats": 5
                                            },
                                            {
                                              "id": 3,
                                              "name": "QA engineer",
                                              "price": 10000,
                                              "image": "22a80fdc-f693-475a-9e2d-9988947ace49.jpg",
                                              "numberseats": 15
                                            },
                                            {
                                              "id": 4,
                                              "name": "JavaScript",
                                              "price": 10000,
                                              "image": "402fb0b7-db1d-4ced-936b-80b7fd9b9a9e.jpg",
                                              "numberseats": 10
                                            },
                                            {
                                              "id": 5,
                                              "name": "Android developer",
                                              "price": 12000,
                                              "image": "noimage.jpg",
                                              "numberseats": 8
                                            }
                                          ]
                                        }
                                        """
                        )
                );
        mockMvc.perform(
                MockMvcRequestBuilders.get("courses/getById")
                        .param("id",String.valueOf(1))
        )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                         //language=JSON
                                """
                                        {
                                          "course": {
                                            "id": 1,
                                            "name": "Java developer",
                                            "price": 20000,
                                            "image": "037646e7-1b56-4df9-8fe3-6c0bd9937cf0.jpg",
                                            "numberseats": 10
                                          }
                                        }
                                        """
                        )
                );
    }
}
