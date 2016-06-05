describe('data.js', function () {

  var dataSupplier = new BackpacktfDataSupplier();

  describe('Интеграционный тест на доступ к данным с сервера', function () {
    it('getPrices()', function () {
      var prices = dataSupplier.getPrices();
      assert(prices !== undefined && prices !== null);
    });
  });
});