package training.employeespollingdemo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/jobs")
@Slf4j
public class JobController {

    private JobService jobService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Job startJob() {
        log.info("Start job");
        var id = UUID.randomUUID().toString();
        var job = new Job(id, Job.JobStatus.STARTED);
        jobService.startJob(job);
        return job;
    }

    @GetMapping
    public List<Job> listJobs() {
        log.info("List jobs");
        return jobService.listJobs();
    }
}
