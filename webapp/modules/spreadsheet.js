function Spreadsheet(options) {
  var spreadsheet;

  this.getElem = getElem;
  this.filterColback = filter();

  function getElem() {
    if (!spreadsheet)  render();
    return spreadsheet;
  }

  function filter() {
    return function() {
      var rows = spreadsheet.getElementsByClassName(options.styles.row);
      for (var i = 0; i < rows.length; i++) {
        rows[i].classList.toggle('sh__row_hidden');
      }
    };
  }

  // render functions
  function render() {
    spreadsheet = document.createElement('div');
    spreadsheet.classList.add(options.styles.container);
    renderRows(options.items);
  }

  function renderRows(items) {
    var item;
    for (item in items) {
      if (!items.hasOwnProperty(item))  continue;
      spreadsheet.appendChild(renderRow(item, items[item]));
    }
  }

  function renderRow(name, item) {
    var row = document.createElement('div');
    row.classList.add(options.styles.row);
    var title = templates.title(name);
    var prices = templates.price(item);

    row.insertAdjacentHTML('afterbegin', title);
    row.insertAdjacentHTML('beforeend', prices);

    return row;
  }

  var templates = {
    title: function (name) {
      return options.title({
        itemName: name
      });
    },

    price: function (entry) {
      return options.price({
        item: entry
      });
    }
  };
}

function SpreadsheetTmpl() {
  this.rowTitle = '<div class="sh__row-title"><%-itemName%></div>';
  this.rowCell =
      '<% _.forIn(item, function(price) { %>\n' +
      '<div class="sh__row-cell"><%-price.value+" "+price.currency%></div>\n' +
      ' <%}) %>';
}
