package validation;

import domain.Tema;

public class TemaValidator implements Validator<Tema> {

    /**
     * Valideaza o tema
     * @param entity - tema pe care o valideaza
     * @throws ValidationException daca tema nu e valida
     */
    @Override
    public void validate(Tema entity) throws ValidationException {
                                                                           // 2
        if(entity.getID().equals("") || entity.getID() == null) {          // 3
            throw new ValidationException("Numar tema invalid!");          // 4
        }
        if(entity.getDescriere().equals("")){                              // 5
            throw new ValidationException("Descriere invalida!");          // 6
        }
        if(entity.getDeadline() < 1 || entity.getDeadline() > 14) {        // 7
            throw new ValidationException("Deadlineul trebuie sa fie" +    // 8
                    " intre 1-14."); // 8
        }
        if(entity.getPrimire() < 1 || entity.getPrimire() > 14) {          // 9
            throw new ValidationException("Saptamana primirii trebuie" +   // 10
                    " sa fie intre 1-14.");
        }
    }
}
