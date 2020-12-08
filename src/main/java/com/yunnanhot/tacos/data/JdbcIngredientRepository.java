package com.yunnanhot.tacos.data;

import com.yunnanhot.tacos.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;



@Repository
//@Repository是一个构造形注解，与之类似的注解还有@Controller and @Component.
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
//        多行查询
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }




    @Override
    public Ingredient findOne(String id) {
//        单行
        /**
         * JdbcTemplate 的 queryForObject 方法签名如下
         * public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
         * 其中RowMapper<T> 是一个函数接口，唯一抽象方法声明如下
         * T mapRow(ResultSet rs, int rowNum) throws SQLException;
         * 用来定义将结果结合中的每行数据，映射到一个对象
         * */

        return jdbc.queryForObject(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient,
                id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
//        插入一条数据
        jdbc.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
            throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }
}
