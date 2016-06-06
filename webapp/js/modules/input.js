function Input(options) {
  var elem;

  this.getElem = getElem;

  function getElem() {
    if (!elem)  render();
    return elem;
  }

  function render() {
    elem = document.createElement('div');
    var html = options.inputTmpl({
      labelText: options.labelText,
      buttonText: options.buttonText
    });

    elem.insertAdjacentHTML('afterbegin', html);
  }
}
