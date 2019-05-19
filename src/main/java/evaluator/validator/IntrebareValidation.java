package evaluator.validator;

import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;

public class IntrebareValidation implements IValidator<Intrebare> {
    @Override
    public void validate(Intrebare entity) throws Exception {
        validateEnunt(entity.getEnunt());
        validateVarianta1(entity.getVarianta1());
        validateVarianta2(entity.getVarianta2());
        validateVarianta3(entity.getVarianta3());
        validateVariantaCorecta(entity.getVariantaCorecta());
        validateDomeniu(entity.getDomeniu());
    }

    private void validateEnunt(String enunt) throws InputValidationFailedException {

        enunt = enunt.trim();

        if (enunt.equals(""))
            throw new InputValidationFailedException("Enuntul este vid!");
        if (!Character.isUpperCase(enunt.charAt(0)))
            throw new InputValidationFailedException("Prima litera din enunt nu e majuscula!");
        if (!String.valueOf(enunt.charAt(enunt.length() - 1)).equals("?"))
            throw new InputValidationFailedException("Ultimul caracter din enunt nu e '?'!");
        if (enunt.length() > 100)
            throw new InputValidationFailedException("Lungimea enuntului depaseste 100 de caractere!");
    }

    private void validateVarianta1(String varianta1) throws InputValidationFailedException {

        varianta1 = varianta1.trim();

        if (varianta1.equals(""))
            throw new InputValidationFailedException("Varianta1 este vida!");
        if (!String.valueOf(varianta1.charAt(0)).equals("1") || !String.valueOf(varianta1.charAt(1)).equals(")"))
            throw new InputValidationFailedException("Varianta1 nu incepe cu '1)'!");
        if (varianta1.length() > 50)
            throw new InputValidationFailedException("Lungimea variantei1 depaseste 50 de caractere!");
    }

    private void validateVarianta2(String varianta2) throws InputValidationFailedException {

        varianta2 = varianta2.trim();

        if (varianta2.equals(""))
            throw new InputValidationFailedException("Varianta2 este vida!");
        if (!String.valueOf(varianta2.charAt(0)).equals("2") || !String.valueOf(varianta2.charAt(1)).equals(")"))
            throw new InputValidationFailedException("Varianta2 nu incepe cu '2)'!");
        if (varianta2.length() > 50)
            throw new InputValidationFailedException("Lungimea variantei2 depaseste 50 de caractere!");
    }

    private void validateVarianta3(String varianta3) throws InputValidationFailedException {

        varianta3 = varianta3.trim();

        if (varianta3.equals(""))
            throw new InputValidationFailedException("Varianta3 este vida!");
        if (!String.valueOf(varianta3.charAt(0)).equals("3") || !String.valueOf(varianta3.charAt(1)).equals(")"))
            throw new InputValidationFailedException("Varianta3 nu incepe cu '3)'!");
        if (varianta3.length() > 50)
            throw new InputValidationFailedException("Lungimea variantei3 depaseste 50 de caractere!");

    }

    private void validateVariantaCorecta(Integer variantaCorecta) throws InputValidationFailedException {

        if (variantaCorecta != 1 && variantaCorecta != 2 && variantaCorecta != 3)
            throw new InputValidationFailedException("Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}");
    }

    private void validateDomeniu(String domeniu) throws InputValidationFailedException {

        domeniu = domeniu.trim();

        if (domeniu.equals(""))
            throw new InputValidationFailedException("Domeniul este vid!");
        if (!Character.isUpperCase(domeniu.charAt(0)))
            throw new InputValidationFailedException("Prima litera din domeniu nu e majuscula!");
        if (domeniu.length() > 30)
            throw new InputValidationFailedException("Lungimea domeniului depaseste 30 de caractere!");

    }
}
