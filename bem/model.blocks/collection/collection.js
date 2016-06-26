// Collection
function Collection() {}
Collection.prototype.property = function (key, value) {
  if (value == undefined) {
    return this[key];
  } else {
    this[key] = value;
  }
};
Collection.prototype.deleteProperty = function (key) {
  delete  this[key];
};
Collection.prototype.hasProperty = function (key, value) {
  if (!this.hasOwnProperty(key)) return false;
  return (this[key] === value);
};