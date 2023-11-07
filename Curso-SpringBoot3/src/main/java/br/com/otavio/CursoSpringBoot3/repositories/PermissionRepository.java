package br.com.otavio.CursoSpringBoot3.repositories;

import br.com.otavio.CursoSpringBoot3.model.Permission;
import br.com.otavio.CursoSpringBoot3.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
