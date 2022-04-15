package vinicanalles.gesep.com.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSolicitacao;

    @Column(nullable = false)
    private Long idCidadao;

    @Column(nullable = false)
    private Long idServicoPublico;

    @Column(nullable = false)
    private Long idEquipeCampo;

    @Column(nullable = false)
    private Date dataAbertura;

    @Column(nullable = false)
    private Date dataEncerramento;
}
