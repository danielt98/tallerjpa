package co.edu.unicauca.asae.core.modelo;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAsignatura", nullable = false)
    private Asignatura idAsignatura;
    @Column(nullable = false, length = 20)
    private String periodo;
    @Column(nullable = false, length = 50)
    private String grupo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="estudiantes_cursos", joinColumns = @JoinColumn(name = "idCurso"), inverseJoinColumns = @JoinColumn(name = "codigo"))
    private List<Estudiante> estudiantes;

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Estudiante estudiante) {
       this.estudiantes.add(estudiante);
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getGrupo() {
        return grupo;
    }
}
