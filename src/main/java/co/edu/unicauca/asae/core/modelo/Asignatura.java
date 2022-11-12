package co.edu.unicauca.asae.core.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Asignaturas")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsignatura;
    @Column(unique = true, nullable = false, length = 50)
    private String codigo;
    @Column(nullable = false, length = 50)
    private String nombre;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idAsignatura")
    private List<Curso> cursos;

    public Asignatura() {
        this.cursos = new ArrayList<Curso>();
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCurso(Curso cursos) {
        this.cursos.add(cursos);
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
