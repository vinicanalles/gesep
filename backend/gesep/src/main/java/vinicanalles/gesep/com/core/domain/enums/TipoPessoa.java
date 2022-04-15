package vinicanalles.gesep.com.core.domain.enums;

public enum TipoPessoa {
    CENTRAL_ATENDIMENTO(1),
    EQUIPE_CAMPO(2),
    ORGAO_MUNICIPAL(3),
    CIDADAO(4);

    private Integer codigo;

    TipoPessoa(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return this.codigo;
    }
}
