import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckStringLengthTest {

    @ParameterizedTest
    @ValueSource(strings = {"less_than_15", "more_than_15_symbols", "equal_15_symbol", ""})
    public void CheckStringLength(String anyString)
    {
        assertTrue(anyString.length() > 15, "Length of string '"+ anyString +"' <= 15");
    }
}
