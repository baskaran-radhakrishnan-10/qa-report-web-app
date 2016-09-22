/**
 * @name		jQuery Countdown Plugin
 * @author		Martin Angelov
 * @modified    Baskaran Radhakrishnan
 * @version 	1.1
 * @url			-------------------------
 * @license		------------------------
 */

(function($){
	
	// Number of seconds in every time division
	var days	= 24*60*60,
		hours	= 60*60,
		minutes	= 60;
	
	// Creating the plugin
	$.fn.countdown = function(prop){
		
		var serverTime=getCurrentServerTime();
		
		var options = $.extend({
			callback	: function(){},
			timestamp	: 0
		},prop);
		
		var left, d, h, m, s, positions,selector;

		// Initialize the plugin
		init(this, options);
		
		positions = this.find('.position');
		
		selector=this.selector;
		
		(function tick(){
			
			// Time left
			left = Math.floor((options.timestamp - (serverTime)) / 1000);
			
			if(left < 0){
				left = 0;
			}
			
			// Number of days left
			d = Math.floor(left / days);
			updateDuo(0, 1, d);
			left -= d*days;
			
			// Number of hours left
			h = Math.floor(left / hours);
			updateDuo(2, 3, h);
			left -= h*hours;
			
			// Number of minutes left
			m = Math.floor(left / minutes);
			updateDuo(4, 5, m);
			left -= m*minutes;
			
			// Number of seconds left
			s = left;
			updateDuo(6, 7, s);
			
			// Calling an optional user supplied callback
			
			serverTime=serverTime+1000;
			
			var leftMinute=$(selector).find('.countMinutes').find('#left').text().trim().replace("00","0");
			
			var rightMinute=$(selector).find('.countMinutes').find('#right').text().trim().replace("00","0");
			
			var leftSecond=$(selector).find('.countSeconds').find('#right').text().trim().replace("00","0");
			
			var rightSecond=$(selector).find('.countSeconds').find('#left').text().trim().replace("00","0");
			
			options.callback(d, h, m, s);
			
			if("0" != leftMinute || "0" != leftSecond || "0" != rightMinute || "0" != rightSecond){
				
				setTimeout(tick, 1000);
				
			}
			
		})();
		
		// This function updates two digit positions at once
		function updateDuo(minor,major,value){
			switchDigit(positions.eq(minor),Math.floor(value/10)%10);
			switchDigit(positions.eq(major),value%10);
		}
		
		return this;
	};


	function init(elem, options){
		
		elem.addClass('countdownHolder');

		// Creating the markup inside the container
		$.each(['Days','Hours','Minutes','Seconds'],function(i){
			$('<span class="count'+this+'">').html(
				'<span class="position" id="left">\
					<span class="digit static">0</span>\
				</span>\
				<span class="position" id="right">\
					<span class="digit static">0</span>\
				</span>'
			).appendTo(elem);
			
			if(this!="Seconds"){
				elem.append('<span class="countDiv countDiv'+i+'"></span>');
			}
		});
		
		//Code Modified By Baskaran Radhakrishnan for Quiz App
		
		$('.countDays').css("display","none");
		
		$('.countDiv0').css("display","none");
		
		$('.countHours').css("display","none");
		
		$('.countDiv1').css("display","none");
		
	}

	// Creates an animated transition between the two numbers
	function switchDigit(position,number){
		
		var digit = position.find('.digit')
		
		if(digit.is(':animated')){
			return false;
		}
		
		if(position.data('digit') == number){
			// We are already showing this number
			return false;
		}
		
		position.data('digit', number);
		
		var replacement = $('<span>',{
			'class':'digit',
			css:{
				top:'-2.1em',
				opacity:0
			},
			html:number
		});
		
		// The .static class is added when the animation
		// completes. This makes it run smoother.
		
		digit
			.before(replacement)
			.removeClass('static')
			.animate({top:'2.5em',opacity:0},'fast',function(){
				digit.remove();
			})

		replacement
			.delay(100)
			.animate({top:0,opacity:1},'fast',function(){
				replacement.addClass('static');
			});
	}
})(jQuery);