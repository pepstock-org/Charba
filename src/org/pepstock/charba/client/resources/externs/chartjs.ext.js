/**
 * @externs
 */
/**
 * @constructor
 * @param {CanvasRenderingContext2D} context
 * @param {Object} configuration
 * @return {Chart}
 */
function Chart(context, configuration) {};
/**
 * @param {number=} width
 * @param {number=} height
 * @return {undefined}
 */
Chart.prototype.resize = function(width, height) {};
/**
 * @param {string=} configuration
 * @return {undefined}
 */
Chart.prototype.update = function(configuration) {};
/**
 * @return {undefined}
 */
Chart.prototype.draw = function() {};
/**
 * @return {undefined}
 */
Chart.prototype.render = function() {};
/**
 * @return {undefined}
 */
Chart.prototype.destroy = function() {};
/**
 * @return {undefined}
 */
Chart.prototype.stop = function() {};
/**
 * @return {undefined}
 */
Chart.prototype.clear = function() {};
/**
 * @return {undefined}
 */
Chart.prototype.reset = function() {};
/**
 * @param {string=} type
 * @param {number=} quality
 * @return {string}
 */
Chart.prototype.toBase64Image = function(type, quality) {};
/**
 * @return {string}
 */
Chart.prototype.generateLegend = function() {};
/**
 * @param {string} pluginId
 * @return {boolean}
 */
Chart.prototype.isPluginEnabled = function(pluginId) {};
/**
 * @param {Object} event
 * @param {string} mode 
 * @param {Object} options 
 * @param {boolean} useFinalPosition 
 * @return {Array}
 */
Chart.prototype.getElementsAtEventForMode = function(event, mode, options, useFinalPosition) {};
/**
 * @return {Array}
 */
Chart.prototype.getSortedVisibleDatasetMetas = function() {};
/**
 * @param {number} index
 * @return {Object}
 */
Chart.prototype.getDatasetMeta = function(index) {};
/**
 * @param {number} index
 * @return {boolean}
 */
Chart.prototype.isDatasetVisible = function(index) {};
/**
 * @return {number}
 */
Chart.prototype.getVisibleDatasetCount = function() {};
/**
 * @param {number} datasetIndex
 * @param {boolean} visibility
 * @return {undefined}
 */
Chart.prototype.setDatasetVisibility = function(datasetIndex, visibility) {};
/**
 * @param {number} index
 * @return {undefined}
 */
Chart.prototype.toggleDataVisibility = function(index) {};
/**
 * @param {number} index
 * @return {boolean}
 */
Chart.prototype.getDataVisibility = function(index) {};
/**
 * @param {number} datasetIndex
 * @param {number=} dataIndex
 * @return {undefined}
 */
Chart.prototype.hide = function(datasetIndex, dataIndex) {};
/**
 * @param {number} datasetIndex
 * @param {number=} dataIndex
 * @return {undefined}
 */
Chart.prototype.show = function(datasetIndex, dataIndex) {};
/**
 * @param {Array} elements
 * @return {undefined}
 */
Chart.prototype.setActiveElements = function(elements) {};
/**
 * @return {Array}
 */
Chart.prototype.getActiveElements = function() {};
/**
 * @type {number}
 */
Chart.prototype.id;
/**
 * @type {number}
 */
Chart.prototype.width;
/**
 * @type {number}
 */
Chart.prototype.height;
/**
 * @type {number}
 */
Chart.prototype.aspectRatio;
/**
 * @type {number}
 */
Chart.prototype.currentDevicePixelRatio;
/**
 * @type {Object}
 */
Chart.prototype.chartArea;
/**
 * @type {Object}
 */
Chart.prototype.legend;
/**
 * @type {Object}
 */
Chart.prototype.titleBlock;
/**
 * @type {Object}
 */
Chart.prototype.options;
/**
 * @type {Object}
 */
Chart.prototype.scales;
/**
 * @type {Object}
 */
Chart.prototype.tooltip;
/**
 * @type {Object}
 */
Chart.defaults;
/**
 * @type {Object}
 */
Chart.overrides;
/**
 * @type {CharbaChartHelpers}
 */
Chart.helpers;
/**
 * @type {Array}
 */
Chart.instances;
/**
 * @type {Object}
 */
Chart.Interaction;
/**
 * @constructor
 * @return {CharbaChartHelpers}
 */
function CharbaChartHelpers() {};
/**
 * @param {Object} target
 * @param {Object} source
 * @return {Object}
 */
CharbaChartHelpers.prototype.mergeIf = function(target, source){};
/**
 * @param {Object} source
 * @return {Object}
 */
CharbaChartHelpers.prototype.clone = function(source){};
/**
 * @param {Object} event
 * @param {Chart} chart
 * @return {Object}
 */
CharbaChartHelpers.prototype.getRelativePosition = function(event, chart){};
/**
 * @param {Object} font
 * @return {Object}
 */
CharbaChartHelpers.prototype.toFont = function(font){};
/**
 * @param {number} number
 * @param {string=} locale
 * @param {Object=} options
 * @return {string}
 */
CharbaChartHelpers.prototype.formatNumber = function(number, locale, options) {};
/**
 * @constructor
 * @return {CharbaChartAdaptersDate}
 */
function CharbaChartAdaptersDate() {};
/**
 * @param {*} key
 * @return {boolean}
 */
CharbaChartAdaptersDate.prototype.hasOwnProperty = function(key){};
/**
 * @type {string}
 */
CharbaChartAdaptersDate.prototype._id;
/**
 * @return {Object}
 */
CharbaChartAdaptersDate.prototype.formats = function(){};
/**
 * @param {string} time
 * @param {string} format
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.parse = function(time, format){};
/**
 * @param {number} time
 * @param {string} format
 * @return {string}
 */
CharbaChartAdaptersDate.prototype.format = function(time, format){};
/**
 * @param {number} time
 * @param {number} amount
 * @param {string} unit
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.add = function(time, amount, unit){};
/**
 * @param {number} maxTime
 * @param {number} minTime
 * @param {string} unit
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.diff = function(maxTime, minTime, unit){};
/**
 * @param {number} time
 * @param {string} unit
 * @param {number} weekday
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.startOf = function(time, unit, weekday){};
/**
 * @param {number} time
  * @param {string} unit
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.endOf = function(time, unit){};
/**
 * @constructor
 * @return {CharbaControllerContext}
 */
function CharbaControllerContext() {};
/**
 * @type {Chart}
 */
CharbaControllerContext.prototype.chart;
/**
 * @type {number}
 */
CharbaControllerContext.prototype.index;
/**
 * @type {Array}
 */
CharbaControllerContext.prototype._data;
/**
 * @constructor
 * @return {CharbaScriptableOptionsContext}
 */
function CharbaScriptableOptionsContext() {};
/**
 * @type {Chart}
 */
CharbaScriptableOptionsContext.prototype.chart;
/**
 * @type {boolean}
 */
CharbaScriptableOptionsContext.prototype.active;
/**
 * @type {number}
 */
CharbaScriptableOptionsContext.prototype.datasetIndex;
/**
 * @type {number}
 */
CharbaScriptableOptionsContext.prototype.dataIndex;
/**
 * @type {Object}
 */
CharbaScriptableOptionsContext.prototype.dataPoint;
/**
 * @type {Object}
 */
CharbaScriptableOptionsContext.prototype.element;
/**
 * @type {Object}
 */
CharbaScriptableOptionsContext.prototype.scale;
/**
 * @type {Object}
 */
CharbaScriptableOptionsContext.prototype.tick;
/**
 * @type {number}
 */
CharbaScriptableOptionsContext.prototype.index;
/**
 * @type {Object}
 */
CharbaScriptableOptionsContext.prototype.options;
