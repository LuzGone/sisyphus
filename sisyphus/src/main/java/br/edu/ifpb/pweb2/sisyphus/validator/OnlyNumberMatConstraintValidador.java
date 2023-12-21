
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyNumberMatConstraintValidador implements ConstraintValidator<OnlyNumberMat, String> {
    
    @Override
    public void initialize(OnlyNumberMat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Lógica de validação: verificar se a matrícula contém apenas números
        if (value == null) {
            return true; // Aceitar valores nulos, se aplicável
        }
        
        return value.matches("\\d+"); // Verificar se contém apenas números
    }
}

 */