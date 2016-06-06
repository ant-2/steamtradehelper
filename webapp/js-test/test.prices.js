describe('prices.js', function () {

  describe('Методы объекта PriceParser', function () {

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

    it('getItemsNames()', function () {
      var names = parser.getItemsNames();
      assert(names !== undefined && names !== null);
      assert(names.length == 1);
    });

    it('getItemsWithName({string} name)', function () {
      var items = parser.getItemsWithName('A Distinctive Lack of Hue');
      assert(items !== undefined && items !== null);
      assert(items.length == 2);
    });

    it('getAllItems()', function () {
      var items = parser.getAllItems();
      assert(items !== undefined && items !== null);
      assert(items.hasOwnProperty('A Distinctive Lack of Hue'));
    });
  });
});