package ibh.accounting.pettycash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("ibh.accounting.pettycash.model")
@EnableJpaRepositories("ibh.accounting.pettycash.repositories")
@SpringBootApplication
public class pettyCashApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(pettyCashApp.class, args);
    }

}
