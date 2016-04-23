package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * FuncionarioFaixa generated by hbm2java
 */
@Entity
@Table(name = "funcionario_faixa", schema = "public"
)
public class FuncionarioFaixa implements java.io.Serializable {

    private int id;
    private CargoNivel cargoNivel;
    private FaixaSalarial faixaSalarial;
    private FuncionarioCargo funcionarioCargo;
    private Date dataInicio;

    public FuncionarioFaixa() {
    }

    public FuncionarioFaixa(int id, CargoNivel cargoNivel, FaixaSalarial faixaSalarial, FuncionarioCargo funcionarioCargo, Date dataInicio) {
        this.id = id;
        this.cargoNivel = cargoNivel;
        this.faixaSalarial = faixaSalarial;
        this.funcionarioCargo = funcionarioCargo;
        this.dataInicio = dataInicio;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_nivel_id", nullable = false)
    public CargoNivel getCargoNivel() {
        return this.cargoNivel;
    }

    public void setCargoNivel(CargoNivel cargoNivel) {
        this.cargoNivel = cargoNivel;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faixa_salarial_id", nullable = false)
    public FaixaSalarial getFaixaSalarial() {
        return this.faixaSalarial;
    }

    public void setFaixaSalarial(FaixaSalarial faixaSalarial) {
        this.faixaSalarial = faixaSalarial;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_cargo_id", nullable = false)
    public FuncionarioCargo getFuncionarioCargo() {
        return this.funcionarioCargo;
    }

    public void setFuncionarioCargo(FuncionarioCargo funcionarioCargo) {
        this.funcionarioCargo = funcionarioCargo;
    }

    @Column(name = "data_inicio", nullable = false)
    @Type(type = "date")

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

}
