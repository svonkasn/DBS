package enteties;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class DelkaTrvaniConverter implements AttributeConverter<Masaz.DelkaTrvani, String> {

  @Override
  public String convertToDatabaseColumn(Masaz.DelkaTrvani attribute) {
    if (attribute == null) return null;
    return attribute.name().substring(1); // _90 → "90"
  }

  @Override
  public Masaz.DelkaTrvani convertToEntityAttribute(String dbData) {
    if (dbData == null) return null;
    return Masaz.DelkaTrvani.valueOf("_" + dbData); // "90" → _90
  }
}
