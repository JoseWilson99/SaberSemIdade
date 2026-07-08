package com.sabersemidade.backend.controller;

import com.sabersemidade.backend.model.Matricula;
import com.sabersemidade.backend.repository.CursoRepository;
import com.sabersemidade.backend.repository.MatriculaRepository;
import com.sabersemidade.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin(origins = "http://localhost:5173")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Matricula> listarMatriculas(){
        return matriculaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Integer id){
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        return matricula.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Matricula>> buscarPorUsuario(@PathVariable Integer usuarioId){
        List<Matricula> matriculas = matriculaRepository.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(matriculas);
    }

    @PostMapping
    public ResponseEntity<?> criarMatricula(@RequestBody Matricula matricula){
        try {
            if(matricula.getUsuario() != null){
                Integer usuarioId = matricula.getUsuario().getId();
                matricula.setUsuario(usuarioRepository.findById(usuarioId).orElse(null));
            }

            if(matricula.getCurso() != null){
                Integer cursoId = matricula.getCurso().getId();
                matricula.setCurso(cursoRepository.findById(cursoId).orElse(null));
            }

            matricula.setDataMatricula(new Timestamp(System.currentTimeMillis()));
            Matricula salva = matriculaRepository.save(matricula);
            return ResponseEntity.ok(salva);
        } catch(Exception erro){
            return ResponseEntity.status(500).body("Erro ao criar matrícula: " + erro.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizarMatricula(@PathVariable Integer id, @RequestBody Matricula dados){
        Optional<Matricula> resultado = matriculaRepository.findById(id);
        if(resultado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Matricula matricula = resultado.get();
        matricula.setStatus(dados.getStatus());
        return ResponseEntity.ok(matriculaRepository.save(matricula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable Integer id){
        if(!matriculaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        matriculaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}