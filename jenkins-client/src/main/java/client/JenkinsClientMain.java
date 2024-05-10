package client;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;

import java.util.List;
import java.util.Map;

public class JenkinsClientMain {

    public static void main(String[] args) {
        JenkinsClient client = JenkinsClient.builder()
                .endPoint("http://3.120.146.110:8080") // Optional. Defaults to http://127.0.0.1:8080
                .credentials("jenkins:116b511e49ae01303fa6964963803c3e35") // Optional.
                .build();

        SystemInfo systemInfo = client.api().systemApi().systemInfo();
        System.out.println(systemInfo.jenkinsVersion());

        var api = client.api().jobsApi();

        var jobList = api.jobList("");
        for (var job: jobList.jobs()) {
            System.out.println(job.name());
        }

//        api.build("", "demo");
        api.buildWithParameters("", "demo", Map.of("client_name", List.of("Jack Doe")));

    }
}
