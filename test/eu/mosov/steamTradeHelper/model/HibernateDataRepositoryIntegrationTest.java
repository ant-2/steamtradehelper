package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.client.BackpackApiClient;
import eu.mosov.steamtradehelper.model.entity.Item;
import eu.mosov.steamtradehelper.model.entity.Quality;
import eu.mosov.steamtradehelper.model.entity.parser.ItemParser;
import eu.mosov.steamtradehelper.model.entity.parser.Parser;
import org.hibernate.dialect.H2Dialect;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HibernateDataRepositoryIntegrationTest {
  DataSource dataSource;
  HibernateDataRepository repo = new HibernateDataRepository();
  Parser<Item> parser = new ItemParser<>();
  BackpackApiClient client = new BackpackApiClient();

  @Before
  public void setUp() throws Exception {
    System.setProperty("log4j.configurationFile", "config\\log4j2-test.xml");

    dataSource = new DriverManagerDataSource("jdbc:h2:mem:hibernate;DB_CLOSE_DELAY=-1", "sa", "sa");
    System.setProperty("hibernate.dialect", H2Dialect.class.getName());
    System.setProperty("hibernate.hbm2ddl.auto", "create-drop");

    AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setAnnotatedClasses(Item.class, Quality.class);
    sessionFactory.afterPropertiesSet();

    repo.hibernate = new HibernateTemplate(sessionFactory.getObject());
  }

  @Test
  public void itemFieldsAreCorrectMappedToDbColumns() throws Exception {
    Connection conn = dataSource.getConnection();
    conn.createStatement().execute("insert into Quality values('Unique', 6);");
    conn.createStatement().execute("insert into Item(id, name, description) values (0, 'metal', 'description')");
    conn.createStatement().execute("insert into item_quality values(0, 'Unique')");

    ResultSet rs = conn.createStatement().executeQuery("select * from item_quality");
    int rowsCounter = 0;
    while (rs.next()) {
      rowsCounter++;
      assertThat(rs.getInt("item_id"), is(0));
      assertThat(rs.getString("quality_quality"), is("Unique"));
    }
    assertThat(rowsCounter, is(1));
  }

  @Test
  public void loading() throws Exception {
    Connection conn = dataSource.getConnection();
    conn.createStatement().execute("insert into Quality values('Unique', 6);");
    conn.createStatement().execute("insert into Item(id, name, description) values (0, 'metal', 'description')");
    conn.createStatement().execute("insert into item_quality values(0, 'Unique')");

    List<Item> list = repo.getAllItems();
    assertTrue(list.size() == 1);

    Item item = list.get(0);
    assertThat(item.name, is("metal"));
    assertThat(item.description, is("description"));
    assertTrue(item.qualities.contains(new Quality("Unique")));
  }

  @Test
  public void saveAndLoadWorksFine() throws Exception {
    dataSource.getConnection().createStatement().execute("insert into Quality values ('Unique', 6)");
    Item item = new Item(e -> {
      e.name = "metal";
      e.description = "description";
      e.qualities.add(new Quality("Unique"));
    });

    repo.saveOrUpdate(item);

    List<Item> fromDB = repo.getAllItems();
    assertThat(fromDB.size(), is(1));
    Item itemFromDb = fromDB.get(0);
    assertThat(itemFromDb.name, is("metal"));
    assertThat(itemFromDb.description, is("description"));
    assertThat(itemFromDb.qualities.size(), is(1));
    assertThat(itemFromDb.qualities.contains(new Quality("Unique")), is(true));
  }

  @Test // todo хайбернейт не понимает что происходит сохранение одного и того же предмета
  public void saveDoesntInsertsDataMoreThanOneTime() throws SQLException {
    dataSource.getConnection().createStatement().execute("insert into Quality values ('Unique', 6)");

    Item item0 = new Item(e -> {
      e.name = "metal";
      e.description = "description";
      e.qualities.add(new Quality("Unique"));
    });

    Item item1 = new Item(e -> {
      e.name = "metal";
      e.description = "description";
      e.qualities.add(new Quality("Unique"));
    });

    repo.saveOrUpdate(item0);
    repo.saveOrUpdate(item1);

    List<Item> fromDB = repo.getAllItems();
    assertThat(fromDB.size(), is(1));

    Item itemFromDb = fromDB.get(0);
    assertThat(itemFromDb.name, is("metal"));
    assertThat(itemFromDb.description, is("description"));
    assertThat(itemFromDb.qualities.size(), is(1));
    assertThat(itemFromDb.qualities.contains(new Quality("Unique")), is(true));
  }
}