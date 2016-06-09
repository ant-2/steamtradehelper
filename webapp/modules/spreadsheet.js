function Spreadsheet(options) {
  var spreadsheetDiv, rows = {} /*{item name: {item}}*/;

  this.getElem = getElem;
  this.filterColback = filterByItemName;

  function getElem() {
    if (!spreadsheetDiv)  render();
    return spreadsheetDiv;
  }

  function filterByItemName(inputDiv) {
    function selectRowsToFilter(name) {
      var arr = [], str = name.trim().toLowerCase();
      for (var row in rows) {
        if (!rows.hasOwnProperty(row))  continue;
        if (row.indexOf(str) == -1) {
          arr.push(row);
        }
      }
      return arr;
    }

    function toggleRowsVisibility(arr) {
      for (var i = 0; i < arr.length; i++) {
        rows[arr[i]].classList.toggle('sh__row_hidden');
      }
    }

    return function() {
      toggleRowsVisibility(selectRowsToFilter(inputDiv.value));
    };
  }

  // render functions
  function render() {
    spreadsheetDiv = document.createElement('div');
    spreadsheetDiv.classList.add(options.styles.container);
    renderRows(options.items);
  }

  function renderRows(items) {
    var item;
    for (item in items) {
      if (!items.hasOwnProperty(item))  continue;
      spreadsheetDiv.appendChild(renderRow(item, items[item]));
    }
  }

  function renderRow(name, item) {
    var row = document.createElement('div');
    row.classList.add(options.styles.row);
    var title = templates.title(name);
    var prices = templates.price(item);

    row.insertAdjacentHTML('afterbegin', title);
    row.insertAdjacentHTML('beforeend', prices);

    // saves pointer to row for make filter by item name in future
    rows[name.toLowerCase()] = row;

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
