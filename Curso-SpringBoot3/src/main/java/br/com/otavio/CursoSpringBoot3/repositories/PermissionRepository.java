package br.com.otavio.CursoSpringBoot3.repositories;

import br.com.otavio.CursoSpringBoot3.model.Permission;
import br.com.otavio.CursoSpringBoot3.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
