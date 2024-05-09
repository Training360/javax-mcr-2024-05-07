package training.configdemo;

import lombok.Data;

@Data
public class Environment {

    private String name;

    private String url;

    private String databaseUrl;

    private String username;

    private String password;
}
