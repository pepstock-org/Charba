	/*
	    Copyright 2017 Andrea "Stock" Stocchero
	
	    Licensed under the Apache License, Version 2.0 (the "License");
	    you may not use this file except in compliance with the License.
	    You may obtain a copy of the License at
	
		    http://www.apache.org/licenses/LICENSE-2.0
	
	    Unless required by applicable law or agreed to in writing, software
	    distributed under the License is distributed on an "AS IS" BASIS,
	    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	    See the License for the specific language governing permissions and
	    limitations under the License.
	*/
	/*
		JSHelper is an object with a set of static methods used as utility
		and needed to improve JSINTEROP adoption for CHARBA, because 
		JSINTEROP is not able to address all javascript model.   
	*/
    function CharbaJsHelper() {}
    /*
	 Returns the java script object type of the object.
	  
	 @param object the object to get type.
	 @return the object type
    */
    CharbaJsHelper.typeOf = function(obj) {
    	return typeof obj;
    }
   /*
	 Returns an undefined.
	 @return undefined
    */
    CharbaJsHelper.undefined = function(obj) {
    	return undefined;
    }
   /*
	 Removes a property from a java script object.
	 
	 @param object the object on which to remove the property.
	 @param key the string name of the property to remove.
    */
    CharbaJsHelper.remove = function(obj, key) {
    	delete obj[key];
    }
    /*
	 Returns the java script object type of a property.
	  
	 @param object the object on which to search the property.
	 @param key the string name of the property to test.
	 @return the object type
    */
    CharbaJsHelper.type = function(obj, key) {
    	return typeof obj[key];
    }
    /*
	 This method determines whether the passed property of passed object is an Array.
	  
	 @param object the object on which to test the property.
	 @param key the string name of the property to test.
	 @return true if the value is an Array; otherwise, false.
    */
    CharbaJsHelper.isArray = function(obj, key) {
	    return Array.isArray(obj[key]);
    }    
    /*
	 Returns a property of java script object as string.
	  
	 @param object the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return string value
    */
    CharbaJsHelper.propertyAsString = function(obj, key) {
    	return obj[key];
    }
    /*
	 Returns a property of java script object as double.
	  
	 @param object the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return double value
    */
    CharbaJsHelper.propertyAsDouble = function(obj, key) {
    	return obj[key];
    }
    /*
	 Returns a property of java script object as integer.
	  
	 @param object the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return integer value
    */
    CharbaJsHelper.propertyAsInt = function(obj, key) {
    	return obj[key];
    }  
    /*
	 Returns a chart native event from CHART.JS event.
	  
	 @param event CHART.JS event
	 @param key key of java script object
	 @return a chart native event
    */
    CharbaJsHelper.nativeEvent = function(obj, key) {
    	return obj[key];
    }      
    /*
	 Returns an array of strings with element attributes.
	  
	 @param element DOM element to scan
	 @return an array of strings with element attributes
    */
    CharbaJsHelper.elementAttributes = function(element) {
    	var result = new Array();
    	// First, let's verify that the paragraph has some attributes    
     	if (element.hasAttributes()) {
           var attrs = element.attributes;
	       for(var i = 0; i < attrs.length; i++) {
	          result[i] = attrs[i].name + "='" + attrs[i].value +"'";
	       }
	    }
    	return result;
    }  
    /*
	 Sets the line dash pattern used when stroking lines. It uses an array of values that specify alternating lengths of lines
	 and gaps which describe the pattern.
	 
	 @param context context of canvas
	 @param object array of values that specify alternating lengths of lines and gaps which describe the pattern
    */
    CharbaJsHelper.setLineDash = function(context, values) {
    	context.setLineDash(values);
    }
    /*
	 Creates new proxy for callback which will pass "this" environment of java script as first argument of callback
	 method.
	  
	 @return new proxy for callback.
    */
    CharbaJsHelper.newCallbackProxy = function() {
    	/*
    		Creates an object with 2 properties.
    		CALLBACK: contains user callback implementation which must be called
    		PROXY: contains a function which can call CALLBACk passing "this" 
    	 */
    	var obj = new Object();
		// CALLBACK
    	obj.callback = null;
    	// PROXY
    	obj.proxy = function() {
    		// checks if callback is a function	
        	if (obj.callback != null && typeof obj.callback === 'function'){
        		// creates arguments for callbacks adding the "this" 
				var args = Array.of(this).concat(Array.prototype.slice.call(arguments));
				// calls CALLBACK
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
    /*
		JSControllerHelper is an object with a set of static methods used as utility
		and needed to improve JSINTEROP adoption for CHARBA controllers implementation.   
	*/
    function CharbaJsControllerHelper() {}
    /*
     Register the controller which does not extend any existing one.
	  
	 @param controllerType controller type
	 @param instance controller java script instance
    */
    CharbaJsControllerHelper.register = function(controllerType, instance) {
    	Chart.controllers[controllerType] = Chart.DatasetController.extend(instance);
    }
    /*
	 Extends an existing chart with a controller implementation.
	  
	 @param controllerType controller type
	 @param chartType type of extended chart
	 @param instance controller java script instance    
    */
    CharbaJsControllerHelper.extend = function(controllerType, chartType, instance) {
		Chart.defaults[controllerType] = Chart.defaults[chartType];
		Chart.defaults.global.datasets[controllerType] = Chart.defaults.global.datasets[chartType];
		Chart.controllers[controllerType] = Chart.controllers[chartType].extend(instance);
    }
    /*
	 Invokes the default "initialize" method.
	  
	 @param chartType extended chart type
	 @param context context of controller
	 @param datasetIndex dataset index
    */
    CharbaJsControllerHelper.initialize = function(controllerType, context, datasetIndex) {
    	Chart.controllers[controllerType].prototype.initialize.call(context, context.chart, datasetIndex);
    }
    /*
	 Invokes the default "AddElements" method.
	  
	 @param chartType extended chart type
	 @param context context of controller
    */
    CharbaJsControllerHelper.addElements = function(controllerType, context) {
       	Chart.controllers[controllerType].prototype.addElements.call(context);
    }
    /*
	 Invokes the default "addElementAndReset" method.
	  
	 @param chartType extended chart type
	 @param context context of controller
	 @param index dataset index
    */
    CharbaJsControllerHelper.addElementAndReset = function(controllerType, context, index) {
    	Chart.controllers[controllerType].prototype.addElementAndReset.call(context, index);
    }
    /*
	 Invokes the default "draw" method.
	  
	 @param chartType extended chart type
	 @param context context of controller
	 @param ease if specified, this number represents how far to transition elements.
    */
    CharbaJsControllerHelper.draw = function(controllerType, context, ease) {
    	Chart.controllers[controllerType].prototype.draw.call(context, ease);
    }
    /*
	 Invokes the default "removeHoverStyle" method.
	  
	 @param chartType extended chart type
	 @param context context of controller
	 @param element element to be remove.
    */
    CharbaJsControllerHelper.removeHoverStyle = function(controllerType, context, element) {
    	Chart.controllers[controllerType].prototype.removeHoverStyle.call(context, element);
    }
    /*
	 Invokes the default "setHoverStyle" method.
	  
	 @param chartType extended chart type
	 @param context context of controller
	 @param element element to be set.
    */
    CharbaJsControllerHelper.setHoverStyle = function(controllerType, context, element) {
    	Chart.controllers[controllerType].prototype.setHoverStyle.call(context, element);
    }
    /*
	 Invokes the default "update" method.
	  
	 @param chartType extended chart type
	 @param context context of controller
	 @param reset if true, put the elements into a reset state so they can animate to their final values
    */
    CharbaJsControllerHelper.update = function(controllerType, context, reset) {
    	Chart.controllers[controllerType].prototype.update.call(context, reset);
    }
    /*
		JSWindowHelper is an object with a set of static methods used as utility
		and needed to act on window java script object.   
	*/
    function CharbaJsWindowHelper() {}
    /*
     CSS media queries allow changing styles when printing a page. The CSS applied from these media queries may cause charts
	 to need to resize. However, the resize won't happen automatically. To support resizing charts when printing, one needs to
	 hook the "onbeforeprint" event and manually trigger resizing of each chart.
    */
    CharbaJsWindowHelper.enableResizeOnBeforePrint = function() {
    	window.onbeforeprint = function (event) {
 			for (var id in Chart.instances) {
    			Chart.instances[id].resize();
  			}
		}
    }
    /*
		JSPositionerHelper is an object with a set of static methods used as utility
		and needed to add custom positioner on tooltips.   
	*/
    function CharbaJsPositionerHelper() {}
    /*
     Registers a custom postioner for tooltips into CHART.JS.

	 @param name name of new position to set into tooltip config
	 @param module function to invoke to get control
    */
    CharbaJsPositionerHelper.register = function(name, module) {
    	if (module != null && typeof module === 'function'){
	    	Chart.Tooltip.positioners[name] = module;
    	}
    }
    /*
     Unregisters a custom postioner for tooltips from CHART.JS.

	 @param name name of new position to set into tooltip config
    */
    CharbaJsPositionerHelper.unregister = function(name) {
    	if (Chart.Tooltip.positioners[name] != 'undefined'){
 		    delete Chart.Tooltip.positioners[name];
    	}
    }
    /*
	 Invokes an existing positioner to get the point.
	  
	 @param name name of position to be invoked
	 @return the point calculated by positioner or <code>null</code> if positioner does not exist
    */
    CharbaJsPositionerHelper.invoke = function(name, context, elements, eventPoint) {
    	if (Chart.Tooltip.positioners[name] != 'undefined'){
    		return Chart.Tooltip.positioners[name].apply(context, Array.of(elements, eventPoint));
    	}
    	return null;
    }
    /*
		JSCallbacksHelper is an object with a set of static methods used as utility
		and needed to act on CHART.JS default callbacks.   
	*/
    function CharbaJsCallbacksHelper() {}
    /*
     Invokes the default generate legend callbacks from CHART.JS.

	 @param context context function, this in javascript
	 @param options chart options where generate legend callback is stored
	 @param chart chart instance
    */
    CharbaJsCallbacksHelper.generateDefaultCallback = function(chart, options) {
    	if (options != null && typeof options.legendCallback === 'function'){
    		return options.legendCallback.call(chart, chart);
    	}
    	return null;
    }
    
    