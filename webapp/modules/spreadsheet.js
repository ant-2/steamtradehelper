function Spreadsheet(items, template) {
  "use strict";
  var html;

  this.html = function() {
    if (!html) {html = renderItems(items)}
    return html;
  };

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