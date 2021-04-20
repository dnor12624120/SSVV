package service;

import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class WhiteboxTest {
    private TemaXMLRepo instantiateEmptyTemaRepository() { return new TemaXMLRepo("src/test/resources/teme_empty.xml"); }
    private TemaXMLRepo instantiateNonEmptyTemaRepository() { return new TemaXMLRepo("src/test/resources/teme_nonempty.xml"); }
    private TemaValidator instantiateTemaValidator() { return new TemaValidator(); }
    private Service instantiateService(TemaXMLRepo temaXMLRepo) { return new Service(null, null,
            temaXMLRepo, instantiateTemaValidator(), null, null); }

    void clearRepository(Service service) {
        try {
            service.deleteTema("1");
        }
        catch (ValidationException ignored) {

        }
    }

    @Test
    void TC1_InvalidIDPath() {
        Service service = instantiateService(instantiateEmptyTemaRepository());
        Tema tema = new Tema("", "description", 2, 1);
        boolean exceptionThrown = false;
        try {
            service.addTema(tema);
        }
        catch (ValidationException e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC2_InvalidDescriptionPath() {
        Service service = instantiateService(instantiateEmptyTemaRepository());
        Tema tema = new Tema("1", "", 2, 1);
        boolean exceptionThrown = false;
        try {
            service.addTema(tema);
        }
        catch (ValidationException e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC3_InvalidDeadlinePath() {
        Service service = instantiateService(instantiateEmptyTemaRepository());
        Tema tema = new Tema("1", "description", 0, 1);
        boolean exceptionThrown = false;
        try {
            service.addTema(tema);
        }
        catch (ValidationException e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC4_InvalidPrimirePath() {
        Service service = instantiateService(instantiateEmptyTemaRepository());
        Tema tema = new Tema("1", "description", 2, 0);
        boolean exceptionThrown = false;
        try {
            service.addTema(tema);
        }
        catch (ValidationException e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC5_AlreadyExistingTemaPath() throws FileNotFoundException {
        Service service = instantiateService(instantiateEmptyTemaRepository());
        clearRepository(service);
        Tema tema = new Tema("1", "description", 2, 1);
        service.addTema(tema);
        boolean exceptionThrown = false;
        try {
            service.addTema(tema);
        }
        catch (RuntimeException e) {
            exceptionThrown = true;
        }
        assert(exceptionThrown);
    }

    @Test
    void TC6_ValidPath() throws FileNotFoundException {
        Service service = instantiateService(instantiateEmptyTemaRepository());
        clearRepository(service);
        Tema tema = new Tema("1", "description", 2, 1);
        boolean exceptionThrown = false;
        try {
            service.addTema(tema);
        }
        catch (ValidationException e) {
            exceptionThrown = true;
        }
        assert(!exceptionThrown);
    }
}