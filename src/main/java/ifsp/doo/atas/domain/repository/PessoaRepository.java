package ifsp.doo.atas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Email;

public interface PessoaRepository extends JpaRepository<PessoaGetPersistDTO, Long> {
    public boolean existsByEmail(Email email);
}

/*
 * cnages:
 * - nome não é uma chame primaria, então email, como uma chave unique
 *     é de melhor uso pra verificar se existe uma entidade já cadastrada
 */