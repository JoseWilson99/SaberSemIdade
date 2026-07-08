package com.sabersemidade.backend.controller;

import com.sabersemidade.backend.dto.LoginRequest;
import com.sabersemidade.backend.model.Usuario;
import com.sabersemidade.backend.model.Matricula;
import com.sabersemidade.backend.repository.UsuarioRepository;
import com.sabersemidade.backend.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        usuario.setDataCadastro(new Timestamp(System.currentTimeMillis()));
        return usuarioRepository.save(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha());
        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(401).body("E-mail ou senha inválidos.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            Usuario usuarioAtual = usuario.get();
            usuarioAtual.setNome(usuarioDetails.getNome());
            usuarioAtual.setEmail(usuarioDetails.getEmail());
            usuarioAtual.setSenha(usuarioDetails.getSenha());
            return ResponseEntity.ok(usuarioRepository.save(usuarioAtual));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<Matricula> matriculas = matriculaRepository.findByUsuarioId(id);
        matriculaRepository.deleteAll(matriculas);

        usuarioRepository.delete(usuario.get());
        return ResponseEntity.ok("Usuário excluído com sucesso.");
    }
}