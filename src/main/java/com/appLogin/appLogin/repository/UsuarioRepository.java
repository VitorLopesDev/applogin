package com.appLogin.appLogin.repository;

import com.appLogin.appLogin.entity.Usuario;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuario WHERE email = :email AND senha = :senha", nativeQuery = true)
    Usuario login(@Param("email") String email, @Param("senha") String senha);

    Usuario findByEmail(@NotEmpty String email);
}

