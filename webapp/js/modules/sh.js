function Spreadsheet(options) {
  var spreadsheet;

  this.getElem = getElem;

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
    var title = renderTitle(name);
    var prices = renderPrices(item);

    row.insertAdjacentHTML('afterbegin',title);
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
}