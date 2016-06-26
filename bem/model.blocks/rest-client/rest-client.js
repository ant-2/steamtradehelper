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