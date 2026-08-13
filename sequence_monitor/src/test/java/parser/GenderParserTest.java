package parser;

import org.example.enums.Gender;
import org.example.parser.GenderParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GenderParserTest {

    private final GenderParser genderParser = new GenderParser();

    @Test
    public void shouldReturnFemaleWhenGenderSequenceIsBelow5000(){
        String idNumber = "4012163119085";

        Gender result = genderParser.parse(idNumber);

        assertEquals(
                Gender.FEMALE,
                result
        );
    }

    @Test
    public void shouldRejectNullId(){
        assertThrows(
                IllegalArgumentException.class,
                () -> genderParser.parse(null)
        );
    }

    @Test
    public void shouldRejectIdContainingLetters() {

        assertThrows(
                IllegalArgumentException.class,
                () -> genderParser.parse("900315ABC0084")
        );
    }
}
