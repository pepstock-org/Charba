/**
 * @externs
 */
/**
 * namespace
 * @namespace
 */
var ML = {}; 
/**
 * @constructor
 * @return {ML.RegressionScore}
 */
ML.RegressionScore = function() {};
/**
 * @type {number}
 */
ML.RegressionScore.prototype.r;
/**
 * @type {number}
 */
ML.RegressionScore.prototype.r2;
/**
 * @type {number}
 */
ML.RegressionScore.prototype.chi2;
/**
 * @type {number}
 */
ML.RegressionScore.prototype.rsmd;
/**
 * @constructor
 * @return {ML.BaseRegression}
 */
ML.BaseRegression = function() {};
/**
 * @param {number} x
 * @return {number}
 */
ML.BaseRegression.prototype.predict = function(x) {};
/**
 * @param {Array} x
 * @param {Array} y
 * @return {ML.RegressionScore}
 */
ML.BaseRegression.prototype.score = function(x, y) {};
/**
 * @param {number} precision
 * @return {string}
 */
ML.BaseRegression.prototype.toLaTeX = function(precision) {};
/**
 * @return {Object}
 */
ML.BaseRegression.prototype.toJSON = function() {};
/**
 * @constructor
 * @extends {ML.BaseRegression}
 * @param {Array} x
 * @param {Array} y
 * @return {ML.SimpleLinearRegression}
 */
ML.SimpleLinearRegression = function(x, y) {};
/**
 * @param {number} x
 * @return {number}
 */
ML.SimpleLinearRegression.prototype.computeX = function(x) {};
/**
 * @type {number}
 */
ML.SimpleLinearRegression.prototype.slope;
/**
 * @type {number}
 */
ML.SimpleLinearRegression.prototype.intercept;
/**
 * @type {Array}
 */
ML.SimpleLinearRegression.prototype.coefficients;
/**
 * @param {Object} descriptor
 * @return {ML.SimpleLinearRegression}
 */
ML.SimpleLinearRegression.load = function(descriptor) {};
/**
 * @constructor
 * @extends {ML.BaseRegression}
 * @param {Array} x
 * @param {Array} y
 * @return {ML.ExponentialRegression}
 */
ML.ExponentialRegression = function(x, y) {};
/**
 * @type {number}
 */
ML.ExponentialRegression.prototype.A;
/**
 * @type {number}
 */
ML.ExponentialRegression.prototype.B;
/**
 * @param {Object} descriptor
 * @return {ML.ExponentialRegression}
 */
ML.ExponentialRegression.load = function(descriptor) {};
/**
 * @constructor
 * @extends {ML.BaseRegression}
 * @param {Array} x
 * @param {Array} y
 * @param {number} degree
 * @return {ML.PolynomialRegression}
 */
ML.PolynomialRegression = function(x, y, degree) {};
/**
 * @type {number}
 */
ML.PolynomialRegression.prototype.degree;
/**
 * @type {Array}
 */
ML.PolynomialRegression.prototype.powers;
/**
 * @type {Array}
 */
ML.PolynomialRegression.prototype.coefficients;
/**
 * @param {Object} descriptor
 * @return {ML.PolynomialRegression}
 */
ML.PolynomialRegression.load = function(descriptor) {};
/**
 * @constructor
 * @extends {ML.BaseRegression}
 * @param {Array} x
 * @param {Array} y
 * @return {ML.PowerRegression}
 */
ML.PowerRegression = function(x, y) {};
/**
 * @type {number}
 */
ML.PowerRegression.prototype.A;
/**
 * @type {number}
 */
ML.PowerRegression.prototype.B;
/**
 * @param {Object} descriptor
 * @return {ML.PowerRegression}
 */
ML.PowerRegression.load = function(descriptor) {};
/**
 * @constructor
 * @extends {ML.BaseRegression}
 * @param {Array} x
 * @param {Array} y
 * @return {ML.TheilSenRegression}
 */
ML.TheilSenRegression = function(x, y) {};
/**
 * @param {number} x
 * @return {number}
 */
ML.TheilSenRegression.prototype.computeX = function(x) {};
/**
 * @type {number}
 */
ML.TheilSenRegression.prototype.slope;
/**
 * @type {number}
 */
ML.TheilSenRegression.prototype.intercept;
/**
 * @type {Array}
 */
ML.TheilSenRegression.prototype.coefficients;
/**
 * @param {Object} descriptor
 * @return {ML.TheilSenRegression}
 */
ML.TheilSenRegression.load = function(descriptor) {};
/**
 * @constructor
 * @return {CharbaJsMLHelper}
 */
function CharbaJsMLHelper() {}
/**
 * @param {ML.BaseRegression} regression
 * @param {Array} values
 * @return {Array}
 */
CharbaJsMLHelper.predict = function(regression, values) {};
/**
 * @param {ML.BaseRegression} regression
 * @param {number} precision
 * @return {string}
 */
CharbaJsMLHelper.toFormula = function(regression, precision) {};
