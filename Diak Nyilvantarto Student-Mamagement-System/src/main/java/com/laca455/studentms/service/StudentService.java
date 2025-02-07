package com.laca455.studentms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.laca455.studentms.exception.EmailInUseException;
import com.laca455.studentms.exception.EmptyInputException;
import com.laca455.studentms.exception.ProjectNotFoundException;
import com.laca455.studentms.exception.StudentNotFoundException;
import com.laca455.studentms.model.Project;
import com.laca455.studentms.model.Student;
import com.laca455.studentms.repository.ProjectRepository;
import com.laca455.studentms.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	private final ProjectRepository projectRepository;

	public StudentService(StudentRepository studentRepository, ProjectRepository projectRepository) {
		this.studentRepository = studentRepository;
		this.projectRepository = projectRepository;
	}

	public Student saveStudent(Student student) {
		Student checkIfStudentWithEmailExist = studentRepository.findStudentByEmail(student.getEmail());
		if (checkIfStudentWithEmailExist != null) {
			throw new EmailInUseException("Ezt az emailt már használják.");
		}
		return studentRepository.save(student);
	}

	public List<Student> getAllStudents() {
		List<Student> students = (List<Student>) studentRepository.findAll();
		if (students.isEmpty()) {
			throw new StudentNotFoundException("Diák nem található.");
		}
		return students;
	}

	public Student findStudentById(Long id) {
		return studentRepository.findStudentById(id)
				.orElseThrow(() -> new StudentNotFoundException("Diák azonosító: " + id + " Nem létezik."));
	}

	public Student findStudentByEmail(String email) {
		if (StringUtils.isBlank(email)) {
			throw new EmptyInputException("Érvénytelen email.");
		}
		Student checkIfStudentWithEmailExist = studentRepository.findStudentByEmail(email);
		if (checkIfStudentWithEmailExist == null) {
			throw new StudentNotFoundException("Diák emailcímmel: " + email + " Nem létezik.");
		}
		return checkIfStudentWithEmailExist;
	}

	public List<Student> findStudentsByIndexNumber(Integer indexNumber) {
		if (indexNumber == null || indexNumber == 0) {
			throw new EmptyInputException(
					"Adja meg a diék index számát. Ez nem lehet 0.");
		}
		List<Student> students = studentRepository.findStudentsByIndexNumber(indexNumber);
		if (students.isEmpty()) {
			throw new StudentNotFoundException("Diák index: " + indexNumber + " Nem létezik.");
		}
		return students;
	}

	public List<Student> getStudentsBetweenTwoDOB(LocalDate dob1, LocalDate dob2) {
		List<Student> students = studentRepository.findBetweenTwoDOB(dob1, dob2);
		if (students.isEmpty()) {
			throw new StudentNotFoundException("A diák nem létezik ezzel a születési dátummal: " + dob1 + " - " + dob2);
		}
		return students;
	}

	@Transactional
	public Student updateStudentById(Student student, Long id) {
		Student updateStudent = new Student();

		Optional<Student> studentDB = studentRepository.findStudentById(id);
		if (studentDB.isEmpty()) {
			throw new StudentNotFoundException("Diák id: " + id + " Nem létezik.");
		}
		if (StringUtils.isNotBlank(student.getFirstName())
				&& !Objects.equals(student.getFirstName(), studentDB.get().getFirstName())) {
			updateStudent.setFirstName(student.getFirstName());
		} else {
			updateStudent.setFirstName(studentDB.get().getFirstName());
		}
		if (StringUtils.isNotBlank(student.getLastName())
				&& !Objects.equals(student.getLastName(), studentDB.get().getLastName())) {
			updateStudent.setLastName(student.getLastName());
		} else {
			updateStudent.setLastName(studentDB.get().getLastName());
		}
		if (student.getDateOfBirth() != null
				&& !Objects.equals(student.getDateOfBirth(), studentDB.get().getDateOfBirth())) {
			updateStudent.setDateOfBirth(student.getDateOfBirth());
		} else {
			updateStudent.setDateOfBirth(studentDB.get().getDateOfBirth());
		}
		if (StringUtils.isNotBlank(student.getEmail())
				&& !Objects.equals(student.getEmail(), studentDB.get().getEmail())) {

			Student checkIfStudentWithEmailExist = studentRepository.findStudentByEmail(student.getEmail());
			if (checkIfStudentWithEmailExist != null) {
				throw new EmailInUseException("Ezt az emailt már használják.");
			}
			updateStudent.setEmail(student.getEmail());
		} else {
			updateStudent.setEmail(studentDB.get().getEmail());
		}
		String indexNumberLength = String.valueOf(student.getIndexNumber());
		if (student.getIndexNumber() != null && indexNumberLength.length() > 0
				&& !Objects.equals(student.getIndexNumber(), studentDB.get().getIndexNumber())) {
			updateStudent.setIndexNumber(student.getIndexNumber());
		} else {
			updateStudent.setIndexNumber(studentDB.get().getIndexNumber());
		}
		if (student.getIsOnBudget() != null
				&& !Objects.equals(student.getIsOnBudget(), studentDB.get().getIsOnBudget())) {
			updateStudent.setIsOnBudget(student.getIsOnBudget());
		} else {
			updateStudent.setIsOnBudget(studentDB.get().getIsOnBudget());
		}
		updateStudent.setId(id);
		return studentRepository.save(updateStudent);
	}

	public void deleteStudentById(Long id) {
		if (id == 0) {
			throw new EmptyInputException("Adja meg a törlendő diék ID-ját . Ez nem lehet 0.");
		}
		Optional<Student> checkIfStudentWithIdExist = studentRepository.findById(id);
		if (checkIfStudentWithIdExist.isEmpty()) {
			throw new StudentNotFoundException(
					"A diák nem törlendp mivel id-val rendelkezik: " + id + " Nem létezik.");
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public void deleteStudentByEmail(String email) {
		if (StringUtils.isBlank(email)) {
			throw new EmptyInputException("Érvénytelen Emailcím.");
		}
		Student checkIfStudentWithEmailExist = studentRepository.findStudentByEmail(email);
		if (checkIfStudentWithEmailExist == null) {
			throw new StudentNotFoundException(
					"A diék nem törölhető mer Emailcímmel rendelkezik: " + email + " Nem létezik.");
		}
		studentRepository.deleteStudentByEmail(email);
	}

	public Project createProjectForStudent(Long id, Project project) {
		Optional<Student> student = studentRepository.findStudentById(id);
		if (student.isEmpty()) {
			throw new StudentNotFoundException("Diák id: " + id + " Nem létezik.");
		}
		project.setStudent(student.get());
		Project savedProject = projectRepository.save(project);
		return savedProject;
	}

	public List<Project> getProjectsByIdForStudentById(Long id) {
		Optional<Student> student = studentRepository.findStudentById(id);
		if (student.isEmpty()) {
			throw new StudentNotFoundException("Diák id: " + id + " Nem létezik.");
		}
		return student.get().getProjects();
	}

	public Project getProjectByIdForStudentById(Long studentId, Long projectId) {
		Optional<Student> student = studentRepository.findStudentById(studentId);
		if (student.isEmpty()) {
			throw new StudentNotFoundException("Diák id: " + studentId + " Nem létezik.");
		}
		List<Project> projects = student.get().getProjects();
		for (Project p : projects) {
			if (p.getId() == projectId) {
				return p;
			}
		}
		throw new ProjectNotFoundException("Adja meg a projectid: " + projectId + " Nem létezik.");
	}

}