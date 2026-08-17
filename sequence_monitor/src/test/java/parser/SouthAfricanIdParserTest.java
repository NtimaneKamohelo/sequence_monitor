package parser;

import org.example.domain.ParsedIdentity;
import org.example.enums.Citizenship;
import org.example.enums.Gender;
import org.example.parser.SouthAfricanIdParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SouthAfricanIdParserTest {

    private final SouthAfricanIdParser parser =
            new SouthAfricanIdParser();

    @Test
    void shouldParseCompleteIdentity() {

        String idNumber =
                "9003155800084";

        ParsedIdentity result =
                parser.parse(idNumber);

        assertNotNull(result);

        assertEquals(
                idNumber,
                result.getIdNumber()
        );

        assertEquals(
                LocalDate.of(
                        1990,
                        3,
                        15
                ),
                result.getBirthDate()
        );

        assertEquals(
                Gender.MALE,
                result.getGender()
        );

        assertEquals(
                Citizenship.CITIZEN,
                result.getCitizenship()
        );

        assertEquals(
                8,
                result.getObsoleteDigit()
        );

        assertEquals(
                4,
                result.getChecksumDigit()
        );

        assertTrue(
                result.isChecksumValid()
        );

        assertTrue(
                result.isValid()
        );
    }

    @Test
    void shouldRejectInvalidFormat() {

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(
                        "12345"
                )
        );
    }

    @Test
    void shouldRejectInvalidDate() {

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(
                        "9013325800084"
                )
        );
    }

    @Test
    void shouldRejectInvalidCitizenshipCode() {

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(
                        "9003155800384"
                )
        );
    }

    @Test
    void shouldIdentifyInvalidChecksum() {

        String idNumber =
                "9003155800085";

        ParsedIdentity result =
                parser.parse(idNumber);

        assertFalse(
                result.isChecksumValid()
        );

        assertFalse(
                result.isValid()
        );
    }


}
