package validation;

import domain.Student;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
        public void validate(Student entity) throws ValidationException {
            // 2
            if(entity.getID().equals("")){                            // 3
                throw new ValidationException("Id incorect!");        // 4
            }
            if(entity.getID() == null){                               // 5
                throw new ValidationException("Id incorect!");        // 6
            }
            if(entity.getNume() == ""){                               // 7
                throw new ValidationException("Nume incorect!");      // 8
            }
            if(entity.getGrupa() < 0) {                               // 9
                throw new ValidationException("Grupa incorecta!");    // 10
            }
            if(entity.getEmail() == null){                            // 11
                throw new ValidationException("Email incorect!");     // 12
            }
            if(entity.getNume() == null){                             // 13
                throw new ValidationException("Nume incorect!");      // 14
            }
            if(entity.getEmail().equals("")){                         // 15
                throw new ValidationException("Email incorect!");     // 16
            }
        }
}
