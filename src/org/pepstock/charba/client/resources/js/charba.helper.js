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