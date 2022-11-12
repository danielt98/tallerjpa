package co.edu.unicauca.asae.core.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column(nullable = false, length = 50)
    private String tipoIdentificacion;
    @Column(nullable = false, length = 50)
    private String numeroIdentificacion;
    @Column(nullable = false, length = 50)
    private String nombres;
    @Column(nullable = true, length = 50)
    private String apellidos;

    @OneToOne(mappedBy = "objEstudiante", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Direccion objDireccion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="estudiantes_cursos", joinColumns = @JoinColumn(name = "codigo"), inverseJoinColumns = @JoinColumn(name = "idcurso"))
    private List<Curso> cursos;

    public Estudiante() {
        this.cursos = new ArrayList<>();
    }

    public Direccion getObjDireccion() {
        return objDireccion;
    }

    public void setObjDireccion(Direccion objDireccion) {
        this.objDireccion = objDireccion;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }
    
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }
    
}
