package service;

import domain.Student;
import org.junit.jupiter.api.Test;
import repository.StudentXMLRepo;
import validation.StudentValidator;

class ServiceTest {
    private StudentXMLRepo instantiateStudentRepository() { return new StudentXMLRepo("students.xml"); }
    private StudentValidator instantiateStudentValidator() { return new StudentValidator(); }
    private Service instantiateService() { return new Service(instantiateStudentRepository(),
                                                              instantiateStudentValidator(),
                                                              null, null, null, null); }

    @Test
    void TC1_AddStudent_Valid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 911, "firstnamelastname@gmail.com");
        assert(student.equals(service.addStudent(student)));
    }

    @Test
    void TC2_AddStudent_IdNull_Invalid() {
        Service service = instantiateService();
        Student student = new Student(null, "Firstname Lastname", 911, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC3_AddStudent_NameNull_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", null, 911, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC4_AddStudent_EmailNull_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 911, null);
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC5_IdEmpty_Invalid() {
        Service service = instantiateService();
        Student student = new Student("", "Firstname Lastname", 911, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC6_NameEmpty_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "", 911, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC7_EmailEmpty_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 911, "");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC8_NameContainsIllegalCharacters_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname! Lastname?", 911, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC9_EmailInvalidFormat_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 911, "invalid_email");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC10_StudentGroupZero_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 0, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC11_StudentGroupNegative_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", -1, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC12_StudentNameNoSpaces_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname", 911, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC13_StudentNameMoreSpaces_Valid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Middlename Lastname", 911, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(!exceptionThrown);
    }

    @Test
    void TC14_StudentGroupLowerBound1_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 99, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC15_StudentGroupBound1_Valid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 100, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(!exceptionThrown);
    }

    @Test
    void TC16_StudentGroupUpperBound1_Valid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 101, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(!exceptionThrown);
    }

    @Test
    void TC17_StudentGroupLowerBound2_Valid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 998, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(!exceptionThrown);
    }

    @Test
    void TC18_StudentGroupBound2_Valid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 999, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(!exceptionThrown);
    }

    @Test
    void TC19_StudentGroupUpperBound2_Invalid() {
        Service service = instantiateService();
        Student student = new Student("1", "Firstname Lastname", 1000, "firstnamelastname@gmail.com");
        boolean exceptionThrown = false;
        try {
            service.addStudent(student);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }
}