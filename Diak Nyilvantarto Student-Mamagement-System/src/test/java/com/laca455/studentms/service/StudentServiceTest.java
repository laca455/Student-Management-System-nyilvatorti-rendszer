package com.laca455.studentms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.laca455.studentms.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.laca455.studentms.exception.EmptyInputException;
import com.laca455.studentms.exception.StudentNotFoundException;
import com.laca455.studentms.repository.ProjectRepository;
import com.laca455.studentms.repository.StudentRepository;

class StudentServiceTest {

	private static final Long ID_1 = 1L;
	private static final String FIRST_NAME_1 = "Papp";
	private static final String LAST_NAME_1 = "Laszlo";
	private static final LocalDate DOB_1 = LocalDate.of(1999, 11, 20);
	private static final String EMAIL_1 = "laca4555@gmail.com";
	private static final Integer INDEX_1 = 183;
	private static final Boolean IS_ON_BUDGET_1 = true;

	private static final Long ID_2 = 2L;
	private static final String FIRST_NAME_2 = "Teszt";
	private static final String LAST_NAME_2 = "tESZT";
	private static final LocalDate DOB_2 = LocalDate.of(2000, 1, 01);
	private static final String EMAIL_2 = "teszt@gmail.com";
	private static final Integer INDEX_2 = 169;
	private static final Boolean IS_ON_BUDGET_2 = false;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private ProjectRepository projectRepository;

	private StudentService studentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		studentService = new StudentService(studentRepository, projectRepository);
	}

	@Test
	void testStudentService() {
	}

	// SAVE
	@Test
	void shouldSaveStudent() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);
		studentService.saveStudent(student);

		ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(studentArgumentCaptor.capture());

		Student capturetStudent = studentArgumentCaptor.getValue();
		assertThat(capturetStudent).isEqualTo(student);
	}

	@Test
	void saveStudentShouldThrowExceptionWhenStudentParametersIsNull() {
		Student student = new Student(null, null, null, null, null, null, null);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenStudentIsNull() {
		Student student = null;

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenFirstNameIsNull() {
		Student student = new Student(ID_1, null, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenFirstNameIsEmpty() {
		Student student = new Student(ID_1, "", LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenFirstNameIsEmptyWithSpace() {
		Student student = new Student(ID_1, " ", LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenLastNameIsNull() {
		Student student = new Student(ID_1, FIRST_NAME_1, null, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenLastNameIsEmpty() {
		Student student = new Student(ID_1, FIRST_NAME_1, "", DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenLastNameIsEmptyWithSpace() {
		Student student = new Student(ID_1, FIRST_NAME_1, " ", DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenDobIsEmpty() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, null, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenEmailIsNull() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, null, INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenEmailIsEmpty() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, "", INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenEmailIsEmptyWithSpace() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, " ", INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenIndexIsEmpty() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, null, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenIndexIs0() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, 0, IS_ON_BUDGET_1);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	@Test
	void saveStudentShouldThrowExceptionWhenIsOnBudgetIsEmpty() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, null);

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.saveStudent(student))
				.withMessage("Töltse ki a mezőket.");
	}

	// DEVELOP
	@Test
	void saveStudentShouldThrowExceptionWhenStudentWithSameEmailExist() {
		
	}

	// GET ALL
	@Test
	void shouldGetAllStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1));
		students.add(new Student(ID_2, FIRST_NAME_2, LAST_NAME_2, DOB_2, EMAIL_2, INDEX_2, IS_ON_BUDGET_2));

		when(studentRepository.findAll()).thenReturn(students);

		assertThat(studentService.getAllStudents()).isEqualTo(students);
	}

	@Test
	void getAllStudentsShouldThrowExceptionWhenStudentsNotExistInDB() {

		assertThatExceptionOfType(StudentNotFoundException.class).isThrownBy(() -> studentService.getAllStudents())
				.withMessage("Diák nem található.");
	}

	// FIND BY ID
	@Test
	void shouldFindStudentById() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentRepository.findStudentById(ID_1)).thenReturn(Optional.of(student));

		assertThat(studentService.findStudentById(ID_1)).isEqualTo(student);
	}

	@Test
	void findStudentByIdShouldThrowExceptionWhenIdIsNull() {

		assertThatExceptionOfType(StudentNotFoundException.class).isThrownBy(() -> studentService.findStudentById(3L))
				.withMessage("A diák ID: 3 nem létezik.");
	}

	// FIND BY EMAIL
	@Test
	void shouldFindStudentByEmail() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentRepository.findStudentByEmail(EMAIL_1)).thenReturn(student);

		assertThat(studentService.findStudentByEmail(EMAIL_1)).isEqualTo(student);
	}

	@Test
	void findStudentByEmailShouldThrowExceptionWhenEmailIsNull() {

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.findStudentByEmail(null))
				.withMessage("Érvénytelen Email.");
	}

	@Test
	void findStudentByEmailShouldThrowExceptionWhenEmailIsEmpty() {

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.findStudentByEmail(""))
				.withMessage("Érvénytelen Email.");
	}

	@Test
	void findStudentByEmailShouldThrowExceptionWhenEmailIsEmptyWithSpace() {

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.findStudentByEmail(" "))
				.withMessage("Érvénytelen Email.");
	}

	@Test
	void findStudentByEmailShouldThrowExceptionWhenEmailNotExist() {

		assertThatExceptionOfType(StudentNotFoundException.class)
				.isThrownBy(() -> studentService.findStudentByEmail("asd@gmail.com"))
				.withMessage("Diák ezel az Email-címmel: asd@gmail.com nem létezik.");
	}

	// FIND BY INDEX NUMBER
	@Test
	void shouldFindStudentsByIndexNumber() {
		List<Student> students = new ArrayList<>();
		Student student1 = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);
		Student student2 = new Student(ID_2, FIRST_NAME_2, LAST_NAME_2, DOB_2, EMAIL_2, INDEX_2, IS_ON_BUDGET_2);
		students.add(student1);
		students.add(student2);

		when(studentRepository.findStudentsByIndexNumber(INDEX_1)).thenReturn(students);

		assertThat(studentService.findStudentsByIndexNumber(INDEX_1)).isEqualTo(students);
	}

	@Test
	void findStudentByIndexShouldThrowExceptionWhenIndexIsNull() {

		assertThatExceptionOfType(EmptyInputException.class)
				.isThrownBy(() -> studentService.findStudentsByIndexNumber(null))
				.withMessage("Adja meg a diék index számát. Ez nem lehet 0.");
	}

	@Test
	void findStudentByIndexShouldThrowExceptionWhenIndexIs0() {

		assertThatExceptionOfType(EmptyInputException.class)
				.isThrownBy(() -> studentService.findStudentsByIndexNumber(0))
				.withMessage("Adja meg a diék index számát. Ez nem lehet 0."");
	}

	@Test
	void findStudentByIndexShouldThrowExceptionWhenIndexNotExist() {

		assertThatExceptionOfType(StudentNotFoundException.class)
				.isThrownBy(() -> studentService.findStudentsByIndexNumber(11))
				.withMessage("Diák ezzel az index számmal: 11 nem létezik.");
	}

	// GET BETWEEN TWO DOB
	@Test
	void shouldGetStudentsBetweenTwoDOB() {
		LocalDate date1 = LocalDate.of(1998, 8, 25);
		LocalDate date2 = LocalDate.of(2000, 12, 01);

		List<Student> students = new ArrayList<>();
		students.add(new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1));
		students.add(new Student(ID_2, FIRST_NAME_2, LAST_NAME_2, DOB_2, EMAIL_2, INDEX_2, IS_ON_BUDGET_2));

		when(studentRepository.findBetweenTwoDOB(date1, date2)).thenReturn(students);

		assertThat(studentService.getStudentsBetweenTwoDOB(date1, date2)).isEqualTo(students);
	}

	@Test
	void getStudentsBetweenTwoDobShouldThrowExceptionWhenStudentsBetweenNotExist() {
		LocalDate date1 = LocalDate.of(2001, 8, 25);
		LocalDate date2 = LocalDate.of(2002, 12, 01);

		assertThatExceptionOfType(StudentNotFoundException.class)
				.isThrownBy(() -> studentService.getStudentsBetweenTwoDOB(date1, date2))
				.withMessage("A diák nem létezik ezzel a születési dátummal: " + date1 + " - " + date2);
	}

	// UPDATE BY ID - DEVELOP
	@Test
	void shouldUpdateStudentById() {
		
	}

	@Test
	void updateStudentByIdShouldThrowExceptionWhenStudentIsNull() {
		Student student = null;

		assertThatExceptionOfType(StudentNotFoundException.class)
				.isThrownBy(() -> studentService.updateStudentById(student, ID_1))
				.withMessage("Diák ezzel az azonosíóval: 1 nem létezik.");
	}

	@Test
	void updateStudentByIdShouldThrowExceptionWhenStudentParametersIsNull() {
		Student student = new Student(null, null, null, null, null, null, null);

		assertThatExceptionOfType(StudentNotFoundException.class)
				.isThrownBy(() -> studentService.updateStudentById(student, ID_1))
				.withMessage("Diák ezzel az azonosíóval: 1 nem létezik.");
	}

	@Test
	void updateStudentByIdShouldThrowExceptionWhenStudentWithIdNotExistInDb() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		assertThatExceptionOfType(StudentNotFoundException.class)
				.isThrownBy(() -> studentService.updateStudentById(student, ID_1))
				.withMessage("Diák ezzel az azonosíóval: 1 nem létezik.");
	}

	// DELETE BY ID
	@Test
	void shouldDeleteStudentById() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentRepository.findById(ID_1)).thenReturn(Optional.of(student));
		studentService.deleteStudentById(ID_1);
		verify(studentRepository).deleteById(ID_1);
	}

	@Test
	void deleteStudentByIdShouldThrowExceptionWhenIdIsNull() {

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.deleteStudentById(0L))
				.withMessage("Adja meg a törlendő diék ID-ját . Ez nem lehet 0.");
	}

	@Test
	void deleteStudentByIdShouldThrowExceptionWhenIdNotExistInDB() {

		assertThatExceptionOfType(StudentNotFoundException.class).isThrownBy(() -> studentService.deleteStudentById(3L))
				.withMessage("A diák nem törölhető, mert ezzel az azonosítóval: 3 nem létezik.");
	}

	// DELETE BY EMAIL
	@Test
	void shouldDeleteStudentByEmail() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentRepository.findStudentByEmail(EMAIL_1)).thenReturn(student);
		studentService.deleteStudentByEmail(EMAIL_1);
		verify(studentRepository).deleteStudentByEmail(EMAIL_1);
	}

	@Test
	void deleteStudentByIdShouldThrowExceptionWhenEmailIsNull() {

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.deleteStudentByEmail(null))
				.withMessage("Érvénytelen Email.");
	}

	@Test
	void deleteStudentByIdShouldThrowExceptionWhenEmailIsEmpty() {

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.deleteStudentByEmail(""))
				.withMessage("Érvénytelen Email.");
	}

	@Test
	void deleteStudentByIdShouldThrowExceptionWhenEmailIsEmptyWithSpace() {

		assertThatExceptionOfType(EmptyInputException.class).isThrownBy(() -> studentService.deleteStudentByEmail(" "))
				.withMessage("Érvénytelen Email.");
	}

	@Test
	void deleteStudentByIdShouldThrowExceptionWhenEmailNotExist() {

		assertThatExceptionOfType(StudentNotFoundException.class)
				.isThrownBy(() -> studentService.deleteStudentByEmail("asd@gmail.com"))
				.withMessage("Diák nem törölhető, mert ezzel az Email-címmel: asd@gmail.com nem létezik.");
	}

}
