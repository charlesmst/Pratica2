package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TabelaValores generated by hbm2java
 */
@Entity
@Table(name="tabela_valores"
    ,schema="public"
)
public class TabelaValores  implements java.io.Serializable {


     private int id;
     @JsonIgnore
     private Tabela tabela;
     private BigDecimal valorInicial;
     private BigDecimal valorFinal;
     private BigDecimal aliquota;
     private BigDecimal desconto;

    public TabelaValores() {
    }

	
    public TabelaValores(int id, Tabela tabela, BigDecimal valorInicial) {
        this.id = id;
        this.tabela = tabela;
        this.valorInicial = valorInicial;
    }
    public TabelaValores(int id, Tabela tabela, BigDecimal valorInicial, BigDecimal valorFinal, BigDecimal aliquota, BigDecimal desconto) {
       this.id = id;
       this.tabela = tabela;
       this.valorInicial = valorInicial;
       this.valorFinal = valorFinal;
       this.aliquota = aliquota;
       this.desconto = desconto;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tabela_id", nullable=false)
    public Tabela getTabela() {
        return this.tabela;
    }
    
    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    
    @Column(name="valor_inicial", nullable=false, precision=10)
    public BigDecimal getValorInicial() {
        return this.valorInicial;
    }
    
    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    
    @Column(name="valor_final", precision=10)
    public BigDecimal getValorFinal() {
        return this.valorFinal;
    }
    
    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    
    @Column(name="aliquota", precision=10)
    public BigDecimal getAliquota() {
        return this.aliquota;
    }
    
    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    
    @Column(name="desconto", precision=10)
    public BigDecimal getDesconto() {
        return this.desconto;
    }
    
    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }




}


