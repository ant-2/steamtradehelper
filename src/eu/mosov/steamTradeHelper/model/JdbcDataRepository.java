package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

public class JdbcDataRepository implements DataRepository {
	@Autowired DataSource dataSource;

	@Override
	public List<Item> getCurrencies() {
		return null;
	}

	@Override
	public List<Item> getPrices() {
		return null;
	}
}
