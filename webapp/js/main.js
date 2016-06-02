// Constructor for a collection
function Collection() {
    // this.properties = Object.create(null);
}

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

// Constructor for price objects
function Price(currency, value, priceID) {
    this.currency = currency;
    this.value = value;
    this.priceID = priceID;
}

// Price.prototype = Object.create(Collection.prototype);
Price.prototype = new Collection();
Price.prototype.constructor = Price;

/* Utility methods to parse backpack.tf prices API*/

/**
 * Data holder and data extractor for backpack.tf prices API
 * @param {object} prices, an object from backpack.tf API response
 * */
function PricesApiParser(prices) {
    this.prices = prices;
    var items = this.prices["response"]["items"];
    var self = this;

    this.setPricesObject = function (pricesObject) {
        this.prices = pricesObject;
        items = this.prices["response"]["items"];
    };

    /**
     * @param {string} name of an item
     * @return boolean
     * */
    this.hasItem = function (name) {
       return items.hasOwnProperty(name);
    };

    function checkForItemPresence(name) {
        if (!self.hasItem(name))    throw new ReferenceError("There is no item with name: "+name);
    }

    /**
     * @return [string]
     * */
    this.getItemsNames = function () {
        return Object.keys(items);
    };

    /**
     * @param {string} itemName
     * @return [string]
     * */
    this.getQualities = function (itemName) {
        checkForItemPresence(itemName);

        return items[itemName]["prices"];
    };

    /**
     * @param {string} itemName
     * @return [string] containing qualities names for a specific item
     * */
    this.getQualitiesNames = function (itemName) {

        var qualities = this.getQualities(itemName);
        var qualitiesIDs = Object.keys(qualities);
        var converter = fooConvertQualityIdToQualityName();

        var i, arr = [];
        for (i = 0; i < qualitiesIDs.length; i++) {
            arr.push(converter(qualitiesIDs[i]));
        }

        return arr;
    };

    /**
     * @param {string} itemName
     * @return [Price] for an specific item
     * */
    this.getItemPrices = function (itemName) {
        checkForItemPresence(itemName);

        var qualities, qualityID, arr, priceArr;
        arr = [];
        qualities = getItemQualities(items[itemName]);

        for (qualityID in qualities) {
            if (!qualities.hasOwnProperty(qualityID)) continue;
            priceArr = getPricesForQuality(qualities[qualityID]);
            for (var i = 0; i < priceArr.length; i++) {
                priceArr[i].addProperty("qualityID", qualityID);
            }
            arr = arr.concat(priceArr);
        }

        return arr;
    };

    this.getAllPrices = function () {
        var item, arr = [];
        for (item in items) {
            if (!items.hasOwnProperty(item))    continue;
            arr = arr.concat(this.getItemPrices(item));
        }

        return arr;
    }
}

/**
 * @param {object} quality object
 * @return [Price] for a specific quality object
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

                    var price = new Price(currencyName, currencyValue, priceID);
                    price.addProperty("tradable", tradeSt);
                    price.addProperty("craftable", craftSt);

                    resultArr.push(price);
                }
            }
        }
    } catch (e) {
        throw new Error("Error during parsing a price object. " + getErrorInformation(e));
    }

    return resultArr;
}

/**
 * @param {object} item object
 * @return {object} key - qualityID, value{string} - quality name
 * */
function getQualitiesNames(item) {
    var qualityID, prices, result, foo;
    foo = fooConvertQualityIdToQualityName();
    prices = item["prices"];
    result = {};

    for (qualityID in prices) {
        if (!prices.hasOwnProperty(qualityID))  continue;
        result[qualityID] = foo(qualityID);
    }

    return result;
}

/**
 * Returns function that converts a quality id to a quality name
 * @return {function(qualityID)} that converts a qualityID to a string quality name
 * */
function fooConvertQualityIdToQualityName() {
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

/**
 * @param {object} item
 * @return {object} containing qualities for the given item
 * */
function getItemQualities(item) {
    return item["prices"];
}