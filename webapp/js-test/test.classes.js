describe('prices.js', function () {

  describe('Parsing items from Backpack.tf API', function () {
    var prices = {
      "response": {
        "success": 1,
        "current_time": 1464927489,
        "raw_usd_value": 0.115,
        "usd_currency": "metal",
        "usd_currency_index": 5002,
        "items": {
          "A Distinctive Lack of Hue": {
            "defindex": [
              5040
            ],
            "prices": {
              "6": {
                "Tradable": {
                  "Craftable": {
                    "0": {
                      "currency": "keys",
                      "value": 1,
                      "last_update": 1455649142,
                      "difference": -1.933
                    }
                  },
                  "Non-Craftable": {
                    "0": {
                      "currency": "metal",
                      "value": 16.66,
                      "last_update": 1464371607,
                      "difference": -1.865
                    }
                  }
                }
              }
            }
          }
        }
      }
    };
    var parser = new PricesApiParser(prices);

    it("Parsed items are in valid state, all properties are filled", function () {
      var items = parser.parseAllItems();

      var arr = items.property('A Distinctive Lack of Hue');
      assert(arr.length == 2);

      var item = arr[0];
      assert(item.name() == 'A Distinctive Lack of Hue');
      assert(item.quality() === 6);
      assert(item.tradable() === true);
      assert(item.craftable() === true);

      var price = item.price();
      assert(price.id() === 0);
      assert(price.currency() === 'keys');
      assert(price.value() === 1);
    });

    describe('Методы класса Item', function () {
      var item = new Item('anger');
      item.quality(6);
      item.tradable(true);
      item.craftable(true);
      item.price(new Price(0, 'refined', 4.66));

      it('Генерация URI на Backpack.tf', function () {
        var expected = 'http://backpack.tf/stats/Unique/Anger/Tradable/Craftable/0'.toLowerCase();
        var uri = item.getBackpackUri();
        console.log(uri);
        assert(typeof uri === 'string', 'Generated uri is not type of string. Current type is: ' + typeof uri);
        assert(uri.toLowerCase() == expected, 'Returned URI doesn\'t match expected URI. Current URI: ' + uri + '. Expected: ' + expected);
      })
    });

    describe('Class Collection', function () {
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
    })
  });
});