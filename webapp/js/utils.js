function getErrorInformation(e) {
    var result = e.name + ": " + e.message;
    if (typeof e.fileName != "undefined")   result = result + " file name: " + e.fileName;
    if (typeof e.lineNumber != "undefined")   result = result + ", line number: " + e.lineNumber;
    return result;
}
