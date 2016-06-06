// Constructor for a collection
function Collection() {}
Collection.prototype.addProperty = function (key, value) {
  this[key] = value;
};
Collection.prototype.removeProperty = function (key) {
  delete this[key];
};
Collection.prototype.getProperty = function (key) {
  return this[key];
};
Collection.prototype.hasProperty = function (key, value) {
  if (!this.hasOwnProperty(key)) return false;
  return (this[key] === value);
};

// Constructor for Item objects
function Item() {}
// Item.prototype = Object.create(Collection.prototype);
Item.prototype = new Collection();
Item.prototype.constructor = Item;

/**
 * Data holder and data extractor for backpack.tf prices API
 * @param {object} prices, an object from backpack.tf API response
 * */
function PricesApiParser(prices) {
  var items = prices["response"]["items"];
  var qualityConverter = qualityIdToqualityName();
  var self = this;

  /**
   * @param {string} name of an item
   * @return boolean
   * */
  this.hasItemWithName = function (name) {
    return items.hasOwnProperty(name);
  };

  /**
   * @return [string]
   * */
  this.getItemsNames = function () {
    return Object.keys(items);
  };

  /**
   * @param {string} itemName
   * @return [Item] for an specific item or {null} if there is no such item
   * */
  this.getItemsWithName = function (itemName) {
    if (!items.hasOwnProperty(itemName))  return null;

    var qualities, qualityID, resultArr, tempArr;
    resultArr = [];

    qualities = getItemQualities(items[itemName]);
    for (qualityID in qualities) {
      if (!qualities.hasOwnProperty(qualityID)) continue;
      tempArr = getPricesForQuality(qualities[qualityID]);
      //adds item name and qualityID to all items
      for (var i = 0; i < tempArr.length; i++) {
        tempArr[i].addProperty("name", itemName);
        tempArr[i].addProperty("qualityID", qualityID);
        tempArr[i].addProperty("qualityName", qualityConverter(qualityID));
      }
      resultArr = resultArr.concat(tempArr);
    }

    return resultArr;
  };

  /**
   * @return {Collection} contains {Item} objects
   * */
  this.getAllItems = function () {
    var item, tempArr, resultObj = new Collection();
    for (item in items) {
      if (!items.hasOwnProperty(item))    continue;
      tempArr = this.getItemsWithName(item);
      resultObj.addProperty(item, tempArr);
    }

    return resultObj;
  };

  /* private util functions */
  /**
   * @param {object} item
   * @return {object} containing qualities for the given item
   * */
  function getItemQualities(item) {
    return item["prices"];
  }

  /**
   * @param {string} itemName
   * @return [string]
   * */
  function getQualities(itemName) {
    return items[itemName]["prices"];
  }

  /**
   * @param {string} itemName
   * @return [string] containing qualities names for a specific item
   * */
  function getQualitiesNames(itemName) {

    var qualities = getQualities(itemName);
    var qualitiesIDs = Object.keys(qualities);

    var i, arr = [];
    for (i = 0; i < qualitiesIDs.length; i++) {
      arr.push(qualityIdToqualityName(qualitiesIDs[i]));
    }

    return arr;
  }

  /**
   * @param {object} quality object
   * @return [Item] for a specific quality object
   * */
  function getPricesForQuality(quality) {
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
            item.addProperty("currency", currencyName);
            item.addProperty("value", currencyValue);
            item.addProperty("priceID", priceID);
            item.addProperty("tradable", tradeSt);
            item.addProperty("craftable", craftSt);

            resultArr.push(item);
          }
        }
      }
    } catch (e) {
      throw new Error("Error during parsing a price object. " + getErrorInformation(e));
    }

    return resultArr;
  }

  function qualityIdToqualityName() {
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

/*===============================================*/
function QueryMaker() {
  var self = this;
}

QueryMaker.prototype.getItemsWithName = function (name) {

};

QueryMaker.prototype.getWithQuality = function (quality) {

};