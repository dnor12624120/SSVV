package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationServiceTest {
    private StudentXMLRepo instantiateStudentRepository() { return new StudentXMLRepo("students.xml"); }
    private TemaXMLRepo instantiateTemaRepository() { return new TemaXMLRepo("teme.xml"); }
    private NotaXMLRepo instantiateNotaRepository() { return new NotaXMLRepo("note.xml"); }
    private StudentValidator instantiateStudentValidator() { return new StudentValidator(); }
    private TemaValidator instantiateTemaValidator() { return new TemaValidator(); }
    private NotaValidator instantiateNotaValidator(StudentXMLRepo studentXMLRepo, TemaXMLRepo temaXMLRepo) { return new NotaValidator(studentXMLRepo, temaXMLRepo); }
    private Service instantiateService() {
        StudentXMLRepo studentXMLRepo = instantiateStudentRepository();
        TemaXMLRepo temaXMLRepo = instantiateTemaRepository();
        return new Service(studentXMLRepo,
            instantiateStudentValidator(),
            temaXMLRepo, instantiateTemaValidator(), instantiateNotaRepository(), instantiateNotaValidator(studentXMLRepo, temaXMLRepo)); }


    @Test
    void BigBang_addStudent_Valid() {
        Service service = instantiateService();

        Student student = new Student("1", "Firstname Lastname", 911, "firstnamelastname@gmail.com");
        service.addStudent(student);
        assert(student.equals(service.findStudent(student.getID())));
    }

    @Test
    void BigBang_addTema_Valid() {
        Service service = instantiateService();

        Tema tema = new Tema("1", "description", 1, 2);
        service.addTema(tema);
        assert(tema.equals(service.findTema(tema.getID())));
    }

    @Test
    void BigBang_addNota_Valid() {
        Service service = instantiateService();

        Student student = new Student("1", "Firstname Lastname", 911, "firstnamelastname@gmail.com");
        service.addStudent(student);

        Tema tema = new Tema("1", "description", 1, 2);
        service.addTema(tema);

        Nota nota = new Nota("1", "1", "1", 5, LocalDate.of(2020, 10, 10));
        service.addNota(nota, "feedback");
        assert(nota.equals(service.findNota(nota.getID())));
    }

    @Test
    void BigBang_addNotaIntegrated_Valid() {
        Service service = instantiateService();

        Student student = new Student("1", "Firstname Lastname", 911, "firstnamelastname@gmail.com");
        service.addStudent(student);
        assert(student.equals(service.findStudent(student.getID())));

        Tema tema = new Tema("1", "description", 1, 2);
        service.addTema(tema);
        assert(tema.equals(service.findTema(tema.getID())));

        Nota nota = new Nota("1", "1", "1", 5, LocalDate.of(2020, 10, 10));
        service.addNota(nota, "feedback");
        assert(nota.equals(service.findNota(nota.getID())));

    }
}