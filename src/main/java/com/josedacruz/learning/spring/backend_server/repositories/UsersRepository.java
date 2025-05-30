package com.josedacruz.learning.spring.backend_server.repositories;

import com.josedacruz.learning.spring.backend_server.domain.User;
import com.josedacruz.learning.spring.backend_server.security.PasswordResetToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
@Lazy
public class UsersRepository {

    private static final Logger logger = LoggerFactory.getLogger(UsersRepository.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UsersRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        logger.info("UsersRepository initialized");
    }

    public List<User> getUsers() {
        String sql = "select id,username,password,name,email, department from users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("department")
        ));
    }

    public Optional<User> getUserById(int id) {
        String sql = "select id,username,password,name,email, department from users where id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        // using a list - educational purpose (check the query vs queryForObject)
        // List<User> users = jdbcTemplate.query(sql, userRowMapper(), id);
        // return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    public Optional<User> getUserByUsername(String username) {
        String sql = "select id,username,password,name,email, department from users where username = :username";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, params, userRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int countUsers() {
        String sql = "select count(*) from users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Map<String, List<String>> getUsersByDepartment() {
        String sql = "select department, username from users";
        Map<String, List<String>> depsUsers = jdbcTemplate.query(sql, rs -> {
            Map<String, List<String>> map = new HashMap<>();
            while (rs.next()) {
                String department = rs.getString("department");
                String username = rs.getString("username");
                map.computeIfAbsent(department, k -> new ArrayList<>()).add(username);
            }
            return map;
        });

        return depsUsers;
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("department")
        );
    }

    public List<User> searchUsersFromDepartments(String department, String domain) {
        String sql = "select id,username,password,name,email, department from users where department = ? and email like ?";
        return jdbcTemplate.query(sql, userRowMapper(), department, domain);
    }

    public User save(User user) {

        String sql = "insert into users (username,password,name,email,department) values (?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getDepartment());
            return ps;
        }, keyHolder);

        int id = keyHolder.getKey().intValue();
        user.setId(id);

        return user;
    }

    public User update(User user) {
        String sql = "update users set username = :username, password = :password, name = :name, email = :email, department = :department where id = :id";

        int rows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(user));
        if (rows == 0) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public void delete(User user) {
        String sql = "delete from users where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        int rows = namedParameterJdbcTemplate.update(sql, params);
        if (rows == 0) {
            throw new RuntimeException("User not found");
        }
    }

    public void saveResetToken(PasswordResetToken token) {
        String sql = "insert into password_reset_tokens (token, user_email, expiration) values (?, ?, ?)";
        jdbcTemplate.update(sql, token.getToken(), token.getEmail(), token.getExpiration());
    }

    public List<String> getPasswordTokens() {
        String sql = "select token||';'||user_email||';'||FORMATDATETIME(expiration,'yyyy-MM-dd HH:mm:ss') from password_reset_tokens";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString(1));
    }

}
