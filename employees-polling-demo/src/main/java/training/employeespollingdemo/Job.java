package training.employeespollingdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    public enum JobStatus {STARTED, COMPLETED}

    private String id;

    private JobStatus jobStatus;
}
