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
		This object is used to get a proxy instance which is able to call a 
		java script function passing also "this" java script value, to maintain 
		the environment when required.
	*/
	var CharbaCallbackProxy = {
  		callback: null,
  		proxy: null
	}	
	/*
		JSHelper is an object with a set of static methods used as utility
		and needed to improve JSINTEROP adoption for CHARBA, because 
		JSINTEROP is not able to address all javascript model.   
	*/
    function CharbaJsHelper() {}
    /*
	 Returns a boolean indicating whether the object has the specified property as its own property.
	  
	 @param obj the object on which to search the property.
	 @param key the string name of the property to test.
	 @return boolean indicating whether or not the object has the specified property as own property.
	*/
	CharbaJsHelper.exist = function(obj, key){
		return key in obj;
	}
    /*
	 Returns a boolean indicating whether the object has the specified property as its own property.
	  
	 @param object the object on which to search the property.
	 @param key the string name of the property to test.
	 @return boolean indicating whether or not the object has the specified property as own property.
	*/
	CharbaJsHelper.has = function(obj, key){
		return Object.prototype.hasOwnProperty.call(obj, key);
	}
    /*
	 Returns the java script object type of the object.
	  
	 @param obj the object to get type.
	 @return the object type
    */
    CharbaJsHelper.typeOf = function(obj) {
    	return typeof obj;
    }
    /*
	 Removes a property from a java script object.
	 
	 @param obj the object on which to remove the property.
	 @param key the string name of the property to remove.
    */
    CharbaJsHelper.remove = function(obj, key) {
    	delete obj[key];
    }
    /*
	 Returns the java script object type of a property.
	  
	 @param obj the object on which to search the property.
	 @param key the string name of the property to test.
	 @return the object type
    */
    CharbaJsHelper.type = function(obj, key) {
    	return typeof obj[key];
    }
    /*
	 This method determines whether the passed property of passed object is an Array.
	  
	 @param obj the object on which to test the property.
	 @param key the string name of the property to test.
	 @return true if the value is an Array; otherwise, false.
    */
    CharbaJsHelper.isArray = function(obj, key) {
	    return Array.isArray(obj[key]);
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
    	var obj = Object.create(CharbaCallbackProxy);
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
	 Returns true if the object is a CanvasPattern.
	  
	 @param obj the object to check
	 @return true if the object is a CanvasPattern
    */
    CharbaJsHelper.isCanvasPattern = function(obj) {
   		return obj instanceof CanvasPattern;
    } 
    /*
	 Returns true if the object is a CanvasGradient.
	  
	 @param obj the object to check
	 @return true if the object is a CanvasGradient
    */
    CharbaJsHelper.isCanvasGradient = function(obj) {
   		return obj instanceof CanvasGradient;
    } 
    /*
		JSPluginHelper is an object with a set of static methods used as utility
		and needed to improve JSINTEROP adoption for CHARBA controllers implementation.   
	*/
    function CharbaJsPluginHelper() {}
    /*
     Registers new plugin.
	  
	 @param instance plugin java script instance
    */
    CharbaJsPluginHelper.register = function(instance) {
    	Chart.register(instance);
    }
    /*
     Unregisters an existing plugin.
	  
	 @param instance plugin java script instance
    */
    CharbaJsPluginHelper.unregister = function(instance) {
    	Chart.registry.remove([instance]);
    }
    /*
     Returns all registered plugins as object.
	  
	 @return all registered plugins as object
    */
    CharbaJsPluginHelper.getAll = function() {
    	return Chart.registry.plugins.items;
    }
    /*
		JSControllerHelper is an object with a set of static methods used as utility
		and needed to improve JSINTEROP adoption for CHARBA controllers implementation.   
	*/
    function CharbaJsControllerHelper() {}
    /*
	 Extends an existing chart with a controller implementation.
	  
	 @param controllerType controller type
	 @param chartType type of extended chart
	 @param instance controller java script instance    
    */
    CharbaJsControllerHelper.register = function(controllerType, chartType, instance) {
    	if (typeof CharbaJsControllerHelper.wrappers === 'undefined'){ 
			Object.defineProperty(CharbaJsControllerHelper, 'wrappers', {
				value: {},
				configurable: false,
				enumerable: false,
				writable: false
			});
		}
		CharbaJsControllerHelper.wrappers[controllerType] = instance;
    }
    /*
	 Invokes the default "initialize" method.
	  
	 @param controllerType controller type
	 @param context context of controller
    */
    CharbaJsControllerHelper.initialize = function(controllerType, context) {
    	Chart.controllers[controllerType].prototype.initialize.call(context);
    }
    /*
	 Invokes the default "AddElements" method.
	  
	 @param controllerType controller type
	 @param context context of controller
    */
    CharbaJsControllerHelper.addElements = function(controllerType, context) {
       	Chart.controllers[controllerType].prototype.addElements.call(context);
    }
    /*
	 Invokes the default "draw" method.
	  
	 @param controllerType controller type
	 @param context context of controller
    */
    CharbaJsControllerHelper.draw = function(controllerType, context) {
    	Chart.controllers[controllerType].prototype.draw.call(context);
    }
    /*
	 Invokes the default "removeHoverStyle" method.
	  
	 @param controllerType controller type
	 @param context context of controller
	 @param element element to be remove.
	 @param datasetIndex dataset index
	 @param index data index
	 */
    CharbaJsControllerHelper.removeHoverStyle = function(controllerType, context, element, datasetIndex, index) {
    	Chart.controllers[controllerType].prototype.removeHoverStyle.call(context, element, datasetIndex, index);
    }
    /*
	 Invokes the default "setHoverStyle" method.
	  
	 @param controllerType controller type
	 @param context context of controller
	 @param element element to be set.
	 @param datasetIndex dataset index
	 @param index data index
    */
    CharbaJsControllerHelper.setHoverStyle = function(controllerType, context, element, datasetIndex, index) {
    	Chart.controllers[controllerType].prototype.setHoverStyle.call(context, element, datasetIndex, index);
    }
    /*
	 Invokes the default "update" method.
	  
	 @param controllerType controller type
	 @param context context of controller
	 @param mode update mode, core calls this method using any of `'active'`, `'hide'`, `'reset'`, `'resize'`, `'show'` or `undefined`
    */
    CharbaJsControllerHelper.update = function(controllerType, context, mode) {
    	Chart.controllers[controllerType].prototype.update.call(context, mode);
    }
    /*
	 Invokes the default "linkScales" method.
	  
	 @param controllerType controller type
	 @param context context of controller
    */
    CharbaJsControllerHelper.linkScales = function(controllerType, context) {
    	Chart.controllers[controllerType].prototype.linkScales.call(context);
    }
    /*
	 Invokes the default "buildOrUpdateElements" method.
	  
	 @param controllerType controller type
	 @param context context of controller
    */
    CharbaJsControllerHelper.buildOrUpdateElements = function(controllerType, context) {
    	Chart.controllers[controllerType].prototype.buildOrUpdateElements.call(context);
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
    	    const tooltipPlugin = Chart.registry.getPlugin('tooltip');
            tooltipPlugin.positioners[name] = module;
    	}
    }
    /*
     Unregisters a custom postioner for tooltips from CHART.JS.

	 @param name name of new position to set into tooltip config
    */
    CharbaJsPositionerHelper.unregister = function(name) {
   	    const tooltipPlugin = Chart.registry.getPlugin('tooltip');
    	if (tooltipPlugin.positioners[name] != 'undefined'){
 		    delete tooltipPlugin.positioners[name];
    	}
    }
    /*
	 Invokes an existing positioner to get the point.
	  
	 @param name name of position to be invoked
	 @param context function context of javascript call
	 @param elements datasets items
	 @param eventPoint the point on the canvas where the event occurred
	 @return the point calculated by positioner or <code>null</code> if positioner does not exist
    */
    CharbaJsPositionerHelper.invoke = function(name, context, elements, eventPoint) {
        const tooltipPlugin = Chart.registry.getPlugin('tooltip');
    	if (tooltipPlugin.positioners[name]  != 'undefined'){
    		return tooltipPlugin.positioners[name].apply(context, Array.of(elements, eventPoint));
    	}
    	return null;
    }
    /*
		JSCallbacksHelper is an object with a set of static methods used as utility
		and needed to act on CHART.JS default callbacks.   
	*/
    function CharbaJsChartHelper() {}
    /*
     Invokes the default generate labels callbacks from CHART.JS.

	 @param chart chart instance
	 @param options chart options where generate legend callback is stored
	 @return an array of labels
    */
    CharbaJsChartHelper.generateDefaultLabels = function(chart, options) {
    	if (options != null && typeof options.legend === 'object' && typeof options.legend.labels === 'object' && typeof options.legend.labels.generateLabels === 'function'){
    		return options.legend.labels.generateLabels.call(chart, chart);
     	}
    	return null;
    }
    /*
	 Invokes the legend event callbacks, provided out of the box by CHART.JS.
	  
	 @param options chart options, generated merging all defaults.
	 @param key the key of options which should have the event callback
	 @param chart chart instance, used as function context
	 @param event native event from user interface
	 @param item legend item native  
    */
    CharbaJsChartHelper.invokeDefaultLegendEvent = function(options, key, chart, event, item) {
    	if (options != null && typeof options.legend === 'object' && typeof options.legend[key] === 'function'){
    		options.legend[key].call(chart, event, item, chart.legend);
    	}
    }
    /*
	 Invokes the chart event callbacks, provided out of the box by CHART.JS.
	  
	 @param options chart options, generated merging all defaults.
	 @param key the key of options which should have the event callback
	 @param chart chart instance, used as function context
	 @param event native event from user interface
	 @param items array of datasets native objects  
    */
    CharbaJsChartHelper.invokeDefaultChartEvent = function(options, key, chart, event, items) {
    	if (options != null && typeof options[key] === 'function'){
    		options[key].call(chart, event, items, chart);
    	}
    }
    /*
		JsZoomHelpers is an object with a set of static methods used as utility
		and needed when ZOOM plugin has been activated.   
	*/
    function CharbaJsZoomHelper() {} 
    /*
	 Invokes the chart reset zoom function if exists.
	  
	 @param chart chart instance
    */
    CharbaJsZoomHelper.resetZoom = function(chart) {
    	if (chart != null && typeof chart.resetZoom === 'function'){
    		chart.resetZoom.call(chart);
    	}
    }
    /*
		JsItemsHelpers is an object with a set of static methods used as utility
		and needed to act with CHARBA items.   
	*/
    function CharbaJsItemsHelper() {} 
    /*
	 Returns true if the property into native object is a CanvasPattern.
	  
	 @param obj the object on which to define the property.
	 @param key the string name of the property to be defined or modified.
	 @return true if the property into native object is a CanvasPattern
    */
    CharbaJsItemsHelper.isCanvasPattern = function(obj, key) {
		return obj[key] instanceof CanvasPattern;
    } 
    /*
	 Returns true if the property into native object is a CanvasGradient.
	  
	 @param obj the object on which to define the property.
	 @param key the string name of the property to be defined or modified.
	 @return true if the property into native object is a CanvasGradient
    */
    CharbaJsItemsHelper.isCanvasGradient = function(obj, key) {
   		return obj[key] instanceof CanvasGradient;
    } 
    /*
	 Returns a chart native event from CHART.JS event.
	  
	 @param obj native event instance
	 @param key key of java script object
	 @return a chart native event
    */
    CharbaJsItemsHelper.nativeEvent = function(obj, key) {
    	return obj[key];
    }    
    /*
	 Used to get the data value from a given pixel. This is the inverse of getPixelForValue
     The coordinate (0, 0) is at the upper-left corner of the canvas
	  
	 @param obj scale native object instance
	 @param pixel pixel value
	 @return the data value from a given pixel
    */
    CharbaJsItemsHelper.getDecimalForPixel = function(obj, pixel) {
    	return obj.getDecimalForPixel(pixel);
    }  
    /*
	 Utility for getting the pixel location of a percentage of scale
     The coordinate (0, 0) is at the upper-left corner of the canvas
	  
	 @param obj scale native object instance
	 @param decimal number value to use
	 @return the pixel location of a percentage of scale
    */
    CharbaJsItemsHelper.getPixelForDecimal = function(obj, decimal) {
    	return obj.getPixelForDecimal(decimal);
    }
    /*
	 Returns the location of the tick at the given index
   	 The coordinate (0, 0) is at the upper-left corner of the canvas
	  
	 @param obj scale native object instance
	 @param index tick index to use
	 @return the location of the tick at the given index
    */
    CharbaJsItemsHelper.getPixelForTick = function(obj, index) {
    	return obj.getPixelForTick(index);
    }   
    /*
	 Used to get the label to display in the tooltip for the given value
	  
	 @param obj scale native object instance
	 @param value value of the data
	 @return the label to display in the tooltip for the given value
    */
    CharbaJsItemsHelper.getLabelForValue = function(obj, value) {
    	return obj.getLabelForValue(value);
    }
    /*
     Returns the location of the given data point as string.
	  
	 @param obj scale native object instance
	 @param value value of the data as string
	 @param index index of the data
	 @return the location of the given data point
    */
    CharbaJsItemsHelper.getPixelForStringValue = function(obj, value, index) {
    	return obj.getPixelForValue(value, index);
    }
    
    /*
     Returns the location of the given data point. Value can either be an index or a numerical value
     The coordinate (0, 0) is at the upper-left corner of the canvas
	  
	 @param obj scale native object instance
	 @param value value of the data
	 @param index index of the data
	 @return the location of the given data point
    */
    CharbaJsItemsHelper.getPixelForValue = function(obj, value, index) {
    	return obj.getPixelForValue(value, index);
    }
    /*
     Used to get the data value from a given pixel. This is the inverse of getPixelForValue
     The coordinate (0, 0) is at the upper-left corner of the canvas
	  
	 @param obj scale native object instance
	 @param pixel pixel value
	 @return the data value from a given pixel
    */
    CharbaJsItemsHelper.getValueForPixel = function(obj, pixel) {
    	return obj.getValueForPixel(pixel);
    }
    /*
     Returns the minimum chart value
	  
	 @param obj scale native object instance
	 @return the minimum chart value
    */
    CharbaJsItemsHelper.getBaseValue = function(obj) {
    	return obj.getBaseValue();
    }    
    /*
     Returns the pixel for the minimum chart value
     The coordinate (0, 0) is at the upper-left corner of the canvas
	  
	 @param obj scale native object instance
	 @return the pixel for the minimum chart value
    */
    CharbaJsItemsHelper.getBasePixel = function(obj) {
    	return obj.getBasePixel();
    }    
    /*
	 Returns a set of predefined style properties that should be used to represent the dataset or the data if the index is specified.
	  
	 @param controller controller instance
	 @param dataIndex index of data
	 @return a set of predefined style properties that should be used to represent the dataset or the data if the index is specified
	 */
	CharbaJsItemsHelper.getDatasetControllerStyle = function(controller, dataIndex) {
		return controller.getStyle(dataIndex);
	}
    /*
		JsDateAdapterHelper is an object to create a CHART.JS date adapter  
	*/
    function CharbaJsDateAdapterHelper() {} 
    /*
	 Returns a date adapter instance.
	  
	 @param options options to configure date adapter.
	 @return a date adapter instance
    */
    CharbaJsDateAdapterHelper.create = function(options) {
		return new Chart._adapters._date(options);
    }
    /*
		CharbaJsCallbacksHelper is an object to wrap native object into native scriptable context  
	*/
    function CharbaJsCallbacksHelper() {} 
    /*
	 Returns a native context from a native object.
	  
	 @param obj native objetc to wrap.
	 @return a native context instance
    */    
    CharbaJsCallbacksHelper.wrap = function(obj){
		return obj;
	}
    