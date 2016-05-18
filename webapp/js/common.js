$(function () {
  $.ajaxSetup({
    error: function (req) {
      if (req.status == 0) return;
      console.log(req);
      alert('Failed: ' + req.status + ' ' + req.statusText + (req.responseText && req.responseText.length < 200 ? ': ' + req.responseText : ''));
    }
  });
});

function getResource(resourceUrl) {
  var xhr = new XMLHttpRequest();
  xhr.open('get', resourceUrl, false);
  xhr.send();

  if (xhr.status != 200) {
    console.log("Can't get resource: " + resourceUrl + ". " + xhr.status + ": " + xhr.statusText);
  } else {
    return xhr.responseText;
  }
}

function getResourceAsync(resourceUrl) {
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

// Filters array of entries by given string
// ignores letter case
function filterArrayBySubstring(arr, string) {
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