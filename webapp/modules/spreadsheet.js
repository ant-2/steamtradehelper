function Spreadsheet(options) {
  var spreadsheetDiv, rows = {} /*{price name: {price}}*/;

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

    return function () {
      toggleRowsVisibility(selectRowsToFilter(inputDiv.value));
    };
  }

  // render functions
  function render() {
    spreadsheetDiv = document.createElement('article');
    spreadsheetDiv.classList.add(options.styles.container);
    renderRows(options.items);
  }

  function renderRows(items) {
    var item;
    try {
      for (item in items) {
        if (!items.hasOwnProperty(item))  continue;
        spreadsheetDiv.appendChild(renderRow(item, items[item]));
      }
    } catch (e) {
      console.log(e);
    }
  }

  function renderRow(name, items) {
    try {
      var row = document.createElement('section');
      row.classList.add(options.styles.row);
      var title = templates.title(name);
      row.insertAdjacentHTML('afterbegin', title);

      var cell;
      for (var i = 0; i < items.length; i++) {
        cell = templates.price(items[i]);
        row.insertAdjacentHTML('beforeEnd', cell);
      }

      // saves pointer to row for make filter by price name in future
      rows[name.toLowerCase()] = row;
      return row;
    } catch (e) {
      console.log(e);
    }
  }

  var templates = {
    title: function (name) {
      return options.rowTitle({itemName: name});
    },

    price: function (item) {
      return options.rowCell({uri: item.getBackpackUri(), value: item.price().value(), currency: item.price().currency()});
    }
  }
}

function SpreadsheetTmpl(styles) {
  this.rowTitle = '<div class="sh__row-cell sh__row-cell_title"><%-itemName%></div>';
  this.rowCell = '<div class="sh__row-cell "><a href="<%-uri%>"><%-value+" "+currency%></a></div>';

  function generateCell() {
    var div = document.createElement('div');
    div.classList.add(styles.cell);
    return div;
  }

  this.generateRowTitle = function(itemName) {
    var div = generateCell();
    div.classList.add(styles.title);
    div.innerHTML = itemName;
    return div;
  };

  this.generatePriceCell = function(itemName) {
    var div = document.createElement('div');
    div.classList.add(styles.cell);
    div.classList.add(styles.title);
    div.innerHTML = itemName;
    return div;
  }
}
