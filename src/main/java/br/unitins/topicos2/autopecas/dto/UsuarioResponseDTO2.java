package br.unitins.topicos2.autopecas.dto;

import br.unitins.topicos2.autopecas.model.Perfil;
import br.unitins.topicos2.autopecas.model.Usuario;

public record UsuarioResponseDTO2(
        Long id,
        String nome,
        String email,
        String cpf,
        String senha,
        Perfil perfil) {

    public static UsuarioResponseDTO2 valueOf(Usuario usuario) {
        return new UsuarioResponseDTO2(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getSenha(),
                usuario.getPerfil());
    }
}
