package itemsreplenishment.events;

import static org.junit.Assert.assertTrue;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Strict.class)
public class ItemsReplenishmentSqsHandlerTest {

    @Test
    public void handleRequest() {
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("mf = " + MessageFormat.format("{0}/{1}/{2}/{3}", localDateTime.format(DateTimeFormatter.ofPattern("yyyy")),
                localDateTime.toLocalDate()
                        .getMonthValue(), localDateTime.toLocalDate()
                        .getDayOfMonth(), UUID.randomUUID()
                        .toString()));
        assertTrue(true);
    }
}