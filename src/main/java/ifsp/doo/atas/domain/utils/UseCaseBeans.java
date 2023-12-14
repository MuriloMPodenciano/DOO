package ifsp.doo.atas.domain.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ifsp.doo.atas.domain.usecases.ata.BuscarAtaUseCase;
import ifsp.doo.atas.domain.usecases.ata.CadastrarAtaUseCase;
import ifsp.doo.atas.domain.usecases.ata.EditarAtaUseCase;
import ifsp.doo.atas.domain.usecases.ata.EditarDiscussaoUseCase;
import ifsp.doo.atas.domain.usecases.ata.EditarInformeUseCase;
import ifsp.doo.atas.domain.usecases.ata.EditarPautaUseCase;
import ifsp.doo.atas.domain.usecases.grupo.BuscarGrupoUseCase;
import ifsp.doo.atas.domain.usecases.grupo.CadastrarGrupoUseCase;
import ifsp.doo.atas.domain.usecases.grupo.EditarGrupoUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.BuscarPessoaUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.CadastrarPessoaUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.EditarPessoaUseCase;
import ifsp.doo.atas.domain.usecases.usuario.CadastrarUsuarioUseCase;

@Configuration
public class UseCaseBeans {
    @Bean
    public BuscarAtaUseCase buscarAtaUseCase() {
        return new BuscarAtaUseCase();
    }

    @Bean
    public CadastrarAtaUseCase cadastrarAtaUseCase() {
        return new CadastrarAtaUseCase();
    }

    @Bean
    public EditarAtaUseCase editarAtaUseCase() {
        return new EditarAtaUseCase();
    }

    @Bean
    public EditarDiscussaoUseCase editarDiscussaoUseCase() {
        return new EditarDiscussaoUseCase();
    }

    @Bean
    public EditarInformeUseCase editarInformeUseCase() {
        return new EditarInformeUseCase();
    }

    @Bean
    public EditarPautaUseCase editarPautaUseCase() {
        return new EditarPautaUseCase();
    }

    @Bean
    public BuscarGrupoUseCase buscarGrupoUseCase() {
        return new BuscarGrupoUseCase();
    }

    @Bean
    public CadastrarGrupoUseCase cadastrarGrupoUseCase() {
        return new CadastrarGrupoUseCase();
    }

    @Bean
    public EditarGrupoUseCase editarGrupoUseCase() {
        return new EditarGrupoUseCase();
    }

    @Bean
    public BuscarPessoaUseCase buscarPessoaUseCase() {
        return new BuscarPessoaUseCase();
    }

    @Bean
    public CadastrarPessoaUseCase cadastrarPessoaUseCase() {
        return new CadastrarPessoaUseCase();
    }

    @Bean
    public EditarPessoaUseCase editarPessoaUseCase() {
        return new EditarPessoaUseCase();
    }

    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase() {
        return new CadastrarUsuarioUseCase();
    }
}
