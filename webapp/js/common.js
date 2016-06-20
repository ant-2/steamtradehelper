$(function () {
    $.ajaxSetup({
        error: function (req) {
            if (req.status == 0) return;
            console.log(req);
            alert('Failed: ' + req.status + ' ' + req.statusText + (req.responseText && req.responseText.length < 200 ? ': ' + req.responseText : ''));
        }
    });
});

function UtilsData() {
  this.loadResource = function (resourceUrl) {
    var xhr = new XMLHttpRequest();
    xhr.open('get', resourceUrl, false);
    xhr.send();
    
    if (xhr.status != 200) {
      console.log("Can't get resource: " + resourceUrl + ". " + xhr.status + ": " + xhr.statusText);
    } else {
      return xhr.responseText;
    }
  };
  
  //doesn't work //todo fix async loadResource()
  this.loadResourceAsync = function (resourceUrl) {
    var xhr = new XMLHttpRequest();
    xhr.open('get', resourceUrl, true);
    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        if (xhr.status != 200) {
          console.log("Can't get resource: " + resourceUrl + ". " + xhr.status + ": " + xhr.statusText);
        } else {
          return xhr.responseText;
        }
      }
    };
    xhr.send();
  }
}

function UtilsCollection() {
  // Filters array of entries by given string
// ignores letter case
  this.filterArrayBySubstring= function(arr, string) {
    var matches = [];
    for (var i = 0; i < arr.length; i++) {
      if (~arr[i]
              .name.toLowerCase()
              .indexOf(string.toLowerCase())) {
        matches.push(arr[i]);
      }
    }
    return matches;
  }
}

function UtilsError() {
  this.getErrorInformation =function(e) {
    var result = e.name + ": " + e.message;
    if (typeof e.fileName != "undefined")   result = result + " file name: " + e.fileName;
    if (typeof e.lineNumber != "undefined")   result = result + ", line number: " + e.lineNumber;
    return result;
  }
}

// Collection
function Collection() {}
Collection.prototype.property = function (key, value) {
  if (value == undefined) {
    return this[key];
  } else {
    this[key] = value;
  }
};
Collection.prototype.deleteProperty = function (key) {
  delete  this[key];
};
Collection.prototype.hasProperty = function (key, value) {
  if (!this.hasOwnProperty(key)) return false;
  return (this[key] === value);
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