var convertor = require('html2bemjson'),
    file = ('pages/spreadsheet.html'),
    fs = require('fs');

(function (data) {
    fs.writeFileSync('converted.bemjson.js', convertor.stringify(data));
})(fs.readFileSync(file));