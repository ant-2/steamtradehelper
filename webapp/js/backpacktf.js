// Item object
function Item(name) {
  this._name = name;
  this._qualityID = undefined;
  this._tradable = undefined;
  this._craftable = undefined;
  this._price = undefined;
}
Item.prototype = {};
Item.prototype.constructor = Item;
// Item getters & setters
Item.prototype.name = function (name) {
  if (!arguments.length)  return this._name;

  if (typeof name !== 'string') throw new Error('Item name should be a string.');
  if (name.length == 0) throw new Error('Item name should not has zero length.');
  this._name = name;
};
Item.prototype.quality = function (qualityID) {
  if (!arguments.length)  return this._qualityID;

  if (typeof qualityID !== 'number') throw new Error('Item.qualityID must be a number.');
  this._qualityID = qualityID;
};
Item.prototype.tradable = function (tradable) {
  if (!arguments.length)  return this._tradable;

  var type = typeof tradable;
  if (type !== 'boolean') throw new Error('Item.tradable must be a boolean value.');
  this._tradable = tradable;
};
Item.prototype.craftable = function (craftable) {
  if (!arguments.length)  return this._craftable;

  var type = typeof craftable;
  if (type !== 'boolean') throw new Error('Item.craftable must be a boolean value.');
  this._craftable = craftable;
};
Item.prototype.price = function (price) {
  if (!arguments.length)  return this._price;

  var type = typeof price;
  if (type !== 'object') throw new Error('Item.price must be an instance of Price type. But now it type of: ' + type);
  this._price = price;
};
// Item methods
Item.prototype.getBackpackUri = function () {
  var baseuri = 'http://backpack.tf/stats/', uri;
  // http://backpack.tf/stats/Unique/Anger/Tradable/Craftable/0
  uri = baseuri + this.getQualityName() + '/' + this.name() + '/' + this.getTradeStatus() + '/' + this.getCraftStatus() + '/' + this.price().id();
  return uri.toLowerCase();
};
Item.prototype.getQualityName = function () {
  var possibleQualities = {
    0: "normal",
    1: "rarity1",
    2: "rarity2",
    3: "vintage",
    4: "rarity3",
    5: "unusual",
    6: "unique",
    7: "community",
    8: "developer",
    9: "self-made",
    10: "customized",
    11: "strange",
    12: "completed",
    13: "haunted",
    14: "collector's",
    15: "paint kit weapon"
  };

  //todo оптимизировать getQualityName
  return possibleQualities[this.quality()];
};
Item.prototype.getTradeStatus = function () {
  return (this.tradable()) ? 'Tradable' : 'Non-Tradable';
};
Item.prototype.getCraftStatus = function () {
  return (this.tradable()) ? 'Craftable' : 'Non-Craftable';
};

// Price object
function Price(id, currency, value) {
  this._id = id;  // number
  this._currency = currency; // string
  this._value = value; // number
}
Price.prototype = {};
Price.prototype.constructor = Price;
Price.prototype.id = function (id) {
  if (!arguments.length)  return this._id;
  if (typeof id !== 'number') throw new Error('priceID should be a number.');
  this._id = id;
};
Price.prototype.currency = function (currency) {
  if (!arguments.length)  return this._currency;

  if (typeof currency !== 'string') throw new Error('Price currency field should be a string.');
  this._currency = currency;
};
Price.prototype.value = function (value) {
  if (!arguments.length)  return this._value;

  if (typeof value !== 'number') throw new Error('Price value should be a number.');
  this._value = value;
};

function RestClient() {
  "use strict";
  var dataLoader = new UtilsData();

  /**
   * @returns {items} object from the server
   * */
  this.loadResource = function(uri) {
    var json, result;
    try {
      json = dataLoader.loadResource(uri);
    } catch (e) {
      throw new Error("Can't get items object from server. "+e.name+": "+e.message);
    }
    try {
      result = JSON.parse(json);
    } catch (e) {
      throw new Error("Can't parse items object. "+e.name+": "+e.message)
    }
    return result;
  }
}

/**
 * Data holder and data extractor for backpack.tf items API
 * @param {object} data, an object from backpack.tf API response
 * */
function PricesApi(data) {
  var items = data["response"]["items"];

  /**
   * @return {Collection} contains {itemName: {Item}} objects
   * */
  this.getItems = function() {
    return parseItems(items)
  };

  function parseItems(items) {
    var item, arr, i, col = new Collection();
    for (item in items) {
      if (!items.hasOwnProperty(item))    continue;
      arr = parseItem(items[item]);
      for (i = 0; i < arr.length; i++) {
        arr[i].name(item);
      }
      col.property(item, arr);
    }

    return col;
  }

  /**
   * @param {string} item
   * @return [Item] for an specific price or {null} if there is no such price
   * */
  function parseItem(item) {
    var resultArr = [], qualities, qualityID, tempArr;

    try {
      qualities = getQualitiesForItem(item);
      for (qualityID in qualities) {
        if (!qualities.hasOwnProperty(qualityID)) continue;
        tempArr = parsePricesForQuality(qualities[qualityID]);
        //adds price name and qualityID to all items
        for (var i = 0; i < tempArr.length; i++) {
          tempArr[i].quality(qualityID * 1);  // converts string to number
        }
        resultArr = resultArr.concat(tempArr);
      }
    } catch (e) {
      throw new Error("In PricesApi.parseItem(). " + e.name + ": " + e.message);
    }
    return resultArr;
  }

  /**
   * @param {object} quality object
   * @return [Item] for a specific quality object
   * */
  function parsePricesForQuality(quality) {
    var resultArr = [], tradeSt, craftSt, priceID;

    try {
      for (tradeSt in quality) {
        if (!quality.hasOwnProperty(tradeSt))   continue;

        for (craftSt in quality[tradeSt]) {
          if (!quality[tradeSt].hasOwnProperty(craftSt))   continue;

          for (priceID in quality[tradeSt][craftSt]) {
            if (!quality[tradeSt][craftSt].hasOwnProperty(priceID))   continue;

            var currencyName = quality[tradeSt][craftSt][priceID]["currency"];
            var currencyValue = quality[tradeSt][craftSt][priceID]["value"];

            var item = new Item();
            item.tradable((tradeSt == 'Tradable'));
            item.craftable((craftSt == 'Craftable'));
            item.price(new Price(priceID * 1, currencyName, currencyValue));
            resultArr.push(item);
          }
        }
      }
    } catch (e) {
      throw new Error("Error during parsing a items for Item quality object. " + e.name + ": " + e.message);
    }

    return resultArr;
  }

  /**
   * @param {object} item
   * @return {object} containing qualities for the given price
   * */
  function getQualitiesForItem(item) {
    return item["prices"];
  }

  function convertQualityIdToQualityName() {
    var possibleQualities = {
      0: "normal",
      1: "rarity1",
      2: "rarity2",
      3: "vintage",
      4: "rarity3",
      5: "unusual",
      6: "unique",
      7: "community",
      8: "developer",
      9: "self-made",
      10: "customized",
      11: "strange",
      12: "completed",
      13: "haunted",
      14: "collector's",
      15: "paint kit weapon"
    };

    return function (qualityID) {
      if (typeof qualityID != "number" && typeof qualityID != "string") {
        throw new TypeError("Quality object is not a number or a string, check the object that you parse, may be it is not a valid quality.");
      }

      return possibleQualities[qualityID];
    }
  }
}

function BackpacktfApi() {
  var
      PRICES_URI = '/rest/raw/prices',
      restClient = new RestClient(new UtilsData()),
      prices, pricesParsed;

  this.items = function(isNeedUpdate) {
    if(isNeedUpdate || !prices) {
      prices = load(PRICES_URI);
      pricesParsed = new PricesApi.getItems(prices)
    }
    return pricesParsed;
  };

  function load(uri) {
    return restClient.loadResource(uri);
  }
}