    function JsHelper() {};
    JsHelper.newArray = function() {
    	return new Array()
    }
    JsHelper.newObject = function() {
    	return new Object()
    }
    JsHelper.remove = function(obj, key) {
    	delete obj[key];
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
    JsHelper.newCallbackProxy = function() {
    	var obj = new Object();
    	obj.callback = null;
    	obj.proxy = function() {
        	if (obj.callback != null && typeof obj.callback === 'function'){
        	    //console.log(this);
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