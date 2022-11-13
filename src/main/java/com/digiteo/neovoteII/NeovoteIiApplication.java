package com.digiteo.neovoteII;

import com.digiteo.neovoteII.model.Admin;
import com.digiteo.neovoteII.model.GenderValues;
import com.digiteo.neovoteII.model.Voter;
import com.digiteo.neovoteII.repository.AdminRepository;
import com.digiteo.neovoteII.repository.VoterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/*
*
*  @fpr95
*
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NeovoteIiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeovoteIiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AdminRepository adminRepository, VoterRepository voterRepository) {
		return args -> {
			List<Voter> voters = new ArrayList<Voter>();

			Voter marcelo = new Voter("Marcelo",
					"Nuñez",
					"marcelodelcolo",
					"_qwerty696",
					GenderValues.MALE,
					"11564895-5",
					"+569 63161827",
					"marcelo.nunhes@protonmail.com");

			Voter maria = new Voter("Maria",
					"Gomez",
					"mariagomez",
					"maria1234*",
					GenderValues.FEMALE,
					"15699636K",
					"+569 57889476",
					"maria.gomez@hotmail.com");

			voters.add(marcelo);
			voters.add(maria);

			voterRepository.saveAll(voters);

			Admin admin = new Admin("Admin",
					"Istrador",
					"admin",
					"Admin_2022_",
					"18.52.963-0",
					"+569 85668956",
					"admin_pulento@gmail.com");

			adminRepository.save(admin);
		};
	}

}
