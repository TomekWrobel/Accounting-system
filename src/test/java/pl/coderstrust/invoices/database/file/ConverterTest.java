package pl.coderstrust.invoices.database.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.coderstrust.invoices.model.Invoice;

class ConverterTest {

  private static Converter converter;

  @BeforeAll
  static void setup() {
    converter = new Converter(new ObjectMapper());
  }

  @Test
  void shouldConvertInvoiceObjectToString() {
    //given
    Invoice invoice = new Invoice(1, "1", LocalDate.of(2019, 4, 11), null,
        LocalDate.of(2019, 4, 11), null, null, null);
    String expected = "{\"id\":1,\"number\":\"1\",\"issueDate\":\"2019-04-11\",\"issuePlace\":null"
        + ",\"sellDate\":\"2019-04-11\",\"seller\":null,\"buyer\":null,\"entries\":[]}";

    //when
    String actual = converter.objectToString(invoice);

    //then
    assertEquals(expected, actual);
  }

  @Test
  void shouldConvertStringToInvoiceObject() {
    //given
    String line = "{\"id\":1,\"number\":\"1\",\"issueDate\":\"2019-04-11\",\"issuePlace\":null,"
        + "\"sellDate\":\"2019-04-11\",\"seller\":null,\"buyer\":null,\"entries\":[]}";
    Invoice expected = new Invoice(1, "1", LocalDate.of(2019, 4, 11), null,
        LocalDate.of(2019, 4, 11), null, null,
        null);

    //when
    Invoice actual = converter.stringToInvoice(line);

    //then
    assertEquals(expected, actual);
  }

  @Test
  void shouldConvertStringListToInvoicesList() {
    //given
    List<String> list = new ArrayList<>(Arrays.asList(
        "{\"id\":1,\"number\":\"1\",\"issueDate\":\"2019-04-11\",\"issuePlace\":null,"
            + "\"sellDate\":\"2019-04-11\",\"seller\":null,\"buyer\":null,\"entries\":[]}",
        "{\"id\":2,\"number\":\"2\",\"issueDate\":\"2019-04-11\",\"issuePlace\":null,"
            + "\"sellDate\":\"2019-04-11\",\"seller\":null,\"buyer\":null,\"entries\":[]}",
        "{\"id\":3,\"number\":\"3\",\"issueDate\":\"2019-04-11\",\"issuePlace\":null,"
            + "\"sellDate\":\"2019-04-11\",\"seller\":null,\"buyer\":null,\"entries\":[]}"));

    List<Invoice> expected = new ArrayList<>(
        Arrays.asList(
            new Invoice(1, "1", LocalDate.of(2019, 4, 11), null,
                LocalDate.of(2019, 4, 11), null, null,
                null),
            new Invoice(2, "2", LocalDate.of(2019, 4, 11), null,
                LocalDate.of(2019, 4, 11), null, null,
                null),
            new Invoice(3, "3", LocalDate.of(2019, 4, 11), null,
                LocalDate.of(2019, 4, 11), null, null,
                null)));

    //when
    List<Invoice> actual = (List<Invoice>) converter.stringListToInvoicesList(list);

    //then
    assertEquals(expected, actual);
  }

  @Test
  void shouldConvertInvoicesListToStringList() {
    //given
    List<Invoice> list = new ArrayList<>(
        Arrays.asList(
            new Invoice(1, "1", LocalDate.of(2019, 4, 11), null,
                LocalDate.of(2019, 4, 11), null, null,
                null),
            new Invoice(2, "2", LocalDate.of(2019, 4, 11), null,
                LocalDate.of(2019, 4, 11), null, null,
                null),
            new Invoice(3, "3", LocalDate.of(2019, 4, 11), null,
                LocalDate.of(2019, 4, 11), null, null,
                null)));

    List<String> expected = new ArrayList<>(Arrays.asList(
        "{\"id\":1,\"number\":\"1\",\"issueDate\":\"2019-04-11\",\"issuePlace\":null,"
            + "\"sellDate\":\"2019-04-11\",\"seller\":null,\"buyer\":null,\"entries\":[]}",
        "{\"id\":2,\"number\":\"2\",\"issueDate\":\"2019-04-11\",\"issuePlace\":null,"
            + "\"sellDate\":\"2019-04-11\",\"seller\":null,\"buyer\":null,\"entries\":[]}",
        "{\"id\":3,\"number\":\"3\",\"issueDate\":\"2019-04-11\",\"issuePlace\":null,"
            + "\"sellDate\":\"2019-04-11\",\"seller\":null,\"buyer\":null,\"entries\":[]}"));

    //when
    List<String> actual = (List<String>) converter.invoicesListToStringList(list);

    //then
    assertEquals(expected, actual);
  }
}