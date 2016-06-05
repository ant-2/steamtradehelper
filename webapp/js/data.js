function BackpacktfDataSupplier() {
  /**
   * @returns {prices} object from the server
   * */
  this.getPrices = function() {
    var json, result, uri = "/rest/raw/prices";
    try {
      json = getResource(uri);
    } catch (e) {
      throw new Error("Can't get prices object from server. "+e.name+": "+e.message);
    }

    try {
      result = JSON.parse(json);
    } catch (e) {
      throw new Error("Can't parse prices object. "+e.name+": "+e.message)
    }
    return result;
  }
}

