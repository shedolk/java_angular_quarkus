package br.unitins.topicos2.autopecas.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.autopecas.dto.UsuarioDTO;
import br.unitins.topicos2.autopecas.dto.UsuarioResponseDTO;
import br.unitins.topicos2.autopecas.dto.UsuarioResponseDTO2;
import br.unitins.topicos2.autopecas.model.Perfil;
import br.unitins.topicos2.autopecas.model.Usuario;
import br.unitins.topicos2.autopecas.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    Validator validator;

    @Override
    public List<UsuarioResponseDTO> getAll() {

        List<Usuario> list = usuarioRepository.findAll().list();
        return list.stream().map(e -> UsuarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO2 findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null)
            throw new NotFoundException("Usuario não encontrado.");
        return UsuarioResponseDTO2.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO2 create(@Valid UsuarioDTO usuarioDTO) throws ConstraintViolationException {
        // validar(usuarioDTO);

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setCpf(usuarioDTO.cpf());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setPerfil(Perfil.valueOf(usuarioDTO.idPerfil()));

        usuarioRepository.persist(usuario);

        return UsuarioResponseDTO2.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO2 update(Long id, UsuarioDTO usuarioDTO) throws ConstraintViolationException {
        validar(usuarioDTO);

        Usuario usuario = usuarioRepository.findById(id);

        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setCpf(usuarioDTO.cpf());
        usuario.setSenha(usuarioDTO.senha());

        return UsuarioResponseDTO2.valueOf(usuario);
    }

    private void validar(UsuarioDTO estadoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(estadoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        List<Usuario> list = usuarioRepository.findByNome(nome).list();
        return list.stream().map(e -> UsuarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return usuarioRepository.findByNome(nome).count();
    }
}
