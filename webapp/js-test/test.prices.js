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
    
    it("All items are parsed", function () {
      var items = parser.getAllItems();
      assert(items.size() === 1, 'Size: '+items.size());
      var arr = items.getProperty('A Distinctive Lack of Hue');
      assert(arr.length === 2, 'items array length are not match with expected');
    });

    it("Parsed items are in valid state, all properties are filled", function () {
      var items = parser.getAllItems();
      var arr = items.getProperty('A Distinctive Lack of Hue');

      for (var i = 0; i < arr.length; i++) {
        var item = arr[i];
        var msg = 'one of the returned Items are not in valid state. arr[i]: '+i+', item name: '+arr[i].name();
        assert(item.name != undefined);
        assert(item.quality() != undefined);
        assert(item.tradable() != undefined);
        assert(item.craftable() != undefined);
        assert(item.price() != undefined);
      }
    })
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
      assert(typeof uri === 'string', 'Generated uri is not type of string. Current type is: '+typeof uri);
      assert(uri.toLowerCase() == expected, 'Returned URI doesn\'t match expected URI. Current URI: '+uri+'. Expected: '+expected);
    })
  })
});