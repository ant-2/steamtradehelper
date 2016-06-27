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