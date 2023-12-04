package ifsp.doo.atas.domain.DTO.informe;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record InformeGetResponseDTO(Long id, String informe, PessoaGetResponseDTO pessoa, Boolean informado) {
    public InformeGetResponseDTO(InformeGetPersistDTO informe) {
        this(informe.getId(), informe.getInforme(), new PessoaGetResponseDTO(informe.getPessoa()), informe.getInformado());
    }
}
