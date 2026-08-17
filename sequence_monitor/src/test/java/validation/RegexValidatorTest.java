package validation;

import org.example.validation.RegexValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RegexValidatorTest {

    private final RegexValidator validator = new RegexValidator();

    @Test
    public void shouldAcceptExactly13Digits(){
        assertTrue(
                validator.isValid("0008305488085")
        );
    }

    @Test
    public void shouldRejectNull() {

        assertFalse(
                validator.isValid(null)
        );
    }

    @Test
    public void shouldRejectLessThan13Digits() {

        assertFalse(
                validator.isValid(
                        "90008305488"
                )
        );
    }

    @Test
    public void shouldRejectMoreThan13Digits() {

        assertFalse(
                validator.isValid(
                        "000830548808555"
                )
        );
    }

    @Test
    public void shouldRejectLetters() {

        assertFalse(
                validator.isValid(
                        "ABC8305488085"
                )
        );
    }

    @Test
    public void shouldRejectSpaces() {

        assertFalse(
                validator.isValid(
                        "000830  5488085"
                )
        );
    }

    @Test
    public void shouldRejectEmptyString() {

        assertFalse(
                validator.isValid("")
        );
    }
}
