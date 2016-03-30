package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.client.BackpackApiClient;
import eu.mosov.steamTradeHelper.entity.Item;
import org.hibernate.dialect.H2Dialect;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HibernateDataRepositoryIntegrationTest {
	DataSource dataSource;
	HibernateDataRepository repo = new HibernateDataRepository();

	@Before
	public void setUp() throws Exception {
		dataSource = new DriverManagerDataSource("jdbc:h2:mem:hibernate;DB_CLOSE_DELAY=-1", "sa", "sa");

		System.setProperty("hibernate.dialect", H2Dialect.class.getName());
		System.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setAnnotatedClasses(Item.class);
		sessionFactory.afterPropertiesSet();

		repo.hibernate = new HibernateTemplate(sessionFactory.getObject());
		repo.client = new BackpackApiClient();

	}

	@Test
	public void updateDoesntInsertsDataMoreThanOneTime() {
		List<Item> currInitial = repo.updateCurrencies();

		List<Item> currUpdated = repo.updateCurrencies();
		assertThat(currUpdated.size(), is(4));
	}

	@Test
	public void hibernateMethodSaveOrUpdateCorrectlyInsertsDataInEmptyBase() {
		repo.updateCurrencies();

		List<Item> curr = repo.getCurrencies();
		assertThat(curr.size(), is(4));
	}
}