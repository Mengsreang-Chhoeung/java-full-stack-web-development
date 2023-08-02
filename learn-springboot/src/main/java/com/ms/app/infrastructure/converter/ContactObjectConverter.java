package com.ms.app.infrastructure.converter;

import com.ms.app.model.obj.ContactObject;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ContactObjectConverter implements AttributeConverter<ContactObject, String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(ContactObject contactObject) {
        if (contactObject == null) return null;

        StringBuilder sb = new StringBuilder();
        if (contactObject.getPhone() != null && !contactObject.getPhone().isEmpty()) {
            sb.append(contactObject.getPhone());
            sb.append(SEPARATOR);
        }

        if (contactObject.getAddress() != null && !contactObject.getAddress().isEmpty()) sb.append(contactObject.getAddress());

        return sb.toString();
    }

    @Override
    public ContactObject convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        String[] pieces = dbData.split(SEPARATOR);
        if (pieces == null || pieces.length == 0) return null;

        ContactObject contactObject = new ContactObject();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbData.contains(SEPARATOR)) {
            contactObject.setPhone(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null && !pieces[1].isEmpty()) contactObject.setAddress(pieces[1]);
        } else {
            contactObject.setPhone(firstPiece);
        }

        return contactObject;
    }
}
