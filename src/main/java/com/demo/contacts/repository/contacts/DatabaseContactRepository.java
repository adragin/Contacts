package com.demo.contacts.repository.contacts;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class DatabaseContactRepository implements ContactRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Contact> contactRowMapper = (ResultSet rs, int rowNum) -> {

        return Contact.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .name(rs.getString("name"))
                .build();
    };

    @Override
    public Contact getContact(int id) {
        String sql = "SELECT id, name, email FROM contacts WHERE id = ?";
        Contact contact;
        try {
            contact = jdbcTemplate.queryForObject(sql, contactRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            contact = null;
        }

        return contact;
    }

    @Override
    public List<Contact> getAllContacts(int ownerId) {
        String sql = "SELECT id, name, email FROM contacts ORDER BY created_at DESC Where owner_id = ?";
        return jdbcTemplate.query(sql, contactRowMapper, ownerId);
    }

    @Override
    public Contact createContact(ContactDTO contact, int ownerId) {
        String sql = "INSERT INTO contacts(name, email, owner_id) VALUE(?, ?, ?)";
        String sqlName = "SELECT id, name, email FROM contacts WHERE name = ?";

        try {
            Contact oldContact = jdbcTemplate.queryForObject(sqlName, contactRowMapper, contact.getName());
            System.out.println(oldContact);
            if (oldContact != null) {
                System.out.println("The contact with this name exist");
                return null;
            }
        } catch (DataAccessException e) {
            System.out.println("The contact with this name not exist");
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setInt(3, ownerId);
            return ps;
        });

        return jdbcTemplate.queryForObject(sqlName, contactRowMapper, contact.getName());
    }

    @Override
    public Contact updateContact(int id, ContactDTO contact) {
        String sql = "UPDATE contacts SET name = ?, email = ? WHERE id = ?";
        String sqlId = "SELECT id, name, email FROM contacts WHERE id = ?";
        String sqlName = "SELECT id, name, email FROM contacts WHERE name = ?";

        try {
            Contact oldContact = jdbcTemplate.queryForObject(sqlName, contactRowMapper, contact.getName());
            System.out.println(oldContact);
            if (oldContact != null) {
                System.out.println("The contact with this name exist");
                return null;
            }
        } catch (DataAccessException e) {
            System.out.println("The contact with this name not exist");
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, String.valueOf(id));
            return ps;
        });
        return jdbcTemplate.queryForObject(sqlId, contactRowMapper, id);
    }

    @Override
    public boolean deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, String.valueOf(id));

                return ps;
            });
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
