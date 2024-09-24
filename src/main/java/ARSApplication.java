import com.igate.airline.bean.BookingInformation;
import com.igate.airline.bean.FlightInformation;
import com.igate.airline.bean.Locations;
import com.igate.airline.bean.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.igate.*")
public class ARSApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ARSApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ARSApplication.class, args);
    }

}
