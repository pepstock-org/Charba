    function JsHelper() {};
    JsHelper.remove = function(obj, key) {
    	delete obj[key];
    }
    JsHelper.type = function(obj, key) {
    	return typeof obj[key];
    }
    JsHelper.isArray = function(obj, key) {
	    return Array.isArray(obj[key]);
    }    
    JsHelper.propertyAsString = function(obj, key) {
    	return obj[key];
    }
    JsHelper.propertyAsDouble = function(obj, key) {
    	return obj[key];
    }
    JsHelper.propertyAsInt = function(obj, key) {
    	return obj[key];
    }    
    JsHelper.setLineDash = function(context, values) {
    	context.setLineDash(values);
    }
    JsHelper.newCallbackProxy = function() {
    	var obj = new Object();
    	obj.callback = null;
    	obj.proxy = function() {
        	if (obj.callback != null && typeof obj.callback === 'function'){
				var args = Array.of(this).concat(Array.prototype.slice.call(arguments));
				var result = obj.callback.apply(this, args);
				if (result === null){
					// do nothing console.log("null");
				} else if (result === undefined){
					//console.log("undefined");
				} else {
					//console.log(result);
					return result;
				}
    		} else {
    			//console.log("No caller");
    		}
		};
    	return obj;
    }
    function JsControllerHelper() {};
    JsControllerHelper.register = function(controllerType, instance) {
    	Chart.controllers[controllerType] = Chart.DatasetController.extend(instance);
    }
    JsControllerHelper.extend = function(controllerType, chartType, instance) {
		Chart.defaults[controllerType] = Chart.defaults[chartType];
		Chart.controllers[controllerType] = Chart.controllers[chartType].extend(instance);
    }
    JsControllerHelper.initialize = function(controllerType, context, datasetIndex) {
    	Chart.controllers[controllerType].prototype.initialize.call(context, context.chart, datasetIndex);
    }
    JsControllerHelper.addElements = function(controllerType, context) {
       	Chart.controllers[controllerType].prototype.addElements.call(context);
    }
    JsControllerHelper.addElementAndReset = function(controllerType, context, index) {
    	Chart.controllers[controllerType].prototype.addElementAndReset.call(context, index);
    }
    JsControllerHelper.draw = function(controllerType, context, ease) {
    	Chart.controllers[controllerType].prototype.draw.call(context, ease);
    }
    JsControllerHelper.removeHoverStyle = function(controllerType, context, element) {
    	Chart.controllers[controllerType].prototype.removeHoverStyle.call(context, element);
    }
    JsControllerHelper.setHoverStyle = function(controllerType, context, element) {
    	Chart.controllers[controllerType].prototype.setHoverStyle.call(context, element);
    }
    JsControllerHelper.update = function(controllerType, context, reset) {
    	Chart.controllers[controllerType].prototype.update.call(context, reset);
    }
