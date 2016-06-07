function Spreadsheet(options) {
  var spreadsheet, row_cssclass;
  row_cssclass = 'sh-row';

  this.getElem = getElem;
  this.filterColback = filter();

  function getElem() {
    if (!spreadsheet)  render();
    return spreadsheet;
  }

  function render() {
    spreadsheet = document.createElement('div');
    spreadsheet.classList.add(options.containerClass);

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
    row.classList.add(row_cssclass);
    var title = renderTitle(name);
    var prices = renderPrices(item);

    row.insertAdjacentHTML('afterbegin', title);
    row.insertAdjacentHTML('beforeend', prices);

    return row;
  }

  function renderTitle(name) {
    return options.titleTmpl({
      itemName: name
    });
  }

  function renderPrices(enrty) {
    return options.pricesTmpl({
      item: enrty
    });
  }

  function filter() {
    function toggleRowVisibility(row) {
      if (row.classList.contains('hidden')) {
        row.classList.remove('hidden');
      } else {
        row.classList.add('hidden');
      }
    }

    return function() {
      var rows = spreadsheet.getElementsByClassName(row_cssclass);
      for (var i = 0; i < rows.length; i++) {
        toggleRowVisibility(rows[i]);
      }
    };
  }
}