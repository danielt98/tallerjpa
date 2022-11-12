package co.edu.unicauca.asae.core;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.unicauca.asae.core.modelo.Asignatura;
import co.edu.unicauca.asae.core.modelo.Curso;
import co.edu.unicauca.asae.core.modelo.Direccion;
import co.edu.unicauca.asae.core.modelo.Estudiante;
import co.edu.unicauca.asae.core.repositories.AsignaturaRepository;
import co.edu.unicauca.asae.core.repositories.CursosRepository;
import co.edu.unicauca.asae.core.repositories.DireccionesRepository;
import co.edu.unicauca.asae.core.repositories.EstudiantesRepository;

@SpringBootApplication
@Transactional
public class TallerJpaDanielApplication implements CommandLineRunner {

	@Autowired
	private EstudiantesRepository servicioEstudiantes;

	@Autowired
	private DireccionesRepository servicioDirecciones;

	@Autowired
	private CursosRepository servicioCursos;

	@Autowired
	private AsignaturaRepository servicioAsignaturas;

	public static void main(String[] args) {
		SpringApplication.run(TallerJpaDanielApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// consultarEstudiantes();
		// guardarEstudiante();
		consultarCursosAsignaturaEstudiantes();
		// almacenarAsignaturas();
		// buscarEstudianteCursos();
		// matricularEstudiante();
		// nuevoGuardarEstudiante();
	}

	private void consultarEstudiantes() {
		Iterable<Estudiante> listaEstudiantes = this.servicioEstudiantes.findAll();

		for (Estudiante estudiante : listaEstudiantes) {
			System.out.println("Estudiantes");
			System.out.println("Nombres: "+ estudiante.getNombres());
			System.out.println("Apellidos: "+ estudiante.getApellidos());
			System.out.println("Tipo id: "+ estudiante.getTipoIdentificacion());
			System.out.println("Numero Id: "+ estudiante.getNumeroIdentificacion());
			System.out.println("--- ---- ----");
		}
	}

	private void guardarEstudiante() {
		Estudiante estudiante = new Estudiante();
		estudiante.setNombres("Carlos");
		estudiante.setApellidos("Mora");
		estudiante.setTipoIdentificacion("CC");
		estudiante.setNumeroIdentificacion("34123412");

		Estudiante objEstudianteAlmacenado = this.servicioEstudiantes.save(estudiante);

		System.out.println("Id generado para el estudiante: "+ objEstudianteAlmacenado.getCodigo());

		Direccion direccion = new Direccion();
		direccion.setciudad("Popayán");
		direccion.setdireccionResidencia("cr: 20 #12-31");
		direccion.setpais("Colombia");

		direccion.setobjEstudiante(objEstudianteAlmacenado);
		Direccion objDireccionAlmacenada = this.servicioDirecciones.save(direccion);
		System.out.println("Id almacenado: " + objDireccionAlmacenada.getidDireccion());
	}

	private void nuevoGuardarEstudiante() {
		Estudiante estudiante = new Estudiante();
		estudiante.setNombres("Andres");
		estudiante.setApellidos("Castillo");
		estudiante.setTipoIdentificacion("CC");
		estudiante.setNumeroIdentificacion("334234423");

		Direccion direccion = new Direccion();
		direccion.setciudad("Popayán");
		direccion.setdireccionResidencia("cr: 80 #16-42");
		direccion.setpais("Colombia");
		direccion.setobjEstudiante(estudiante);

		estudiante.setObjDireccion(direccion);

		Estudiante objEstudianteAlmacenado = this.servicioEstudiantes.save(estudiante);

		System.out.println("Id generado para el estudiante: "+ objEstudianteAlmacenado.getCodigo());
	}

	private void consultarCursosAsignaturaEstudiantes() {
		Iterable<Asignatura> listaAsignaturas = this.servicioAsignaturas.findAll();

		for (Asignatura asignatura : listaAsignaturas) {
			System.out.println("Asignatura");
			System.out.println("*** *** *** ***");
			System.out.println("Codigo: " + asignatura.getCodigo());
			System.out.println("Nombre: " + asignatura.getNombre());
			// System.out.println("Id asignatura: " + asignatura.getIdAsignatura());
			Iterable<Curso> listaCursos = asignatura.getCursos();
			for (Curso curso : listaCursos) {
				System.out.println("Curso");
				System.out.println("Periodo: " + curso.getPeriodo());
				System.out.println("Grupo: " + curso.getGrupo());
				Iterable<Estudiante> listaEstudiantes =  curso.getEstudiantes();
				for (Estudiante estudiante : listaEstudiantes) {
					System.out.println("Nombres: "+ estudiante.getNombres());
					System.out.println("Apellidos: "+ estudiante.getApellidos());
					System.out.println("Dirección: "+ estudiante.getObjDireccion().getdireccionResidencia());
				}
			}
			System.out.println("--- ---- ---- ----");
		}
	}

	private void almacenarAsignaturas() {
		Asignatura objAsignatura = new Asignatura();
		objAsignatura.setNombre("Algebra lineal");
		objAsignatura.setCodigo("AZ5");

		this.servicioAsignaturas.save(objAsignatura);

		Curso objCurso = new Curso();
		objCurso.setPeriodo("2023-1");
		objCurso.setGrupo("A");
		objCurso.setIdAsignatura(objAsignatura);
		this.servicioCursos.save(objCurso);

		Curso objCurso2 = new Curso();
		objCurso2.setPeriodo("2023-1");
		objCurso2.setGrupo("B");
		objCurso2.setIdAsignatura(objAsignatura);
		this.servicioCursos.save(objCurso2);
	}

	public void buscarEstudianteCursos() {
		Optional<Estudiante> optional = this.servicioEstudiantes.findById(1);
		if (optional.isPresent()) {
			Estudiante es = optional.get();
			System.out.println("Estudiante: " + es.getNombres());
			System.out.println("Cursos matriculados");
			for (Curso curso : es.getCursos()) {
				System.out.println("Periodo: " + curso.getPeriodo());
				System.out.println("Grupo: " + curso.getGrupo());
				System.out.println("Asignatura: " + curso.getIdAsignatura().getNombre());
			}
		}
		else {
			System.out.println("Estudiante no encontrado");
		}
	}

	public void matricularEstudiante() {
		Optional<Estudiante> optional = this.servicioEstudiantes.findById(1);
		if (optional.isPresent()) {
			Estudiante es = optional.get();
			System.out.println("Estudiante: " + es.getNombres());
			Optional<Curso> optional2 = this.servicioCursos.findById(4);
			if (optional2.isPresent()) {
				Curso curso = optional2.get();
				System.out.println("Grupo: " + curso.getGrupo());
				System.out.println("Asignatura: " + curso.getIdAsignatura().getNombre());
				es.setCurso(curso);
				this.servicioEstudiantes.save(es);
			}
			else {
				System.out.println("Curso no encontrado");
			}
		}
		else {
			System.out.println("Estudiante no encontrado");
		}
	}
}
