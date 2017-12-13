/* import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.JsonSerializer;

public class CustomDateSerializer extends JsonSerializer<DateTime> {

  private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");

  @Override
  public void serialize(DateTime value, JsonGenerator gen, SerializerProvider arg2)
      throws IOException, JsonProcessingException {

    gen.writeString(formatter.print(value));
  }
} */