    function JsFactory() {};
    JsFactory.newArray = function() {
    	return new Array()
    }
    JsFactory.newObject = function() {
    	return new Object()
    }
    JsFactory.remove = function(obj, key) {
    	delete obj[key];
    }
    JsFactory.newCallbackProxy = function() {
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