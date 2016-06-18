function Spreadsheet(options) {
  var spreadsheet, rows, items, styles;
  items = options.items;/*[item]*/
  styles = options.styles;/*{SpreadsheetStyles}*/
  rows = [];/*[row]*/

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
    try {
      spreadsheet = document.createElement('section');
      appendRows(items);
    } catch (e) {
      console.log(e);
    }
  }

  function appendRows(items) {
    var item, row;
    for (item in items) {
      if (!items.hasOwnProperty(item))  continue;
      row = new Row({
        title: item,
        items: items[item],
        styles: styles.row
      }).getElem();
      rows.push(row);
      spreadsheet.appendChild(row);
    }
  }
}

function Row(title, items, styles) {
  var element = create(title, items, styles);

  function create(title, items, styles) {
    var elem = new Element('article', styles);

    return elem;
  }
}

function Price(item, styles) {
  var element = create(item, styles);

  function create(item, styles) {
    var elem = new Element('div', styles);
    elem.innerText = content(item);
    return elem;
  }

  function content(item) {
    var price = item.price();
    return price.value()+" "+price.currency();
  }
}

function Element(type, styles) {
  var element = create();
  this.addStyles = addStyles;
  this.toggleStyles = toggleStyles;
  this.appendTo = appendTo;
  this.appendChild = appendChild;
  this.innerText = innerText;

  function create() {
    var elem = document.createElement(type);
    if (!styles) {
      addStyles(styles);
    }
    return elem;
  }

  function appendTo(parrent) {
    parrent.appendChild(element);
  }

  function appendChild(child) {
    element.appendChild(child);
  }

  function addStyles(styles) {
    var i;
    try {
      for (i = 0; i < styles.length; i++) {
        element.classList.add(styles[i]);
      }
    } catch (e) {
      console.log(e);
    }
  }

  function toggleStyles(styles) {
    var i;
    for (i = 0; i < styles.length; i++) {
      element.classList.toggle(styles[i]);
    }
  }

  function innerText(text) {
    element.innerText = text;
  }
}

function SpreadsheetStyles() {
  var styles = {
    container: "b-sh",
    row: "b-sh__row",
    cell: "b-sh__row-cell",
    title: "b-sh__row-cell_title",
    price: "b-sh__row-cell_price"
  };

  this.getStyles = function () {
    return styles;
  }
}

function SpreadsheetElements(template, data) {

}