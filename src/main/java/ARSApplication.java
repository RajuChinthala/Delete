import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.igate.*")
public class ARSApplication {

    public static void main(String[] args) {
        SpringApplication.run(ARSApplication.class, args);
    }
}
