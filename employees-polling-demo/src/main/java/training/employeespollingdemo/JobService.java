package training.employeespollingdemo;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class JobService {

    private final List<Job> jobs = Collections.synchronizedList(new ArrayList<>());

    private final Random random = new Random();

    @Async
    @SneakyThrows
    public void startJob(Job job) {
        jobs.add(job);
        Thread.sleep(random.nextInt(1,3) * 1000);
        job.setJobStatus(Job.JobStatus.COMPLETED);
    }

    public List<Job> listJobs() {
        return jobs;
    }
}
