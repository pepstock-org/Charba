/**
 * @externs
 */
/**
 * @constructor
 * @return {CharbaToast}
 */
function CharbaToast() {}
/**
 * @type {Object}
 */
CharbaToast.defaults;
/**
 * @type {Object}
 */
CharbaToast.overrides;
/**
 * @type {number}
 */
CharbaToast.currentOpenItems;
/**
 * @type {Function}
 */
CharbaToast.onClose;
/**
 * @param {number} id
 * @param {string} title
 * @param {Array} label
 * @param {Object} options
 * @param {Object} dateTime
 * @return {Object}
 */
CharbaToast.create = function(id, title, label, options, dateTime) {};
/**
 * @param {Object} source
 * @return {Object}
 */
CharbaToast.clone = function(source) {};
/**
 * @param {Object} item
 * @return {undefined}
 */
CharbaToast.close = function(item) {};
