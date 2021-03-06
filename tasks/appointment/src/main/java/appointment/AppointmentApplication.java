package appointment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.management.MXBean;

@SpringBootApplication
public class AppointmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
