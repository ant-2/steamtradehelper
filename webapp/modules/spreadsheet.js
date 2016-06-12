function Spreadsheet(options) {
  var spreadsheet, templates, rows = {} /*{price name: {price}}*/;
  templates = options.templates;

  this.getElem = getElem;
  this.filterColback = filterByItemName;

  function getElem() {
    if (!spreadsheet)  render();
    return spreadsheet;
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
    spreadsheet = templates.generateContainer();
    renderItems(options.items);
  }

  function renderItems(items) {
    var item;
    try {
      for (item in items) {
        if (!items.hasOwnProperty(item))  continue;
        spreadsheet.appendChild(renderRow(item, items[item]));
      }
    } catch (e) {
      console.log(e);
    }
  }

  function renderRow(name, items) {
    try {
      var row, i;
      row = templates.generateRow();
      row.appendChild(templates.generateRowTitle(name));

      for (i = 0; i < items.length; i++) {
        row.appendChild(templates.generatePriceCell(items[i]));
      }
      // saves pointer to row for make filter by price name in future
      rows[name.toLowerCase()] = row;
      return row;
    } catch (e) {
      console.log(e);
    }
  }
}

function getSpreadsheetStyles() {
  return {
    container: "sh",
    row: "sh__row",
    cell: "sh__row-cell",
    title: "sh__row-cell_title",
    price: "sh__row-cell_price"
  };
}

function Spreadsheet_tmpl(styles) {
  function generateCell() {
    var div = document.createElement('div');
    div.classList.add(styles.cell);
    return div;
  }

  this.generateContainer = function () {
    var container = document.createElement('article');
    container.classList.add(styles.container);
    return container;
  };

  this.generateRow = function () {
    var section = document.createElement('section');
    section.classList.add(styles.row);
    return section;
  };

  this.generateRowTitle = function(itemName) {
    var div = generateCell();
    div.classList.add(styles.title);
    div.innerHTML = itemName;
    return div;
  };

  this.generatePriceCell = function(item) {
    var price = item.price(), div = generateCell();
    div.classList.add(styles.price);
    div.innerHTML = price.value() + " " + price.currency();
    return div;
  }
}
