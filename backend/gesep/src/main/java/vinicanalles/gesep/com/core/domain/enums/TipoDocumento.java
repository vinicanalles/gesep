package vinicanalles.gesep.com.core.domain.enums;

public enum TipoDocumento {
    IPTU("iptu"),
    ITR("itr");

    private String descricao;

    TipoDocumento(String descricao){
        this.descricao = descricao;
    }

    public String getTipoDocumento() {
        return this.descricao;
    }
}
