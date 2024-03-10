package br.unitins.topicos2.autopecas.dto;

import br.unitins.topicos2.autopecas.model.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        @NotBlank(message = "O nome deve ser informado.") @Size(max = 60, message = "O campo nome deve possuir no máximo 60 caracteres.") String nome,

        @NotBlank(message = "O email deve ser informado.") @Size(min = 2, max = 60, message = "O campo email deve possuir no máximo 60 caracteres.") String email,

        String cpf,

        @NotBlank(message = "O email deve ser informado.") @Size(min = 2, max = 60, message = "O campo senha deve possuir no máximo 60 caracteres.") String senha,
        Integer idPerfil) {

}
