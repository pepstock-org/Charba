// --------------------------
// Controller class creation
// --------------------------
// Constructor
function Charba{0}() {
  Chart.controllers.{1}.apply(this, arguments);
}
// Copies the extended controller prototype
Charba{0}.prototype = Object.create(Chart.controllers.{1}.prototype);
// Defines the constructor
Charba{0}.prototype.constructor = Charba{0};

// --------------------
// Internal methods
// --------------------
// gets the wrappers y controller type or null if not defined
Charba{0}.prototype.checkAndGetWrapper = function(property){
	var delegated = CharbaJsControllerHelper.wrappers.{0};
	if (typeof delegated !== 'undefined' && typeof delegated[property] === 'function'){
		return delegated;
	}
	return null;
};
// --------------------
// Overrided methods
// --------------------
// Initializes the controller
Charba{0}.prototype.initialize = function() {
	var delegated = this.checkAndGetWrapper('initialize');
	if (delegated !== null){
		delegated.initialize.apply(this, arguments);
	} else {
		Chart.controllers.{1}.prototype.initialize.apply(this, arguments);
	}		
};
// Create elements for each piece of data in the dataset. Store elements in an array on the dataset as dataset.metaData
Charba{0}.prototype.addElements = function() {
	var delegated = this.checkAndGetWrapper('addElements');
	if (delegated !== null){
		delegated.addElements.apply(this, arguments);
	} else {
		Chart.controllers.{1}.prototype.addElements.apply(this, arguments);
	}		
};
// Draw the representation of the dataset
Charba{0}.prototype.draw = function() {
	var delegated = this.checkAndGetWrapper('draw');
	if (delegated !== null){
		delegated.draw.apply(this, arguments);
	} else {
		Chart.controllers.{1}.prototype.draw.apply(this, arguments);
	}			
};
// Remove hover styling from the given element
Charba{0}.prototype.removeHoverStyle = function() {
	var delegated = this.checkAndGetWrapper('removeHoverStyle');
	if (delegated !== null){
		delegated.removeHoverStyle.apply(this, arguments);
	} else {
		Chart.controllers.{1}.prototype.removeHoverStyle.apply(this, arguments);
	}			
};
// Add hover styling to the given element
Charba{0}.prototype.setHoverStyle = function() {
	var delegated = this.checkAndGetWrapper('setHoverStyle');
	if (delegated !== null){
		delegated.setHoverStyle.apply(this, arguments);
	} else {
		Chart.controllers.{1}.prototype.setHoverStyle.apply(this, arguments);
	}		
};
// Update the elements in response to new data
Charba{0}.prototype.update = function() {
	var delegated = this.checkAndGetWrapper('update');
	if (delegated !== null){
		delegated.update.apply(this, arguments);
	} else {
		Chart.controllers.{1}.prototype.update.apply(this, arguments);
	}		
};
// --------------------
// Register to CHART.JS
// --------------------
// sets the controller id
Charba{0}.id = '{0}'; 
// copies the default options of extended chart type
Charba{0}.defaults = Chart.defaults.{1}; 
// registers into CHART.JS
Chart.registry.addControllers([Charba{0}]);