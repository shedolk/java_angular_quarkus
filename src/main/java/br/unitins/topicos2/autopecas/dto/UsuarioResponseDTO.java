package br.unitins.topicos2.autopecas.dto;

import br.unitins.topicos2.autopecas.model.Perfil;
import br.unitins.topicos2.autopecas.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        Perfil perfil) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getPerfil());
    }
}
