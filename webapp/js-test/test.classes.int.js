describe('prices.js', function () {

  describe('Integration test of prices parsing', function () {
    var prices, parser;
    prices = new BackpacktfDataSupplier().getPrices();
    parser = new PricesApiParser(prices);

    it("Parsed items are in valid state", function () {
      var items = parser.parseAllItems();
      var arr = items.property('A Distinctive Lack of Hue');

      for (var i = 0; i < arr.length; i++) {
        var item = arr[i];
        var msg = 'one of the returned Items are not in valid state. arr[i]: '+i+', price name: '+arr[i].name();
        assert(item.name != undefined);
        assert(item.quality() != undefined);
        assert(item.tradable() != undefined);
        assert(item.craftable() != undefined);
        assert(item.price() != undefined);
      }
    })
  });
});