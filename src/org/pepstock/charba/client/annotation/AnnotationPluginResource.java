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
final class AnnotationPluginResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-plugin-annotation.min.js
	private static final String[] CONTENT = {
		"/*!",
		"* chartjs-plugin-annotation v2.0.1",
		"* https://www.chartjs.org/chartjs-plugin-annotation/index",
		" * (c) 2022 chartjs-plugin-annotation Contributors",
		" * Released under the MIT License",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?module.exports=e(require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\"],e):(t=\"undefined\"!=typeof globalThis?globalThis:t||self)[\"chartjs-plugin-annotation\"]=e(t.Chart,t.Chart.helpers)}(this,(function(t,e){\"use strict\";const o={modes:{point:(t,e)=>r(t,e,{intersect:!0}),nearest:(t,o,n)=>function(t,o,n){let i=Number.POSITIVE_INFINITY;return r(t,o,n).reduce(((t,r)=>{const s=r.getCenterPoint(),a=function(t,e,o){if(\"x\"===o)return{x:t.x,y:e.y};if(\"y\"===o)return{x:e.x,y:t.y};return e}(o,s,n.axis),d=e.distanceBetweenPoints(o,a);return d<i?(t=[r],i=d):d===i&&t.push(r),t}),[]).sort(((t,e)=>t._index-e._index)).slice(0,1)}(t,o,n),x:(t,e,o)=>r(t,e,{intersect:o.intersect,axis:\"x\"}),y:(t,e,o)=>r(t,e,{intersect:o.intersect,axis:\"y\"})}};function n(t,e,n){return(o.modes[n.mode]||o.modes.nearest)(t,e,n)}function r(t,e,o){return t.visibleElem",
		"ents.filter((t=>o.intersect?t.inRange(e.x,e.y):function(t,e,o){return\"x\"!==o&&\"y\"!==o?t.inRange(e.x,e.y,\"x\",!0)||t.inRange(e.x,e.y,\"y\",!0):t.inRange(e.x,e.y,o,!0)}(t,e,o.axis)))}const i=[\"enter\",\"leave\"],s=i.concat(\"click\");function a(t,e,o){if(t.listened)switch(e.type){case\"mousemove\":case\"mouseout\":return function(t,e,o){if(!t.moveListened)return;let r;r=\"mousemove\"===e.type?n(t,e,o.interaction):[];const i=t.hovered;t.hovered=r;const s={state:t,event:e};let a=d(s,\"leave\",i,r);return d(s,\"enter\",r,i)||a}(t,e,o);case\"click\":return function(t,e,o){const r=t.listeners,i=n(t,e,o.interaction);let s;for(const t of i)s=l(t.options.click||r.click,t,e)||s;return s}(t,e,o)}}function d({state:t,event:e},o,n,r){let i;for(const s of n)r.indexOf(s)<0&&(i=l(s.options[o]||t.listeners[o],s,e)||i);return i}function l(t,o,n){return!0===e.callback(t,[o.$context,n])}const c=(t,e)=>e>t||t.length>e.length&&t.slice(0,e.length)===e,h=.001,u=(t,e,o)=>Math.min(o,Math.max(e,t));function ",
		"f(t,e,o){for(const n of Object.keys(t))t[n]=u(t[n],e,o);return t}function x(t,{x:e,y:o,x2:n,y2:r},i,s){const a=s/2,d=t.x>=e-a-h&&t.x<=n+a+h,l=t.y>=o-a-h&&t.y<=r+a+h;return\"x\"===i?d:(\"y\"===i||d)&&l}function y(t,e){const{centerX:o,centerY:n}=t.getProps([\"centerX\",\"centerY\"],e);return{x:o,y:n}}const p=t=>\"string\"==typeof t&&t.endsWith(\"%\"),b=t=>u(parseFloat(t)/100,0,1);function g(t,e){return\"start\"===e?0:\"end\"===e?t:p(e)?b(e)*t:t/2}function m(t,e){return\"number\"==typeof e?e:p(e)?b(e)*t:t}function w(t){return e.isObject(t)?{x:e.valueOrDefault(t.x,\"center\"),y:e.valueOrDefault(t.y,\"center\")}:{x:t=e.valueOrDefault(t,\"center\"),y:t}}function v(t){return t&&(e.defined(t.xValue)||e.defined(t.yValue))}const S=new Map;function M(t){if(t&&\"object\"==typeof t){const e=t.toString();return\"[object HTMLImageElement]\"===e||\"[object HTMLCanvasElement]\"===e}}function C(t,{x:o,y:n},r){r&&(t.translate(o,n),t.rotate(e.toRadians(r)),t.translate(-o,-n))}function D(t,e){if(e&&e.border",
		"Width)return t.lineCap=e.borderCapStyle,t.setLineDash(e.borderDash),t.lineDashOffset=e.borderDashOffset,t.lineJoin=e.borderJoinStyle,t.lineWidth=e.borderWidth,t.strokeStyle=e.borderColor,!0}function j(t,e){t.shadowColor=e.backgroundShadowColor,t.shadowBlur=e.shadowBlur,t.shadowOffsetX=e.shadowOffsetX,t.shadowOffsetY=e.shadowOffsetY}function k(t,o){const n=o.content;if(M(n))return{width:m(n.width,o.width),height:m(n.height,o.height)};const r=e.toFont(o.font),i=o.textStrokeWidth,s=e.isArray(n)?n:[n],a=s.join()+r.string+i+(t._measureText?\"-spriting\":\"\");if(!S.has(a)){t.save(),t.font=r.string;const e=s.length;let o=0;for(let n=0;n<e;n++){const e=s[n];o=Math.max(o,t.measureText(e).width+i)}t.restore();const n=e*r.lineHeight+i;S.set(a,{width:o,height:n})}return S.get(a)}function P(t,o,n){const{x:r,y:i,width:s,height:a}=o;t.save(),j(t,n);const d=D(t,n);t.fillStyle=n.backgroundColor,t.beginPath(),e.addRoundedRectPath(t,{x:r,y:i,w:s,h:a,radius:f(e.toTRBLCorners(n.borderRadius),0,Math.min(s,a",
		")/2)}),t.closePath(),t.fill(),d&&(t.shadowColor=n.borderShadowColor,t.stroke()),t.restore()}function O(t,o,n){return o=\"number\"==typeof o?o:t.parse(o),e.isFinite(o)?t.getPixelForValue(o):n}function A(t,e,o){const n=e[o];if(n||\"scaleID\"===o)return n;const r=o.charAt(0),i=Object.values(t).filter((t=>t.axis&&t.axis===r));return i.length?i[0].id:r}function I(t,e){if(t){const o=t.options.reverse;return{start:O(t,e.min,o?e.end:e.start),end:O(t,e.max,o?e.start:e.end)}}}function Y(t,e){const{chartArea:o,scales:n}=t,r=n[A(n,e,\"xScaleID\")],i=n[A(n,e,\"yScaleID\")];let s=o.width/2,a=o.height/2;return r&&(s=O(r,e.xValue,r.left+r.width/2)),i&&(a=O(i,e.yValue,i.top+i.height/2)),{x:s,y:a}}function R(t,e){const o=t.scales,n=o[A(o,e,\"xScaleID\")],r=o[A(o,e,\"yScaleID\")];if(!n&&!r)return{};let{left:i,right:s}=n||t.chartArea,{top:a,bottom:d}=r||t.chartArea;const l=X(n,{min:e.xMin,max:e.xMax,start:i,end:s});i=l.start,s=l.end;const c=X(r,{min:e.yMin,max:e.yMax,start:d,end:a});return a=c.start,d=c.en",
		"d,{x:i,y:a,x2:s,y2:d,width:s-i,height:d-a,centerX:i+(s-i)/2,centerY:a+(d-a)/2}}function W(t,e){if(!v(e)){const o=R(t,e);let n=e.radius;n&&!isNaN(n)||(n=Math.min(o.width,o.height)/2,e.radius=n);const r=2*n;return{x:o.x+e.xAdjust,y:o.y+e.yAdjust,x2:o.x+r+e.xAdjust,y2:o.y+r+e.yAdjust,centerX:o.centerX+e.xAdjust,centerY:o.centerY+e.yAdjust,width:r,height:r}}return function(t,e){const o=Y(t,e),n=2*e.radius;return{x:o.x-e.radius+e.xAdjust,y:o.y-e.radius+e.yAdjust,x2:o.x+e.radius+e.xAdjust,y2:o.y+e.radius+e.yAdjust,centerX:o.x+e.xAdjust,centerY:o.y+e.yAdjust,width:n,height:n}}(t,e)}function X(t,e){const o=I(t,e)||e;return{start:Math.min(o.start,o.end),end:Math.max(o.start,o.end)}}function E(t,e,o){const n=Math.cos(o),r=Math.sin(o),i=e.x,s=e.y;return{x:i+n*(t.x-i)-r*(t.y-s),y:s+r*(t.x-i)+n*(t.y-s)}}function T(t,o,n){const r=function(t,o,n){const r=o.axis,i=o.id,s=r+\"ScaleID\",a={min:e.valueOrDefault(o.min,Number.NEGATIVE_INFINITY),max:e.valueOrDefault(o.max,Number.POSITIVE_INFINITY)};for(const",
		" e of n)e.scaleID===i?V(e,o,[\"value\",\"endValue\"],a):A(t,e,s)===i&&V(e,o,[r+\"Min\",r+\"Max\",r+\"Value\"],a);return a}(t.scales,o,n);let i=z(o,r,\"min\",\"suggestedMin\");i=z(o,r,\"max\",\"suggestedMax\")||i,i&&\"function\"==typeof o.handleTickRangeOptions&&o.handleTickRangeOptions()}function z(t,o,n,r){if(e.isFinite(o[n])&&!function(t,o,n){return e.defined(t[o])||e.defined(t[n])}(t.options,n,r)){const e=t[n]!==o[n];return t[n]=o[n],e}}function _(t,e){for(const o of[\"scaleID\",\"xScaleID\",\"yScaleID\"]){const n=A(e,t,o);n&&!e[n]&&N(t,o)&&console.warn(`No scale found with id '${n}' for annotation '${t.id}'`)}}function N(t,o){if(\"scaleID\"===o)return!0;const n=o.charAt(0);for(const o of[\"Min\",\"Max\",\"Value\"])if(e.defined(t[n+o]))return!0;return!1}function V(t,o,n,r){for(const i of n){const n=t[i];if(e.defined(n)){const t=o.parse(n);r.min=Math.min(r.min,t),r.max=Math.max(r.max,t)}}}class B extends t.Element{inRange(t,o,n,r){const{x:i,y:s}=E({x:t,y:o},this.getCenterPoint(r),e.to",
		"Radians(-this.options.rotation));return x({x:i,y:s},this.getProps([\"x\",\"y\",\"x2\",\"y2\"],r),n,this.options.borderWidth)}getCenterPoint(t){return y(this,t)}draw(t){t.save(),C(t,this.getCenterPoint(),this.options.rotation),P(t,this,this.options),t.restore()}get label(){return this.elements&&this.elements[0]}resolveElementProperties(t,e){const o=R(t,e),{x:n,y:r}=o;return o.elements=[{type:\"label\",optionScope:\"label\",properties:L(t,o,e)}],o.initProperties={x:n,y:r},o}}function H(t,e){const{start:o,end:n,borderWidth:r}=t,{position:i,padding:{start:s,end:a},adjust:d}=e;return o+r/2+d+g(n-r-o-s-a-e.size,i)}function L(t,o,n){const r=n.label;r.backgroundColor=\"transparent\",r.callout.display=!1;const i=w(r.position),s=e.toPadding(r.padding),a=k(t.ctx,r),d=function({properties:t,options:e},o,n,r){const{x:i,x2:s,width:a}=t;return H({start:i,end:s,size:a,borderWidth:e.borderWidth},{position:n.x,padding:{start:r.left,end:r.right},adjust:e.label.xAdjust,size:o.width})}({properties:o,options",
		":n},a,i,s),l=function({properties:t,options:e},o,n,r){const{y:i,y2:s,height:a}=t;return H({start:i,end:s,size:a,borderWidth:e.borderWidth},{position:n.y,padding:{start:r.top,end:r.bottom},adjust:e.label.yAdjust,size:o.height})}({properties:o,options:n},a,i,s),c=a.width+s.width,h=a.height+s.height;return{x:d,y:l,x2:d+c,y2:l+h,width:c,height:h,centerX:d+c/2,centerY:l+h/2,rotation:r.rotation}}B.id=\"boxAnnotation\",B.defaults={adjustScaleRange:!0,backgroundShadowColor:\"transparent\",borderCapStyle:\"butt\",borderDash:[],borderDashOffset:0,borderJoinStyle:\"miter\",borderRadius:0,borderShadowColor:\"transparent\",borderWidth:1,display:!0,label:{backgroundColor:\"transparent\",borderWidth:0,callout:{display:!1},color:\"black\",content:null,display:!1,drawTime:void 0,font:{family:void 0,lineHeight:void 0,size:void 0,style:void 0,weight:\"bold\"},height:void 0,padding:6,position:\"center\",rotation:void 0,textAlign:\"start\",textStrokeColor:void 0,textStrokeWidth:0,width:void 0,xAdjust:0,yAdj",
		"ust:0,z:void 0},rotation:0,shadowBlur:0,shadowOffsetX:0,shadowOffsetY:0,xMax:void 0,xMin:void 0,xScaleID:void 0,yMax:void 0,yMin:void 0,yScaleID:void 0,z:0},B.defaultRoutes={borderColor:\"color\",backgroundColor:\"color\"},B.descriptors={label:{_fallback:!0}};const $=(t,e,o)=>({x:t.x+o*(e.x-t.x),y:t.y+o*(e.y-t.y)}),F=(t,e,o)=>$(e,o,Math.abs((t-e.y)/(o.y-e.y))).x,J=(t,e,o)=>$(e,o,Math.abs((t-e.x)/(o.x-e.x))).y,q=t=>t*t;class U extends t.Element{inRange(t,e,o,n){const r=this.options.borderWidth/2;if(\"x\"!==o&&\"y\"!==o){const o={mouseX:t,mouseY:e};return function(t,{mouseX:e,mouseY:o},n=.001,r){const{x:i,y:s,x2:a,y2:d}=t.getProps([\"x\",\"y\",\"x2\",\"y2\"],r),l=a-i,c=d-s,h=q(l)+q(c),u=0===h?-1:((e-i)*l+(o-s)*c)/h;let f,x;u<0?(f=i,x=s):u>1?(f=a,x=d):(f=i+u*l,x=s+u*c);return q(e-f)+q(o-x)<=n}(this,o,q(r),n)||Q(this,o,n)}const i=((t,e,{x:o,y:n,x2:r,y2:i},s)=>\"y\"===s?{start:Math.min(n,i),end:Math.max(n,i),value:e}:{start:Math.min(o,r),end:Math.max(o,r),value:t})(t,e,this.getProps([\"x\",\"",
		"y\",\"x2\",\"y2\"],n),o);return i.value>=i.start-r&&i.value<=i.end+r||Q(this,{mouseX:t,mouseY:e},n,o)}getCenterPoint(t){return y(this,t)}draw(t){const{x:e,y:o,x2:n,y2:r,options:i}=this;if(t.save(),!D(t,i))return t.restore();j(t,i);const s=Math.atan2(r-o,n-e),a=Math.sqrt(Math.pow(n-e,2)+Math.pow(r-o,2)),{startOpts:d,endOpts:l,startAdjust:c,endAdjust:h}=function(t){const e=t.options,o=e.arrowHeads&&e.arrowHeads.start,n=e.arrowHeads&&e.arrowHeads.end;return{startOpts:o,endOpts:n,startAdjust:ot(t,o),endAdjust:ot(t,n)}}(this);t.translate(e,o),t.rotate(s),t.beginPath(),t.moveTo(0+c,0),t.lineTo(a-h,0),t.shadowColor=i.borderShadowColor,t.stroke(),nt(t,0,c,d),nt(t,a,-h,l),t.restore()}get label(){return this.elements&&this.elements[0]}resolveElementProperties(t,o){const{scales:n,chartArea:r}=t,i=n[o.scaleID],s={x:r.left,y:r.top,x2:r.right,y2:r.bottom};let a,d;if(i)a=O(i,o.value,NaN),d=O(i,o.endValue,a),i.isHorizontal()?(s.x=a,s.x2=d):(s.y=a,s.y2=d);else{const t=n[A(n,o,\"xScaleID\")],e=n[A(n,o,\"",
		"yScaleID\")];t&&Z(s,t,{min:o.xMin,max:o.xMax,start:t.left,end:t.right,startProp:\"x\",endProp:\"x2\"}),e&&Z(s,e,{min:o.yMin,max:o.yMax,start:e.bottom,end:e.top,startProp:\"y\",endProp:\"y2\"})}const{x:l,y:c,x2:h,y2:u}=s,f=function({x:t,y:e,x2:o,y2:n},{top:r,right:i,bottom:s,left:a}){return!(t<a&&o<a||t>i&&o>i||e<r&&n<r||e>s&&n>s)}(s,t.chartArea),x=f?function(t,e,o){const{x:n,y:r}=K(t,e,o),{x:i,y:s}=K(e,t,o);return{x:n,y:r,x2:i,y2:s,width:Math.abs(i-n),height:Math.abs(s-r)}}({x:l,y:c},{x:h,y:u},t.chartArea):{x:l,y:c,x2:h,y2:u,width:Math.abs(h-l),height:Math.abs(u-c)};x.centerX=(h+l)/2,x.centerY=(u+c)/2;const y=function(t,o,n){n.callout.display=!1;const r=n.borderWidth,i=e.toPadding(n.padding),s=k(t.ctx,n),a=s.width+i.width+r,d=s.height+i.height+r;return function(t,o,n,r){const{width:i,height:s,padding:a}=n,{xAdjust:d,yAdjust:l}=o,c={x:t.x,y:t.y},h={x:t.x2,y:t.y2},u=\"auto\"===o.rotation?function(t){const{x:o,y:n,x2:r,y2:i}=t,s=Math.atan2(i-n,r-o);return s>e.PI/2?s-e.PI:s<e.PI/-2?s+e.PI:s",
		"}(t):e.toRadians(o.rotation),f=function(t,e,o){const n=Math.cos(o),r=Math.sin(o);return{w:Math.abs(t*n)+Math.abs(e*r),h:Math.abs(t*r)+Math.abs(e*n)}}(i,s,u),x=function(t,e,o,n){let r;const i=function(t,e){const{x:o,x2:n,y:r,y2:i}=t,s=Math.min(r,i)-e.top,a=Math.min(o,n)-e.left,d=e.bottom-Math.max(r,i),l=e.right-Math.max(o,n);return{x:Math.min(a,l),y:Math.min(s,d),dx:a<=l?1:-1,dy:s<=d?1:-1}}(t,n);r=\"start\"===e.position?tt({w:t.x2-t.x,h:t.y2-t.y},o,e,i):\"end\"===e.position?1-tt({w:t.x-t.x2,h:t.y-t.y2},o,e,i):g(1,e.position);return r}(t,o,{labelSize:f,padding:a},r),y=$(c,h,x),p={size:f.w,min:r.left,max:r.right,padding:a.left},b={size:f.h,min:r.top,max:r.bottom,padding:a.top},m=et(y.x,p)+d,w=et(y.y,b)+l;return{x:m-i/2,y:w-s/2,x2:m+i/2,y2:w+s/2,centerX:m,centerY:w,width:i,height:s,rotation:e.toDegrees(u)}}(o,n,{width:a,height:d,padding:i},t.chartArea)}(t,x,o.label);return y._visible=f,x.elements=[{type:\"label\",optionScope:\"label\",properties:y}],x}}U.id=\"lineAnnotation\";const G={backg",
		"roundColor:void 0,backgroundShadowColor:void 0,borderColor:void 0,borderDash:void 0,borderDashOffset:void 0,borderShadowColor:void 0,borderWidth:void 0,display:void 0,fill:void 0,length:void 0,shadowBlur:void 0,shadowOffsetX:void 0,shadowOffsetY:void 0,width:void 0};function K({x:t,y:e},o,{top:n,right:r,bottom:i,left:s}){return t<s&&(e=J(s,{x:t,y:e},o),t=s),t>r&&(e=J(r,{x:t,y:e},o),t=r),e<n&&(t=F(n,{x:t,y:e},o),e=n),e>i&&(t=F(i,{x:t,y:e},o),e=i),{x:t,y:e}}function Q(t,{mouseX:e,mouseY:o},n,r){const i=t.label;return i.options.display&&i.inRange(e,o,r,n)}function Z(t,e,o){const n=I(e,o);t[o.startProp]=n.start,t[o.endProp]=n.end}function tt(t,e,o,n){const{labelSize:r,padding:i}=e,s=t.w*n.dx,a=t.h*n.dy,d=s>0&&(r.w/2+i.left-n.x)/s,l=a>0&&(r.h/2+i.top-n.y)/a;return u(Math.max(d,l),0,.25)}function et(t,e){const{size:o,min:n,max:r,padding:i}=e,s=o/2;return o>r-n?(r+n)/2:(n>=t-i-s&&(t=n+i+s),r<=t+i+s&&(t=r-i-s),t)}function ot(t,e){if(!e||!e.display)return 0;const{length:o,width:n}=e,r=t.options.",
		"borderWidth/2,i={x:o,y:n+r},s={x:0,y:r};return Math.abs(F(0,i,s))}function nt(t,e,o,n){if(!n||!n.display)return;const{length:r,width:i,fill:s,backgroundColor:a,borderColor:d}=n,l=Math.abs(e-r)+o;t.beginPath(),j(t,n),D(t,n),t.moveTo(l,-i),t.lineTo(e+o,0),t.lineTo(l,i),!0===s?(t.fillStyle=a||d,t.closePath(),t.fill(),t.shadowColor=\"transparent\"):t.shadowColor=n.borderShadowColor,t.stroke()}U.defaults={adjustScaleRange:!0,arrowHeads:{display:!1,end:Object.assign({},G),fill:!1,length:12,start:Object.assign({},G),width:6},borderDash:[],borderDashOffset:0,borderShadowColor:\"transparent\",borderWidth:2,display:!0,endValue:void 0,label:{backgroundColor:\"rgba(0,0,0,0.8)\",backgroundShadowColor:\"transparent\",borderCapStyle:\"butt\",borderColor:\"black\",borderDash:[],borderDashOffset:0,borderJoinStyle:\"miter\",borderRadius:6,borderShadowColor:\"transparent\",borderWidth:0,callout:{display:!1},color:\"#fff\",content:null,display:!1,drawTime:void 0,font:{family:void 0,lineHeight:void 0,size:v",
		"oid 0,style:void 0,weight:\"bold\"},height:void 0,padding:6,position:\"center\",rotation:0,shadowBlur:0,shadowOffsetX:0,shadowOffsetY:0,textAlign:\"center\",textStrokeColor:void 0,textStrokeWidth:0,width:void 0,xAdjust:0,yAdjust:0,z:void 0},scaleID:void 0,shadowBlur:0,shadowOffsetX:0,shadowOffsetY:0,value:void 0,xMax:void 0,xMin:void 0,xScaleID:void 0,yMax:void 0,yMin:void 0,yScaleID:void 0,z:0},U.descriptors={arrowHeads:{start:{_fallback:!0},end:{_fallback:!0},_fallback:!0}},U.defaultRoutes={borderColor:\"color\"};class rt extends t.Element{inRange(t,o,n,r){const i=this.options.rotation,s=this.options.borderWidth;if(\"x\"!==n&&\"y\"!==n)return function(t,o,n,r){const{width:i,height:s,centerX:a,centerY:d}=o,l=i/2,c=s/2;if(l<=0||c<=0)return!1;const h=e.toRadians(n||0),u=r/2||0,f=Math.cos(h),x=Math.sin(h),y=Math.pow(f*(t.x-a)+x*(t.y-d),2),p=Math.pow(x*(t.x-a)-f*(t.y-d),2);return y/Math.pow(l+u,2)+p/Math.pow(c+u,2)<=1.0001}({x:t,y:o},this.getProps([\"width\",\"height\",\"centerX\",\"center",
		"Y\"],r),i,s);const{x:a,y:d,x2:l,y2:c}=this.getProps([\"x\",\"y\",\"x2\",\"y2\"],r),u=s/2,f=\"y\"===n?{start:d,end:c}:{start:a,end:l},x=E({x:t,y:o},this.getCenterPoint(r),e.toRadians(-i));return x[n]>=f.start-u-h&&x[n]<=f.end+u+h}getCenterPoint(t){return y(this,t)}draw(t){const{width:o,height:n,centerX:r,centerY:i,options:s}=this;t.save(),C(t,this.getCenterPoint(),s.rotation),j(t,this.options),t.beginPath(),t.fillStyle=s.backgroundColor;const a=D(t,s);t.ellipse(r,i,n/2,o/2,e.PI/2,0,2*e.PI),t.fill(),a&&(t.shadowColor=s.borderShadowColor,t.stroke()),t.restore()}resolveElementProperties(t,e){return R(t,e)}}rt.id=\"ellipseAnnotation\",rt.defaults={adjustScaleRange:!0,backgroundShadowColor:\"transparent\",borderDash:[],borderDashOffset:0,borderShadowColor:\"transparent\",borderWidth:1,display:!0,rotation:0,shadowBlur:0,shadowOffsetX:0,shadowOffsetY:0,xMax:void 0,xMin:void 0,xScaleID:void 0,yMax:void 0,yMin:void 0,yScaleID:void 0,z:0},rt.defaultRoutes={borderColor:\"color\",backgroundColor:\"c",
		"olor\"};const it=[\"left\",\"bottom\",\"top\",\"right\"];class st extends t.Element{inRange(t,o,n,r){const{x:i,y:s}=E({x:t,y:o},this.getCenterPoint(r),e.toRadians(-this.rotation));return x({x:i,y:s},this.getProps([\"x\",\"y\",\"x2\",\"y2\"],r),n,this.options.borderWidth)}getCenterPoint(t){return y(this,t)}draw(t){const o=this.options,n=!e.defined(this._visible)||this._visible;o.display&&o.content&&n&&(t.save(),C(t,this.getCenterPoint(),this.rotation),function(t,o){const{pointX:n,pointY:r,options:i}=o,s=i.callout,a=s&&s.display&&function(t,o){const n=o.position;if(it.includes(n))return n;return function(t,o){const{x:n,y:r,x2:i,y2:s,width:a,height:d,pointX:l,pointY:c,centerX:h,centerY:u,rotation:f}=t,x={x:h,y:u},y=o.start,p=m(a,y),b=m(d,y),g=[n,n+p,n+p,i],w=[r+b,s,r,s],v=[];for(let t=0;t<4;t++){const o=E({x:g[t],y:w[t]},x,e.toRadians(f));v.push({position:it[t],distance:e.distanceBetweenPoints(o,{x:l,y:c})})}return v.sort(((t,e)=>t.distance-e.distance))[0].position}(t,o)}(o,s);if(!a||funct",
		"ion(t,e,o){const{pointX:n,pointY:r}=t,i=e.margin;let s=n,a=r;\"left\"===o?s+=i:\"right\"===o?s-=i:\"top\"===o?a+=i:\"bottom\"===o&&(a-=i);return t.inRange(s,a)}(o,s,a))return;t.save(),t.beginPath();if(!D(t,s))return t.restore();const{separatorStart:d,separatorEnd:l}=function(t,e){const{x:o,y:n,x2:r,y2:i}=t,s=function(t,e){const{width:o,height:n,options:r}=t,i=r.callout.margin+r.borderWidth/2;if(\"right\"===e)return o+i;if(\"bottom\"===e)return n+i;return-i}(t,e);let a,d;\"left\"===e||\"right\"===e?(a={x:o+s,y:n},d={x:a.x,y:i}):(a={x:o,y:n+s},d={x:r,y:a.y});return{separatorStart:a,separatorEnd:d}}(o,a),{sideStart:c,sideEnd:h}=function(t,e,o){const{y:n,width:r,height:i,options:s}=t,a=s.callout.start,d=function(t,e){const o=e.side;if(\"left\"===t||\"top\"===t)return-o;return o}(e,s.callout);let l,c;\"left\"===e||\"right\"===e?(l={x:o.x,y:n+m(i,a)},c={x:l.x+d,y:l.y}):(l={x:o.x+m(r,a),y:o.y},c={x:l.x,y:l.y+d});return{sideStart:l,sideEnd:c}}(o,a,d);(s.margin>0||0===i.borderWidth)&&(t.moveTo(d",
		".x,d.y),t.lineTo(l.x,l.y));t.moveTo(c.x,c.y),t.lineTo(h.x,h.y);const u=E({x:n,y:r},o.getCenterPoint(),e.toRadians(-o.rotation));t.lineTo(u.x,u.y),t.stroke(),t.restore()}(t,this),P(t,this,o),function(t,o,n){const r=n.content;if(M(r))return void t.drawImage(r,o.x,o.y,o.width,o.height);const i=e.isArray(r)?r:[r],s=e.toFont(n.font),a=s.lineHeight,d=function(t,e){const{x:o,width:n}=t,r=e.textAlign;return\"center\"===r?o+n/2:\"end\"===r||\"right\"===r?o+n:o}(o,n),l=o.y+a/2+n.textStrokeWidth/2;t.save(),t.font=s.string,t.textBaseline=\"middle\",t.textAlign=n.textAlign,function(t,e){if(e.textStrokeWidth>0)return t.lineJoin=\"round\",t.miterLimit=2,t.lineWidth=e.textStrokeWidth,t.strokeStyle=e.textStrokeColor,!0}(t,n)&&i.forEach(((e,o)=>t.strokeText(e,d,l+o*a))),t.fillStyle=n.color,i.forEach(((e,o)=>t.fillText(e,d,l+o*a))),t.restore()}(t,function({x:t,y:o,width:n,height:r,options:i}){const s=i.borderWidth/2,a=e.toPadding(i.padding);return{x:t+a.left+s,y:o+a.top+s,width:n-a.left-a.right-i.borderWi",
		"dth,height:r-a.top-a.bottom-i.borderWidth}}(this),o),t.restore())}resolveElementProperties(t,o){let n;if(v(o))n=Y(t,o);else{const{centerX:e,centerY:r}=R(t,o);n={x:e,y:r}}const r=e.toPadding(o.padding),i=function(t,e,o,n){const r=e.width+n.width+o.borderWidth,i=e.height+n.height+o.borderWidth,s=w(o.position),a=at(t.x,r,o.xAdjust,s.x),d=at(t.y,i,o.yAdjust,s.y);return{x:a,y:d,x2:a+r,y2:d+i,width:r,height:i,centerX:a+r/2,centerY:d+i/2}}(n,k(t.ctx,o),o,r);return{pointX:n.x,pointY:n.y,...i,rotation:o.rotation}}}function at(t,e,o=0,n){return t-g(e,n)+o}st.id=\"labelAnnotation\",st.defaults={adjustScaleRange:!0,backgroundColor:\"transparent\",backgroundShadowColor:\"transparent\",borderCapStyle:\"butt\",borderDash:[],borderDashOffset:0,borderJoinStyle:\"miter\",borderRadius:0,borderShadowColor:\"transparent\",borderWidth:0,callout:{borderCapStyle:\"butt\",borderColor:void 0,borderDash:[],borderDashOffset:0,borderJoinStyle:\"miter\",borderWidth:1,display:!1,margin:5,position:\"auto\",side:5,star",
		"t:\"50%\"},color:\"black\",content:null,display:!0,font:{family:void 0,lineHeight:void 0,size:void 0,style:void 0,weight:void 0},height:void 0,padding:6,position:\"center\",rotation:0,shadowBlur:0,shadowOffsetX:0,shadowOffsetY:0,textAlign:\"center\",textStrokeColor:void 0,textStrokeWidth:0,width:void 0,xAdjust:0,xMax:void 0,xMin:void 0,xScaleID:void 0,xValue:void 0,yAdjust:0,yMax:void 0,yMin:void 0,yScaleID:void 0,yValue:void 0,z:0},st.defaultRoutes={borderColor:\"color\"};class dt extends t.Element{inRange(t,e,o,n){const{x:r,y:i,x2:s,y2:a,width:d}=this.getProps([\"x\",\"y\",\"x2\",\"y2\",\"width\"],n),l=this.options.borderWidth;if(\"x\"!==o&&\"y\"!==o)return function(t,e,o,n){if(!t||!e||o<=0)return!1;const r=n/2;return Math.pow(t.x-e.x,2)+Math.pow(t.y-e.y,2)<=Math.pow(o+r,2)}({x:t,y:e},this.getCenterPoint(n),d/2,l);const c=l/2,h=\"y\"===o?{start:i,end:a,value:e}:{start:r,end:s,value:t};return h.value>=h.start-c&&h.value<=h.end+c}getCenterPoint(t){return y(this,t)}draw(t){const o=this.o",
		"ptions,n=o.borderWidth;if(o.radius<.1)return;t.save(),t.fillStyle=o.backgroundColor,j(t,o);const r=D(t,o);o.borderWidth=0,e.drawPoint(t,o,this.centerX,this.centerY),r&&!M(o.pointStyle)&&(t.shadowColor=o.borderShadowColor,t.stroke()),t.restore(),o.borderWidth=n}resolveElementProperties(t,e){return W(t,e)}}dt.id=\"pointAnnotation\",dt.defaults={adjustScaleRange:!0,backgroundShadowColor:\"transparent\",borderDash:[],borderDashOffset:0,borderShadowColor:\"transparent\",borderWidth:1,display:!0,pointStyle:\"circle\",radius:10,rotation:0,shadowBlur:0,shadowOffsetX:0,shadowOffsetY:0,xAdjust:0,xMax:void 0,xMin:void 0,xScaleID:void 0,xValue:void 0,yAdjust:0,yMax:void 0,yMin:void 0,yScaleID:void 0,yValue:void 0,z:0},dt.defaultRoutes={borderColor:\"color\",backgroundColor:\"color\"};class lt extends t.Element{inRange(t,o,n,r){if(\"x\"!==n&&\"y\"!==n)return this.options.radius>=.1&&this.elements.length>1&&function(t,e,o,n){let r=!1,i=t[t.length-1].getProps([\"bX\",\"bY\"],n);for(const s of t){const",
		" t=s.getProps([\"bX\",\"bY\"],n);t.bY>o!=i.bY>o&&e<(i.bX-t.bX)*(o-t.bY)/(i.bY-t.bY)+t.bX&&(r=!r),i=t}return r}(this.elements,t,o,r);const i=E({x:t,y:o},this.getCenterPoint(r),e.toRadians(-this.options.rotation)),s=this.elements.map((t=>\"y\"===n?t.bY:t.bX)),a=Math.min(...s),d=Math.max(...s);return i[n]>=a&&i[n]<=d}getCenterPoint(t){return y(this,t)}draw(t){const{elements:e,options:o}=this;t.save(),t.beginPath(),t.fillStyle=o.backgroundColor,j(t,o);const n=D(t,o);let r=!0;for(const o of e)r?(t.moveTo(o.x,o.y),r=!1):t.lineTo(o.x,o.y);t.closePath(),t.fill(),n&&(t.shadowColor=o.borderShadowColor,t.stroke()),t.restore()}resolveElementProperties(t,o){const n=W(t,o),{x:r,y:i}=n,{sides:s,rotation:a}=o,d=[],l=2*e.PI/s;let c=a*e.RAD_PER_DEG;for(let t=0;t<s;t++,c+=l)d.push(ct(n,o,c));return n.elements=d,n.initProperties={x:r,y:i},n}}function ct({centerX:t,centerY:e},{radius:o,borderWidth:n},r){const i=n/2,s=Math.sin(r),a=Math.cos(r),d={x:t+s*o,y:e-a*o};return{type:\"point\",optionScope:\"point\",p",
		"roperties:{x:d.x,y:d.y,centerX:d.x,centerY:d.y,bX:t+s*(o+i),bY:e-a*(o+i)}}}lt.id=\"polygonAnnotation\",lt.defaults={adjustScaleRange:!0,backgroundShadowColor:\"transparent\",borderCapStyle:\"butt\",borderDash:[],borderDashOffset:0,borderJoinStyle:\"miter\",borderShadowColor:\"transparent\",borderWidth:1,display:!0,point:{radius:0},radius:10,rotation:0,shadowBlur:0,shadowOffsetX:0,shadowOffsetY:0,sides:3,xAdjust:0,xMax:void 0,xMin:void 0,xScaleID:void 0,xValue:void 0,yAdjust:0,yMax:void 0,yMin:void 0,yScaleID:void 0,yValue:void 0,z:0},lt.defaultRoutes={borderColor:\"color\",backgroundColor:\"color\"};const ht={box:B,ellipse:rt,label:st,line:U,point:dt,polygon:lt};Object.keys(ht).forEach((e=>{t.defaults.describe(`elements.${ht[e].id}`,{_fallback:\"plugins.annotation.common\"})}));const ut={update:Object.assign};function ft(t=\"line\"){return ht[t]?t:(console.warn(`Unknown annotation type: '${t}', defaulting to 'line'`),\"line\")}function xt(o,n,r,i){const s=function(e,o,n){if(\"reset\"===",
		"n||\"none\"===n||\"resize\"===n)return ut;return new t.Animations(e,o)}(o,r.animations,i),a=n.annotations,d=function(t,e){const o=e.length,n=t.length;if(n<o){const e=o-n;t.splice(n,0,...new Array(e))}else n>o&&t.splice(o,n-o);return t}(n.elements,a);for(let t=0;t<a.length;t++){const n=a[t],r=bt(d,t,n.type),i=n.setContext(wt(o,r,n)),l=r.resolveElementProperties(o,i);l.skip=yt(l),\"elements\"in l&&(pt(r,l,i,s),delete l.elements),e.defined(r.x)||Object.assign(r,l),l.options=gt(i),s.update(r,l)}}function yt(t){return isNaN(t.x)||isNaN(t.y)}function pt(t,{elements:e,initProperties:o},n,r){const i=t.elements||(t.elements=[]);i.length=e.length;for(let t=0;t<e.length;t++){const s=e[t],a=s.properties,d=bt(i,t,s.type,o),l=n[s.optionScope].override(s);a.options=gt(l),r.update(d,a)}}function bt(t,o,n,r){const i=ht[ft(n)];let s=t[o];return s&&s instanceof i||(s=t[o]=new i,e.isObject(r)&&Object.assign(s,r)),s}function gt(t){const e=ht[ft(t.type)],o={};o.id=t.id,o.type=t.type,o.drawTime=t.drawTime,Obj",
		"ect.assign(o,mt(t,e.defaults),mt(t,e.defaultRoutes));for(const e of s)o[e]=t[e];return o}function mt(t,o){const n={};for(const r of Object.keys(o)){const i=o[r],s=t[r];n[r]=e.isObject(i)?mt(s,i):s}return n}function wt(t,e,o){return e.$context||(e.$context=Object.assign(Object.create(t.getContext()),{element:e,id:o.id,type:\"annotation\"}))}const vt=new Map;var St={id:\"annotation\",version:\"2.0.1\",beforeRegister(){!function(t,e,o,n=!0){const r=o.split(\".\");let i=0;for(const s of e.split(\".\")){const a=r[i++];if(parseInt(s,10)<parseInt(a,10))break;if(c(a,s)){if(n)throw new Error(`${t} v${o} is not supported. v${e} or newer is required.`);return!1}}}(\"chart.js\",\"3.7\",t.Chart.version)},afterRegister(){t.Chart.register(ht)},afterUnregister(){t.Chart.unregister(ht)},beforeInit(t){vt.set(t,{annotations:[],elements:[],visibleElements:[],listeners:{},listened:!1,moveListened:!1,hovered:[]})},beforeUpdate(t,o,n){const r=vt.get(t).annotations=[];let i=n.annotations;e.isObject(i)?Object.k",
		"eys(i).forEach((t=>{const o=i[t];e.isObject(o)&&(o.id=t,r.push(o))})):e.isArray(i)&&r.push(...i),function(t,e){for(const o of t)_(o,e)}(r,t.scales)},afterDataLimits(t,e){const o=vt.get(t);T(t,e.scale,o.annotations.filter((t=>t.display&&t.adjustScaleRange)))},afterUpdate(t,o,r){const a=vt.get(t);!function(t,o,r){o.listened=!1,o.moveListened=!1,o._getElements=n,s.forEach((t=>{\"function\"==typeof r[t]?(o.listened=!0,o.listeners[t]=r[t]):e.defined(o.listeners[t])&&delete o.listeners[t]})),i.forEach((t=>{\"function\"==typeof r[t]&&(o.moveListened=!0)})),o.listened&&o.moveListened||o.annotations.forEach((t=>{o.listened||\"function\"!=typeof t.click||(o.listened=!0),o.moveListened||i.forEach((e=>{\"function\"==typeof t[e]&&(o.listened=!0,o.moveListened=!0)}))}))}(0,a,r),xt(t,a,r,o.mode),a.visibleElements=a.elements.filter((t=>!t.skip&&t.options.display))},beforeDatasetsDraw(t,e,o){Mt(t,\"beforeDatasetsDraw\",o.clip)},afterDatasetsDraw(t,e,o){Mt(t,\"afterDatasetsDraw\",o.clip)},beforeDraw(t,e,",
		"o){Mt(t,\"beforeDraw\",o.clip)},afterDraw(t,e,o){Mt(t,\"afterDraw\",o.clip)},beforeEvent(t,e,o){a(vt.get(t),e.event,o)&&(e.changed=!0)},destroy(t){vt.delete(t)},_getState:t=>vt.get(t),defaults:{animations:{numbers:{properties:[\"x\",\"y\",\"x2\",\"y2\",\"width\",\"height\",\"centerX\",\"centerY\",\"pointX\",\"pointY\",\"radius\"],type:\"number\"}},clip:!0,interaction:{mode:void 0,axis:void 0,intersect:void 0},common:{drawTime:\"afterDatasetsDraw\",label:{}}},descriptors:{_indexable:!1,_scriptable:t=>!s.includes(t),annotations:{_allKeys:!1,_fallback:(t,e)=>`elements.${ht[ft(e.type)].id}`},interaction:{_fallback:!0},common:{label:{_fallback:!0}}},additionalOptionScopes:[\"\"]};function Mt(t,o,n){const{ctx:r,chartArea:i}=t,{visibleElements:s}=vt.get(t);n&&e.clipArea(r,i);const a=function(t,e){const o=[];for(const n of t)if(n.options.drawTime===e&&o.push(n),n.elements&&n.elements.length)for(const t of n.elements)t.options.display&&t.options.drawTime===e&&o.push(t);return o}(s,o).sort(((t,e)",
		"=>t.options.z-e.options.z));for(const e of a)e.draw(t.ctx,i);n&&e.unclipArea(r)}return t.Chart.register(St),St}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-annotation.min.js</code> content.
	 */
	AnnotationPluginResource() {
		super(ResourceName.ANNOTATION_PLUGIN, CONTENT);
	}

}
