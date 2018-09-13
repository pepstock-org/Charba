    function JsFactory() {};
    JsFactory.newArray = function() {
    	return new Array()
    }
    JsFactory.newObject = function() {
    	return new Object()
    }
    JsFactory.newCallbackProxy = function() {
    	var obj = new Object();
    	obj.callback = null;
    	obj.proxy = function() {
    		console.log(this);
    		console.log(Array.prototype.slice.call(arguments));
		};
    	return obj;
    }