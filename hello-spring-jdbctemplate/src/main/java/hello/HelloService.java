package hello;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class HelloService {

    @PostConstruct
    public void logBeanHasStarted() {
        log.info("HelloService has started");
    }

    public String sayHello() {
        return "Hello %s".formatted(LocalDateTime.now());
    }
}
