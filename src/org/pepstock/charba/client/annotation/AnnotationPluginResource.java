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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-plugin-annotation.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationPluginResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-plugin-annotation.min.js
	private static final String[] CONTENT = {
		"/*!",
		"* chartjs-plugin-annotation v1.0.0-beta.3",
		"* undefined",
		" * (c) 2021 chartjs-plugin-annotation Contributors",
		" * Released under the MIT License",
		" */",
		"!function(e,t){\"object\"==typeof exports&&\"undefined\"!=typeof module?module.exports=t(require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\"],t):(e=\"undefined\"!=typeof globalThis?globalThis:e||self)[\"chartjs-plugin-annotation\"]=t(e.Chart,e.Chart.helpers)}(this,(function(e,t){\"use strict\";const n=[\"click\",\"dblclick\"],o=[\"enter\",\"leave\"],i=n.concat(o);function s(e,t,n){if(t.listened)switch(n.type){case\"mousemove\":case\"mouseout\":!function(e,t,n){if(!t.moveListened)return;let o;\"mousemove\"===n.type&&(o=r(t.elements,n));const i=t.hovered;t.hovered=o,function(e,t,n,o){n&&n!==o&&a(e,t,n.options.leave||t.listeners.leave,n);o&&o!==n&&a(e,t,o.options.enter||t.listeners.enter,o)}(e,t,i,o)}(e,t,n);break;case\"click\":!function(e,t,n){const o=t.options,i=t.listeners,s=r(t.elements,n);if(s){const n=s.options,r=n.dblclick||i.dblclick,l=n.click||i.click;s.clickTimeout?(clearTimeout(s.clickTimeout),del",
		"ete s.clickTimeout,a(e,t,r,s)):r?s.clickTimeout=setTimeout((()=>{delete s.clickTimeout,a(e,t,l,s)}),o.dblClickSpeed):a(e,t,l,s)}}(e,t,n)}}function a(e,n,o,i){t.callback(o,[{chart:e,element:i}])}function r(e,n){let o=Number.POSITIVE_INFINITY;return e.filter((e=>e.options.display&&e.inRange(n.x,n.y))).reduce(((e,i)=>{const s=i.getCenterPoint(),a=t.distanceBetweenPoints(n,s);return a<o?(e=[i],o=a):a===o&&e.push(i),e}),[]).sort(((e,t)=>e._index-t._index)).slice(0,1)[0]}const l=Math.PI,c=l/2;function d(e,n,o){return n=\"number\"==typeof n?n:e.parse(n),t.isFinite(n)?e.getPixelForValue(n):o}function h(e,t,n,o,i,s){if(e.beginPath(),s){const a=Math.min(s,i/2,o/2),r=t+a,d=n+a,h=t+o-a,f=n+i-a;e.moveTo(t,d),r<h&&d<f?(e.arc(r,d,a,-l,-c),e.arc(h,d,a,-c,0),e.arc(h,f,a,0,c),e.arc(r,f,a,c,l)):r<h?(e.moveTo(r,n),e.arc(h,d,a,-c,c),e.arc(r,d,a,c,l+c)):d<f?(e.arc(r,d,a,-l,0),e.arc(r,f,a,0,l)):e.arc(r,d,a,-l,l),e.closePath(),e.moveTo(t,n)}else e.rect(t,n,o,i)}class f extends e.Element{inRange(e,t,n){const{x:",
		"o,y:i,width:s,height:a}=this.getProps([\"x\",\"y\",\"width\",\"height\"],n);return e>=o&&e<=o+s&&t>=i&&t<=i+a}getCenterPoint(e){const{x:t,y:n,width:o,height:i}=this.getProps([\"x\",\"y\",\"width\",\"height\"],e);return{x:t+o/2,y:n+i/2}}draw(e){const{x:t,y:n,width:o,height:i,options:s}=this;e.save(),e.lineWidth=s.borderWidth,e.strokeStyle=s.borderColor,e.fillStyle=s.backgroundColor,e.setLineDash(s.borderDash),e.lineDashOffset=s.borderDashOffset,h(e,t,n,o,i,s.cornerRadius),e.fill(),e.stroke(),e.restore()}resolveElementProperties(e,t){const n=e.scales[t.xScaleID],o=e.scales[t.yScaleID];let i,s,{top:a,left:r,bottom:l,right:c}=e.chartArea;return n||o?(n&&(i=d(n,t.xMin,r),s=d(n,t.xMax,c),r=Math.min(i,s),c=Math.max(i,s)),o&&(i=d(o,t.yMin,l),s=d(o,t.yMax,a),a=Math.min(i,s),l=Math.max(i,s)),{x:r,y:a,x2:c,y2:l,width:c-r,height:l-a}):{options:{}}}}f.id=\"boxAnnotation\",f.defaults={display:!0,xScaleID:\"x\",yScaleID:\"y\",borderDash:[],borderDashOffset:0,borderWidth:1,cornerRadius:0},f.defaultRout",
		"es={borderColor:\"color\",backgroundColor:\"color\"};const u=Math.PI,y=(e,t,n)=>({x:e.x+n*(t.x-e.x),y:e.y+n*(t.y-e.y)});class x extends e.Element{intersects(e,t,n){n=n||.001;const o=this,i={x:o.x,y:o.y},s={x:o.x2,y:o.y2},a=((e,t,n)=>y(t,n,Math.abs((e-t.x)/(n.x-t.x))).y)(e,i,s),r=((e,t,n)=>y(t,n,Math.abs((e-t.y)/(n.y-t.y))).x)(t,i,s);return(!isFinite(a)||Math.abs(t-a)<n)&&(!isFinite(r)||Math.abs(e-r)<n)}labelIsVisible(){const e=this.options.label;return e&&e.enabled&&e.content}isOnLabel(e,t){const{labelRect:n}=this;if(!n)return!1;const{x:o,y:i}=(s={x:e,y:t},a=n,r=-n.rotation,l=Math.cos(r),c=Math.sin(r),d=a.x,h=a.y,{x:d+l*(s.x-d)-c*(s.y-h),y:h+c*(s.x-d)+l*(s.y-h)});var s,a,r,l,c,d,h;const f=n.width/2,u=n.height/2;return o>=n.x-f&&o<=n.x+f&&i>=n.y-u&&i<=n.y+u}inRange(e,t){const n=this.options.borderWidth||1;return this.intersects(e,t,n)||this.isOnLabel(e,t)}getCenterPoint(){return{x:(this.x2+this.x)/2,y:(this.y2+this.y)/2}}draw(e){const{x:t,y:n,x2:o,y2:i,options:s}=this;e.save(),e.lineWidt",
		"h=s.borderWidth,e.strokeStyle=s.borderColor,e.setLineDash(s.borderDash),e.lineDashOffset=s.borderDashOffset,e.beginPath(),e.moveTo(t,n),e.lineTo(o,i),e.stroke(),e.restore()}drawLabel(e,n){this.labelIsVisible()&&(e.save(),function(e,n,o){const i=n.options.label;e.font=t.toFontString(i.font),e.textAlign=\"center\";const{width:s,height:a}=function(e,n){const o=n.content;if(o instanceof Image)return{width:p(o.width,n.width)+2*n.xPadding,height:p(o.height,n.height)+2*n.yPadding};const i=t.isArray(o)?o:[o],s=i.length;let a=0;for(let t=0;t<s;t++){const n=i[t];b.has(n)||b.set(n,e.measureText(n).width),a=Math.max(a,b.get(n))}return a+=2*n.xPadding,{width:a,height:s*n.font.size+(s+1)*n.yPadding}}(e,i),r=n.labelRect=function(e,n,o,i){const s=e.options.label,{xAdjust:a,yAdjust:r,xPadding:l,yPadding:c,position:d}=s,h={x:e.x,y:e.y},f={x:e.x2,y:e.y2},x=\"auto\"===s.rotation?function(e){const{x:t,y:n,x2:o,y2:i}=e,s=Math.atan2(i-n,o-t);return s>u/2?s-u:s<u/-2?s+u:s}(e):t.toRadians(s.rotation),p=function",
		"(e,t,n){const o=Math.cos(n),i=Math.sin(n);return{w:Math.abs(e*o)+Math.abs(t*i),h:Math.abs(e*i)+Math.abs(t*o)}}(n,o,x),b=function(e,t,n,o){let i=.5;const s=function(e,t){const{x:n,x2:o,y:i,y2:s}=e,a=Math.min(i,s)-t.top,r=Math.min(n,o)-t.left,l=t.bottom-Math.max(i,s),c=t.right-Math.max(n,o);return{x:Math.min(r,c),y:Math.min(a,l),dx:r<c?1:-1,dy:a<l?1:-1}}(e,o),a=e.options.label;\"start\"===t?i=m({w:e.x2-e.x,h:e.y2-e.y},n,a,s):\"end\"===t&&(i=1-m({w:e.x-e.x2,h:e.y-e.y2},n,a,s));return i}(e,d,p,i),w=y(h,f,b),D={size:p.w,min:i.left,max:i.right,padding:l},M={size:p.h,min:i.top,max:i.bottom,padding:c};return{x:g(w.x,D)+a,y:g(w.y,M)+r,width:n,height:o,rotation:x}}(n,s,a,o);if(e.translate(r.x,r.y),e.rotate(r.rotation),e.fillStyle=i.backgroundColor,h(e,-s/2,-a/2,s,a,i.cornerRadius),e.fill(),e.fillStyle=i.color,t.isArray(i.content)){let t=-a/2+i.yPadding;for(let n=0;n<i.content.length;n++)e.textBaseline=\"top\",e.fillText(i.content[n],-s/2+s/2,t),t+=i.font.size+i.yPadding}else if(i.content instance",
		"of Image){const t=-s/2+i.xPadding,n=-a/2+i.yPadding;e.drawImage(i.content,t,n,s-2*i.xPadding,a-2*i.yPadding)}else e.textBaseline=\"middle\",e.fillText(i.content,0,0)}(e,this,n),e.restore())}resolveElementProperties(e,t){const n=e.scales[t.scaleID];let o,i,{top:s,left:a,bottom:r,right:l}=e.chartArea;if(n)o=d(n,t.value,NaN),i=d(n,t.endValue,o),n.isHorizontal()?(a=o,l=i):(s=o,r=i);else{const n=e.scales[t.xScaleID],o=e.scales[t.yScaleID];n&&(a=d(n,t.xMin,a),l=d(n,t.xMax,l)),o&&(s=d(o,t.yMin,s),r=d(o,t.yMax,r))}return{x:a,y:s,x2:l,y2:r,width:l-a,height:r-s}}}function p(e,t){return\"number\"==typeof t?t:\"string\"==typeof t?(\"string\"==typeof(n=t)&&n.endsWith(\"%\")&&parseFloat(n)/100)*e:e;var n}x.id=\"lineAnnotation\",x.defaults={display:!0,borderWidth:2,borderDash:[],borderDashOffset:0,label:{backgroundColor:\"rgba(0,0,0,0.8)\",font:{family:e.defaults.font.family,size:e.defaults.font.size,style:\"bold\"},color:\"#fff\",xPadding:6,yPadding:6,rotation:0,cornerRadius:6,position:\"center\",xAd",
		"just:0,yAdjust:0,enabled:!1,content:null}},x.defaultRoutes={borderColor:\"color\"};const b=new Map;function m(e,t,n,o){const{xPadding:i,yPadding:s}=n,a=e.w*o.dx,r=e.h*o.dy,l=a>0&&(t.w/2+i-o.x)/a,c=r>0&&(t.h/2+s-o.y)/r;return Math.max(Math.min(Math.max(l,c),.25),0)}function g(e,t){const{size:n,min:o,max:i,padding:s}=t,a=n/2;return n>i-o?(i+o)/2:(o>=e-s-a&&(e=o+s+a),i<=e+s+a&&(e=i-s-a),e)}class w extends f{inRange(e,t){return function(e,t){const{width:n,height:o}=t,i=t.getCenterPoint(!0),s=n/2,a=o/2;if(s<=0||a<=0)return!1;return Math.pow(e.x-i.x,2)/Math.pow(s,2)+Math.pow(e.y-i.y,2)/Math.pow(a,2)<=1}({x:e,y:t},this)}draw(e){const{width:t,height:n,options:o}=this,i=this.getCenterPoint(!0);e.save(),e.beginPath(),e.lineWidth=o.borderWidth,e.strokeStyle=o.borderColor,e.fillStyle=o.backgroundColor,e.setLineDash(o.borderDash),e.lineDashOffset=o.borderDashOffset,e.ellipse(i.x,i.y,n/2,t/2,Math.PI/2,0,2*Math.PI),e.fill(),e.stroke(),e.restore()}}w.id=\"ellipseAnnotation\",w.defaults={display:!0,xSca",
		"leID:\"x\",yScaleID:\"y\",borderDash:[],borderDashOffset:0,borderWidth:1},w.defaultRoutes={borderColor:\"color\",backgroundColor:\"color\"};class D extends e.Element{inRange(e,t){const{width:n,options:o}=this,i=this.getCenterPoint(!0),s=n/2+o.borderWidth;return!(s<=0)&&Math.pow(e-i.x,2)+Math.pow(t-i.y,2)<=Math.pow(s,2)}getCenterPoint(e){const{x:t,y:n}=this.getProps([\"x\",\"y\"],e);return{x:t,y:n}}draw(e){const{x:t,y:n,width:o,options:i}=this;e.save(),e.lineWidth=i.borderWidth,e.strokeStyle=i.borderColor,e.fillStyle=i.backgroundColor,e.setLineDash(i.borderDash),e.lineDashOffset=i.borderDashOffset,e.beginPath(),e.arc(t,n,o/2,0,2*Math.PI),e.fill(),e.stroke(),e.restore()}resolveElementProperties(e,t){const{chartArea:n,scales:o}=e,i=o[t.xScaleID],s=o[t.yScaleID];let a=n.width/2,r=n.height/2;return i&&(a=d(i,t.xValue,a)),s&&(r=d(s,t.yValue,r)),{x:a,y:r,width:2*t.radius,height:2*t.radius}}}D.id=\"pointAnnotation\",D.defaults={display:!0,borderDash:[],borderDashOffset:0,borderWidth:1,radius:10",
		",xScaleID:\"x\",yScaleID:\"y\"},D.defaultRoutes={borderColor:\"color\",backgroundColor:\"color\"};const M=new Map,P={box:f,line:x,ellipse:w,point:D};var v={id:\"annotation\",afterRegister(){e.Chart.register(P)},afterUnregister(){e.Chart.unregister(P)},beforeInit(e){M.set(e,{elements:[],options:{},listeners:{},listened:!1,moveListened:!1,scales:new Set})},beforeUpdate(e,n,o){const i=M.get(e);i.options=t.clone(o);const s=i.options.annotations;if(t.isObject(s)){const n=new Array;Object.keys(s).forEach((o=>{let i=s[o];t.isObject(i)&&(i.id=o,n.push(k(e,i)))})),i.options.annotations=n}else if(t.isArray(s))for(var a=0;a<s.length;a++)s[a]=k(e,s[a]);else i.options.annotations=[]},afterDataLimits(e,n){if(\"category\"!==n.scale.type){const o=M.get(e);!function(e,n){const o=n.annotations.filter((e=>e.display)),i=function(e,n){const o=e.axis,i=e.id,s=e.axis+\"ScaleID\",a=n.filter((e=>e[s]===i||e.scaleID===i));let r=t.valueOrDefault(e.min,Number.NEGATIVE_INFINITY),l=t.valueOrDefault(e.max,Number.POSI",
		"TIVE_INFINITY);return a.forEach((t=>{[\"value\",\"endValue\",o+\"Min\",o+\"Max\",o+\"Value\"].forEach((n=>{if(n in t){const o=e.parse(t[n]);r=Math.min(r,o),l=Math.max(l,o)}}))})),{min:r,max:l}}(e,o);let s=!1;t.isFinite(i.min)&&void 0===e.options.min&&void 0===e.options.suggestedMin&&(s=e.min!==i.min,e.min=i.min);t.isFinite(i.max)&&void 0===e.options.max&&void 0===e.options.suggestedMax&&(s=e.max!==i.max,e.max=i.max);s&&\"function\"==typeof e.handleTickRangeOptions&&e.handleTickRangeOptions()}(n.scale,o.options)}},afterUpdate(s,a){const r=M.get(s);!function(e,t){const s=t.options,a=s.annotations||[];t.listened=!1,t.moveListened=!1,i.forEach((e=>{\"function\"==typeof s[e]&&(t.listened=!0,t.listeners[e]=s[e])})),o.forEach((e=>{\"function\"==typeof s[e]&&(t.moveListened=!0)})),t.listened&&t.moveListened||a.forEach((e=>{t.listened||n.forEach((n=>{\"function\"==typeof e[n]&&(t.listened=!0)})),t.moveListened||o.forEach((n=>{\"function\"==typeof e[n]&&(t.listened=!0,t.moveListened=!0)}))}))}(0,",
		"r),function(n,o,i){const s=o.options,a=n.options.animation,r=a&&t.merge({},[a,s.animation]),l=function(t,n,o){if(\"reset\"===o||\"none\"===o||\"resize\"===o)return I;return new e.Animations(t,n)}(n,r,i),c=s.annotations||[],d=function(e,t){const n=t.length,o=e.length;if(o<n){const t=n-o;e.splice(o,0,...new Array(t))}else o>n&&e.splice(n,o-n);return e}(o.elements,c);for(let e=0;e<c.length;e++){const t=c[e];let o=d[e];const i=P[t.type]||P.line;o&&o instanceof i||(o=d[e]=new i);const s=o.resolveElementProperties(n,t);s.options=t,l.update(o,s)}}(s,r,a.mode)},beforeDatasetsDraw(e){T(e,\"beforeDatasetsDraw\")},afterDatasetsDraw(e){T(e,\"afterDatasetsDraw\")},beforeDraw(e){T(e,\"beforeDraw\")},afterDraw(e){T(e,\"afterDraw\")},beforeEvent(e,t){s(e,M.get(e),t.event)},destroy(e){M.delete(e)},_getState:e=>M.get(e),defaults:{drawTime:\"afterDatasetsDraw\",dblClickSpeed:350,annotations:{},animation:{numbers:{properties:[\"x\",\"y\",\"x2\",\"y2\",\"width\",\"height\"],type:\"number\"}}}};const I={upda",
		"te:Object.assign};function k(e,n){const o=P[n.type]||P.line,i=t.merge(Object.create(null),[o.defaults,e.options.elements[o.id],n]);return i.display=!!t.resolve([i.display,!0],{chart:e,options:i}),i}function T(e,n){const{ctx:o,chartArea:i}=e,s=M.get(e),a=s.options,r=s.elements.filter((e=>e.options.display));t.clipArea(o,i),r.forEach((e=>{(e.options.drawTime||a.drawTime||n)===n&&e.draw(o)})),t.unclipArea(o),r.forEach((e=>{\"drawLabel\"in e&&e.options.label&&(e.options.label.drawTime||e.options.drawTime||a.drawTime||n)===n&&e.drawLabel(o,i)}))}return e.Chart.register(v),v}));",
		"//# sourceMappingURL=chartjs-plugin-annotation.min.js.map"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-annotation.min.js</code> content.
	 */
	AnnotationPluginResource() {
		super(ResourceName.ANNOTATION_PLUGIN, CONTENT);
	}

}
