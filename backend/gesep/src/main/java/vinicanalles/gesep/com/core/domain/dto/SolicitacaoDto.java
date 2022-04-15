package vinicanalles.gesep.com.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolicitacaoDto {

    @JsonProperty("id_cidadao")
    private Long idCidadao;

    @JsonProperty("id_servico_publicos")
    private Long idServicoPublico;

    @JsonProperty("id_equipe_campo")
    private Long idEquipeCampo;

    @JsonProperty("data_abertura")
    private Date dataAbertura;

    @JsonProperty("data_encerramento")
    private Date dataEncerramento;
}
