describe("main.js", function () {

  describe("Парсинг свойств предмета", function () {

    var items = {
      "A Brush with Death":{
        "prices":{
          "6":{
            "Tradable":{
              "Craftable":{
                "0":{
                  "currency":"metal",
                  "value":4.33,
                  "last_update":1464820277,
                  "difference":-0.33
                }
              }
            }
          },
          "14":{
            "Tradable":{
              "Craftable":{
                "0":{
                  "currency":"keys",
                  "value":100,
                  "last_update":1459344639,
                  "difference":1800
                }
              }
            }
          }
        }
      }
    };

    it("getQualitiesNames({item})", function () {
      let qualities = getQualitiesNames(items["A Brush with Death"]);
      assert(qualities[6] == "unique");
      assert(qualities[14] == "collector's");
    });

    it("getPricesForQuality({quality})", function () {
      let prices = getPricesForQuality(items["A Brush with Death"]["prices"]["6"]);
      assert(prices.length == 1);
    });
  });

  describe("Методы объекта PriceParser", function () {

    var items = {
      "A Brush with Death":{
        "prices":{
          "6":{
            "Tradable":{
              "Craftable":{
                "0":{
                  "currency":"metal",
                  "value":4.33,
                  "last_update":1464820277,
                  "difference":-0.33
                }
              }
            }
          },
          "14":{
            "Tradable":{
              "Craftable":{
                "0":{
                  "currency":"keys",
                  "value":100,
                  "last_update":1459344639,
                  "difference":1800
                }
              }
            }
          }
        }
      }
    };

    it("getQualitiesNames({item})", function () {
      let qualities = getQualitiesNames(items["A Brush with Death"]);
      assert(qualities[6] == "unique");
      assert(qualities[14] == "collector's");
    });

    it("getPricesForQuality({quality})", function () {
      let prices = getPricesForQuality(items["A Brush with Death"]["prices"]["6"]);
      assert(prices.length == 1);
    });
  });
});