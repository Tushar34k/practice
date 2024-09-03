package com.qsp.employee.roleseder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qsp.employee.enums.RoleType;
import com.qsp.employee.model.Role;
import com.qsp.employee.repo.RoleRepository;

@Configuration
public class RoleSeeder {

    @Bean
    public CommandLineRunner seedRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role adminRole = new Role();
                adminRole.setName(RoleType.ADMIN);

                Role hrRole = new Role();
                hrRole.setName(RoleType.HR);

                Role employeeRole = new Role();
                employeeRole.setName(RoleType.EMPLOYEE);

                roleRepository.save(adminRole);
                roleRepository.save(hrRole);
                roleRepository.save(employeeRole);

                System.out.println("Roles seeded: ADMIN, HR, EMPLOYEE");
            } else {
                System.out.println("Roles already present");
            }
        };
    }
}
