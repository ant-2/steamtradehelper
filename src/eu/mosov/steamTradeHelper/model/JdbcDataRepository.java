package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.model.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcDataRepository implements DataRepository<Item> {
  @Autowired DataSource dataSource;

  @Override
  public List<Item> loadAll() {
    Connection conn = null;
    List<Item> list = new ArrayList<>();

    try {
      conn = dataSource.getConnection();
      ResultSet rs = conn.createStatement().executeQuery("select * from Item");
      while (rs.next()) {
        list.add(new Item(rs.getString("name")));
      }
    } catch (SQLException e) {
      throw new RuntimeException("Couldn't load items from database.", e);
    }

    return list;
  }
}
