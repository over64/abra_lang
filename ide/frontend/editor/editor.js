function editor_plugin(dom) {
	var $dom = $(dom);
	$dom.append($('<div/>', {
		class: 'editor-content',
		id: 'editor-content'
	}));

	var $ec = $('#editor-content');
	var $esyntax = $('<div/>', {
		id: 'editor-syntax',
	});
	var $ecode = $('<textarea/>', {
		id: 'editor-code',
		spellcheck: 'false'
	});

	$ecode[0].onkeydown = function(e) {
        if(e.keyCode === 9) {
	        // get caret position/selection
	        var start = this.selectionStart;
	        var end = this.selectionEnd;

	        var target = e.target;
	        var value = target.value;

	        // set textarea value to: text before caret + tab + text after caret
	        target.value = value.substring(0, start)
	                    + "    "
	                    + value.substring(end);

	        // put caret at right position again (add one for the tab)
	        this.selectionStart = this.selectionEnd = start + 4;

	        // prevent the focus lose
	        e.preventDefault();

	        $ecode.trigger({ type: 'input' });
    	}
	}

	$ecode.appendTo($ec);
	$esyntax.appendTo($ec);

	function syncScroll() {
		$esyntax.scrollTop($ecode.scrollTop());
		$esyntax.scrollLeft($ecode.scrollLeft());
	}

	$ecode.on('scroll', function(){
		syncScroll();
	})

	var fd;

	function renderSplice(splice) {
		$esyntax.html('');
		splice.add.forEach(function(span) {
			// if(span.text == '<br>') $('<br>').appendTo($esyntax);
			$('<span/>').attr("class", span.clazz).html(span.text).appendTo($esyntax);
		});

		$('<span/>').html('<br>').appendTo($esyntax);
		$('<span class="editor-code-end"/>').html('end!').appendTo($esyntax);
	};

	$.post('http://localhost:4567/fopen', function(data) {
		var data = JSON.parse(data);
		fd = data.fd;

		// console.log(data);

		renderSplice(data.splice);
		$ecode.val(data.text);
	});

	$ecode.on('input', function() {
		syncScroll();
		$.post('http://localhost:4567/fwrite/' + fd, $ecode.val(), function(data) {
			renderSplice(JSON.parse(data));
		})
	});
}