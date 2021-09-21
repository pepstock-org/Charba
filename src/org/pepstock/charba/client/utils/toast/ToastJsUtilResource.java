/**
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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>charba.toast.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ToastJsUtilResource extends AbstractInjectableResource {

	// encoded javascript content of charba.toast.min.js
	private static final String[] CONTENT = {
		"CharbaToast={init:function $CharbaToast$init$(){var $container$$=document.createElement(\"div\");$container$$.id=\"ct-container\";document.body.appendChild($container$$);CharbaToast.create=function $CharbaToast$create$($pId$$,$pOptions$$){function $removeToast$$(){document.getElementById(\"ct-container\").removeChild($toasting$$);var $style$$=document.querySelector(\"#style-\"+$toasting$$.id);$style$$&&$style$$.remove();$result$$.status=\"shown\";$result$$.closeDateTime=Date.now();CharbaToast.currentOpenItems--;",
		"\"function\"===typeof $options$$.onClose&&$options$$.onClose.apply(this,[$result$$]);\"function\"===typeof CharbaToast.onClose&&CharbaToast.onClose.apply(this,[$result$$])}var $$jscomp$this$$=this,$result$$=Object.assign({},$pOptions$$||{}),$options$$=Object.assign({},CharbaToast.defaults,CharbaToast.overrides,$result$$),$font$0_hasTitle_normFont$1_rules_title$$=\"string\"===typeof $options$$.title.content,$cssAnimation_hasLabel_icon_progressBar_text$$=Array.isArray($options$$.label.content)&&0<$options$$.label.content.length;",
		"if(!$font$0_hasTitle_normFont$1_rules_title$$&&!$cssAnimation_hasLabel_icon_progressBar_text$$)return null;var $toasting$$=document.createElement(\"div\");$toasting$$.id=\"toast-\"+$pId$$;$toasting$$.className=\"ct-toast\";var $wrapper$$=document.createElement(\"div\");$wrapper$$.style.borderRadius=$options$$.borderRadius+\"px\";$options$$.hideShadow&&($wrapper$$.style.boxShadow=\"none\");if($font$0_hasTitle_normFont$1_rules_title$$){$font$0_hasTitle_normFont$1_rules_title$$=document.createElement(\"h4\");$font$0_hasTitle_normFont$1_rules_title$$.className=",
		"\"ct-title\";$font$0_hasTitle_normFont$1_rules_title$$.innerHTML=$options$$.title.content;\"string\"===typeof $options$$.title.color&&($font$0_hasTitle_normFont$1_rules_title$$.style.color=$options$$.title.color);if(\"object\"===typeof $options$$.title.font){var $font$$=Object.assign({},CharbaToast.defaults.titleFont,$options$$.title.font),$font$$=CharbaToast.helper.toFont.apply(this,[$font$$]);$font$0_hasTitle_normFont$1_rules_title$$.style.font=$font$$.string}$wrapper$$.appendChild($font$0_hasTitle_normFont$1_rules_title$$)}$cssAnimation_hasLabel_icon_progressBar_text$$&&",
		"($cssAnimation_hasLabel_icon_progressBar_text$$=document.createElement(\"p\"),$cssAnimation_hasLabel_icon_progressBar_text$$.className=\"ct-text\",$cssAnimation_hasLabel_icon_progressBar_text$$.innerHTML=$options$$.label.content.join(\"<br/>\"),\"string\"===typeof $options$$.label.color&&($cssAnimation_hasLabel_icon_progressBar_text$$.style.color=$options$$.label.color),\"object\"===typeof $options$$.label.font&&($font$0_hasTitle_normFont$1_rules_title$$=Object.assign({},CharbaToast.defaults.labelFont,$options$$.label.font),",
		"$font$0_hasTitle_normFont$1_rules_title$$=CharbaToast.helper.toFont.apply(this,[$font$0_hasTitle_normFont$1_rules_title$$]),$cssAnimation_hasLabel_icon_progressBar_text$$.style.font=$font$0_hasTitle_normFont$1_rules_title$$.string),$wrapper$$.appendChild($cssAnimation_hasLabel_icon_progressBar_text$$));$options$$.icon&&$options$$.icon.src&&($wrapper$$.classList+=\" img\",$cssAnimation_hasLabel_icon_progressBar_text$$=document.createElement(\"img\"),$cssAnimation_hasLabel_icon_progressBar_text$$.src=$options$$.icon.src,",
		"$cssAnimation_hasLabel_icon_progressBar_text$$.className=\"ct-icon\",$wrapper$$.appendChild($cssAnimation_hasLabel_icon_progressBar_text$$));$options$$.hideProgressBar||($cssAnimation_hasLabel_icon_progressBar_text$$=document.createElement(\"style\"),$cssAnimation_hasLabel_icon_progressBar_text$$.id=\"style-\"+$toasting$$.id,$cssAnimation_hasLabel_icon_progressBar_text$$.type=\"text/css\",$font$0_hasTitle_normFont$1_rules_title$$=document.createTextNode(\"\\n            @keyframes animate-\"+$pId$$+\" {\\n              0% {\\n                width: 100%\\n              }\\n              100% {\\n                width: 0%;\\n              }\\n            }\\n        \"),",
		"$cssAnimation_hasLabel_icon_progressBar_text$$.appendChild($font$0_hasTitle_normFont$1_rules_title$$),document.getElementsByTagName(\"head\")[0].appendChild($cssAnimation_hasLabel_icon_progressBar_text$$),$cssAnimation_hasLabel_icon_progressBar_text$$=document.createElement(\"div\"),$cssAnimation_hasLabel_icon_progressBar_text$$.classList=\"progress-bar\",$cssAnimation_hasLabel_icon_progressBar_text$$.style.animationName=\"animate-\"+$pId$$,$cssAnimation_hasLabel_icon_progressBar_text$$.style.animationDuration=",
		"$options$$.timeout/1E3+\"s\",$cssAnimation_hasLabel_icon_progressBar_text$$.style.animationTimingFunction=\"linear\",$cssAnimation_hasLabel_icon_progressBar_text$$.style.animationFillMode=\"forwards\",$options$$.progressBarType&&($cssAnimation_hasLabel_icon_progressBar_text$$.classList+=\" \"+$options$$.progressBarType),$wrapper$$.appendChild($cssAnimation_hasLabel_icon_progressBar_text$$));\"function\"===typeof $options$$.onClick&&$toasting$$.addEventListener(\"click\",function($event$$){return $options$$.onClick.apply($$jscomp$this$$,",
		"[$result$$,$event$$])});$result$$.status=\"showing\";$toasting$$.hide=function $$toasting$$$hide$(){$toasting$$.className+=\" ct-fadeOut\";$toasting$$.addEventListener(\"animationend\",$removeToast$$,!1);return null};$options$$.autoHide&&setTimeout($toasting$$.hide,$options$$.timeout+200);$wrapper$$.className=$options$$.type?$wrapper$$.className+(\" ct-\"+$options$$.type):$wrapper$$.className+\" ct-default\";$toasting$$.addEventListener(\"click\",$toasting$$.hide);$toasting$$.appendChild($wrapper$$);document.getElementById(\"ct-container\").appendChild($toasting$$);",
		"$result$$.id=$pId$$;$result$$.openDateTime=Date.now();$result$$.element=$toasting$$;CharbaToast.currentOpenItems++;\"function\"===typeof $options$$.onOpen&&$options$$.onOpen.apply(this,[$result$$]);return $result$$}},create:function $CharbaToast$create$(){console.error(\"DOM has not finished loading.\\n\\tInvoke create method when DOMs readyState is complete\")},helper:{lineHeightRegExp:new RegExp(/^(normal|(\\d+(?:\\.\\d+)?)(px|em|%)?)$/),fontStyleRegExp:new RegExp(/^(normal|italic|initial|inherit|unset|(oblique( -?[0-9]?[0-9]deg)?))$/),",
		"toLineHeight:function $CharbaToast$helper$toLineHeight$($value$$,$size$$){var $matches$$=(\"\"+$value$$).match(CharbaToast.helper.lineHeightRegExp);if(!$matches$$||\"normal\"===$matches$$[1])return 1.2*$size$$;$value$$=+$matches$$[2];switch($matches$$[3]){case \"px\":return $value$$;case \"%\":$value$$/=100}return $size$$*$value$$},toFont:function $CharbaToast$helper$toFont$($font$$4_options$$){var $size$$=$font$$4_options$$.size;\"string\"===typeof $size$$&&($size$$=parseInt($size$$,10));var $style$$=$font$$4_options$$.style;",
		"$style$$&&!(\"\"+$style$$).match(CharbaToast.helper.fontStyleRegExp)&&(console.warn('Invalid font style specified: \"'+$style$$+'\"'),$style$$=\"\");$font$$4_options$$={family:$font$$4_options$$.family,lineHeight:CharbaToast.helper.toLineHeight.apply(this,[$font$$4_options$$.lineHeight,$size$$]),size:$size$$,style:$style$$,weight:$font$$4_options$$.weight,string:\"\"};$font$$4_options$$.string=($font$$4_options$$.style?$font$$4_options$$.style+\" \":\"\")+($font$$4_options$$.weight?$font$$4_options$$.weight+",
		"\" \":\"\")+$font$$4_options$$.size+\"px \"+$font$$4_options$$.family;return $font$$4_options$$}},defaults:{autoHide:!0,borderRadius:8,hideProgressBar:!1,hideShadow:!1,icon:void 0,onClick:void 0,onClose:void 0,onOpen:void 0,progressBarType:void 0,title:{content:void 0,color:void 0,font:void 0},label:{content:void 0,color:void 0,font:void 0},timeout:4E3,type:void 0,titleFont:{family:'system-ui, -apple-system, \"Segoe UI\", Roboto, Helvetica, Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\"',lineHeight:1.2,",
		"size:15,style:\"normal\",weight:\"bold\"},labelFont:{family:'system-ui, -apple-system, \"Segoe UI\", Roboto, Helvetica, Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\"',lineHeight:\"20px\",size:14,style:\"normal\",weight:\"normal\"}},overrides:{},onClose:void 0,currentOpenItems:0};Object.freeze(CharbaToast.defaults);Object.freeze(CharbaToast.helper);\"complete\"===document.readyState?CharbaToast.init():window.addEventListener(\"DOMContentLoaded\",CharbaToast.init);"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.toast.min.js</code> content.
	 */
	ToastJsUtilResource() {
		super(ResourceName.TOAST_JS_UTIL, CONTENT);
	}

}
