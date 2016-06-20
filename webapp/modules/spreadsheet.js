function Spreadsheet(items, template) {
  "use strict";
  var html, _items = items;

  this.html = getHtml;

  function getHtml() {
    if (!html) {html = renderItems(_items)}
    return html;
  }

  function renderItems(items) {
    return template.render(items);
  }
}

function Template(template, context) {
  'use strict';
  var compiled = _.template(template,  {variable: context});

  this.render = function(data) {
    return compiled(data);
  }
}