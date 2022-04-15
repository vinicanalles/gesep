package vinicanalles.gesep.com.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vinicanalles.gesep.com.core.domain.entities.Endereco;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServicoPublicoDto {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("endereco")
    private Long endereco;
}
