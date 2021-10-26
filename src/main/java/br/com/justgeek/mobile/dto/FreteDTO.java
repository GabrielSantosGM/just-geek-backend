package br.com.justgeek.mobile.dto;

public class FreteDTO {

    private String servico;
    private int prazoParaEntrega;
    private Double valorAPagar;
    private Boolean entregaDomiciliar;
    private Boolean entregaSabado;

    private FreteDTO() {
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public int getPrazoParaEntrega() {
        return prazoParaEntrega;
    }

    public void setPrazoParaEntrega(int prazoParaEntrega) {
        this.prazoParaEntrega = prazoParaEntrega;
    }

    public Double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(Double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public Boolean getEntregaDomiciliar() {
        return entregaDomiciliar;
    }

    public void setEntregaDomiciliar(Boolean entregaDomiciliar) {
        this.entregaDomiciliar = entregaDomiciliar;
    }

    public Boolean getEntregaSabado() {
        return entregaSabado;
    }

    public void setEntregaSabado(Boolean entregaSabado) {
        this.entregaSabado = entregaSabado;
    }


    public static final class Builder {
        private String servico;
        private int prazoParaEntrega;
        private Double valorAPagar;
        private Boolean entregaDomiciliar;
        private Boolean entregaSabado;

        private Builder() {
        }

        public static Builder aFreteDTO() {
            return new Builder();
        }

        public Builder withServico(String servico) {
            this.servico = servico;
            return this;
        }

        public Builder withPrazoParaEntrega(int prazoParaEntrega) {
            this.prazoParaEntrega = prazoParaEntrega;
            return this;
        }

        public Builder withValorAPagar(Double valorAPagar) {
            this.valorAPagar = valorAPagar;
            return this;
        }

        public Builder withEntregaDomiciliar(Boolean entregaDomiciliar) {
            this.entregaDomiciliar = entregaDomiciliar;
            return this;
        }

        public Builder withEntregaSabado(Boolean entregaSabado) {
            this.entregaSabado = entregaSabado;
            return this;
        }

        public FreteDTO build() {
            FreteDTO freteDTO = new FreteDTO();
            freteDTO.setServico(servico);
            freteDTO.setPrazoParaEntrega(prazoParaEntrega);
            freteDTO.setValorAPagar(valorAPagar);
            freteDTO.setEntregaDomiciliar(entregaDomiciliar);
            freteDTO.setEntregaSabado(entregaSabado);
            return freteDTO;
        }
    }
}
