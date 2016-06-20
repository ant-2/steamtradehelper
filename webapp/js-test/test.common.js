describe('common.js', function () {
  describe('Класс Collection', function () {
    it('Set new property', function () {
      var col = new Collection();
      col.property('key', 'value');
      assert(col['key'] != undefined);
      assert(col['key'] == 'value');
    });

    it('Get a property value', function () {
      var col = new Collection();
      col.property('key', 'value');
      var value = col.property('key');
      assert(value != undefined);
      assert(value == 'value');
    });

    it('Delete a property', function () {
      var col = new Collection();
      col.property('key', 'value');
      col.deleteProperty('key');
      assert(col['key'] == undefined);
    });
  });

  describe('Класс RestClient', function () {
    it('loadResource()', function () {
      var client = new RestClient();
      var prices = client.loadResource('/rest/raw/prices');

      assert(prices != undefined);
    });
  });
});