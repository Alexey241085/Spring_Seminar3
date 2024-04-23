package ru.gb.example3_sem3_hometask.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.example3_sem3_hometask.domain.User;

import java.util.List;

@Repository
public class DbRepositiry {

    private final JdbcTemplate jdbc;

    public DbRepositiry(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setName(r.getString("name"));
            rowObject.setAge(r.getInt("age"));
            rowObject.setName(r.getString("email"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (name, age, email) VALUES ( ?, ?, ?)";
        jdbc.update(sql, user.getName(), user.getAge(), user.getEmail());
        return  user;
    }

    public void deleteById(int id){
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }


    public void update1(int id, User user){
        String sql = "UPDATE userTable SET name=?, age=?, email=? WHERE id=?";
        jdbc.update(sql, user.getName(), user.getAge(), user.getEmail(), id);
    }

}
