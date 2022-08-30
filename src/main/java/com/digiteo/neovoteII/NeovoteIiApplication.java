package com.digiteo.neovoteII;

import com.digiteo.neovoteII.model.Admin;
import com.digiteo.neovoteII.model.Voter;
import com.digiteo.neovoteII.repository.AdminRepository;
import com.digiteo.neovoteII.repository.VoterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
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
					"qwerty",
					"+569 63161827",
					"marcelo.nunhes@protonmail.com");

			Voter maria = new Voter("Maria",
					"Gomez",
					"12345678",
					"+569 57889476",
					"maria.gomez@hotmail.com");

			voters.add(marcelo);
			voters.add(maria);

			voterRepository.saveAll(voters);

			Admin admin = new Admin("Admin",
					"Istrador",
					"qwertyadmin2022",
					"+569 85668956",
					"admin_pulento@gmail.com");

			adminRepository.save(admin);
		};
	}

}
