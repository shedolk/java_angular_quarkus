package br.unitins.topicos2.autopecas.service;

import java.util.List;

import br.unitins.topicos2.autopecas.dto.UsuarioDTO;
import br.unitins.topicos2.autopecas.dto.UsuarioResponseDTO;
import br.unitins.topicos2.autopecas.dto.UsuarioResponseDTO2;
import jakarta.validation.Valid;

public interface UsuarioService {

    // recursos basicos
    List<UsuarioResponseDTO> getAll();

    UsuarioResponseDTO2 findById(Long id);

    UsuarioResponseDTO2 create(@Valid UsuarioDTO dto);

    UsuarioResponseDTO2 update(Long id, UsuarioDTO dto);

    void delete(Long id);

    // recursos extras

    List<UsuarioResponseDTO> findByNome(String nome);

    long count();

    long countByNome(String nome);
}
