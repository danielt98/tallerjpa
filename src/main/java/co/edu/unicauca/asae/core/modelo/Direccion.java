package co.edu.unicauca.asae.core.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDireccion;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="codigo")
    private Estudiante objEstudiante;
    @Column(nullable = false, length = 50)
    private String direccionResidencia;
    @Column(nullable = false, length = 50)
    private String ciudad;
    @Column(nullable =  false, length = 50)
    private String pais;

    public void setidDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    public Integer getidDireccion() {
        return idDireccion;
    }

    public void setobjEstudiante(Estudiante objEstudiante) {
        this.objEstudiante = objEstudiante;
    }

    public Estudiante getobjEstudiante() {
        return objEstudiante;
    }
    
    public void setdireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getdireccionResidencia() {
        return direccionResidencia;
    }

    public void setciudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getciudad() {
        return ciudad;
    }

    public void setpais(String pais) {
        this.pais = pais;
    }

    public String getpais() {
        return pais;
    }
}
