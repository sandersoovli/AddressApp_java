package ch.makery.address.util;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter (for JAXB) to convert between LocalDate and ISO 8601 strings (e.g. '2012-12-03').
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        if (v == null || v.isEmpty()) return null;
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        if (v == null) return null;
        return v.toString();
    }
}
