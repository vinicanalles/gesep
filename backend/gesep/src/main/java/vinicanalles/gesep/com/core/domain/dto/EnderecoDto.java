package vinicanalles.gesep.com.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoDto {

    @JsonProperty("rua")
    private String rua;

    @JsonProperty("numero")
    private Integer numero;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("pais")
    private String pais;
}
