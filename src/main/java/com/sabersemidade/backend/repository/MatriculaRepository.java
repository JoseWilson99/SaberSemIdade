package com.sabersemidade.backend.repository;

import com.sabersemidade.backend.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    List<Matricula> findByUsuarioId(Integer usuarioId);
    boolean existsByUsuarioIdAndCursoId(Integer usuarioId, Integer cursoId);
}