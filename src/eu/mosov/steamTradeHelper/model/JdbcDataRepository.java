package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.model.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

//todo написать реализацию методов
public class JdbcDataRepository implements DataRepository {
  @Autowired
  DataSource dataSource;

  @Override
  public Item saveOrUpdate(Item item) {
    Connection conn;
    try {
      conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("");
      while (rs.next()) {

      }
      return new Item("");
    } catch (SQLException e) {
      throw new RuntimeException("Couldn't execute SQL query", e);
    }
  }

  @Override
  public List<Item> saveOrUpdateAll(List<Item> list) {
    return null;
  }

  @Override
  public List<Item> getAllItems() {
    return null;
  }

  @Override
  public Item find(String itemName) {
    return null;
  }
}
