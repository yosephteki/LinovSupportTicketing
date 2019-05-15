package LinovSupport.Ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages= {"LinovSupport.Ticketing.dao","LinovSupport.Ticketing.service","LinovSupport.Ticketing.controller"})
@EnableTransactionManagement
@EntityScan(basePackages = "LinovSupport.Ticketing.model")
public class App 
{
    public static void main( String[] args )
    {
     SpringApplication.run(App.class,args);   
    }
}
