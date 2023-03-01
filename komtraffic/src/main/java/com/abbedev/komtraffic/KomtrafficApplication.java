package com.abbedev.komtraffic;

import com.abbedev.komtraffic.model.BusRoute;
import com.abbedev.komtraffic.repository.BusRouteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KomtrafficApplication {

    public static void main(String[] args) {
        SpringApplication.run(KomtrafficApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(BusRouteRepository busRouteRepository) {
        return args -> {
            BusRoute orebro = new BusRoute();
            orebro.setName("orebro");
            orebro.setDistance(5f);
            orebro.setDuration(2.5f);
            orebro.setDelay("");
            busRouteRepository.save(orebro);
            BusRoute stockholm = new BusRoute();
            stockholm.setName("stockholm");
            stockholm.setDistance(10f);
            stockholm.setDuration(5f);
            stockholm.setDelay("");
            busRouteRepository.save(stockholm);
            BusRoute karlstad = new BusRoute();
            karlstad.setName("karlstad");
            karlstad.setDistance(6f);
            karlstad.setDuration(3f);
            karlstad.setDelay("");
            busRouteRepository.save(karlstad);
            BusRoute malmo = new BusRoute();
            malmo.setName("malmo");
            malmo.setDistance(15f);
            malmo.setDuration(7.5f);
            malmo.setDelay("");
            busRouteRepository.save(malmo);
            BusRoute ostersund = new BusRoute();
            ostersund.setName("ostersund");
            ostersund.setDistance(22f);
            ostersund.setDuration(11f);
            ostersund.setDelay("");
            busRouteRepository.save(ostersund);


        };

    }
}
