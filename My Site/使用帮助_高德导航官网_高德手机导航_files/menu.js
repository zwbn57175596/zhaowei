$(function () {
    var $menu = $(".menu"), $menuLi = $menu.find("li"), $current = $menu.find('.current'), $li_3 = $menu.find('li.li_3'), $li_3_content = $li_3.find('.li_3_content');
    // first load page
    var $this = $(this), num = $menuLi.index($this), current = $menuLi.index($current), len = current - num;
    switch(current)
	{
		case 0:{ 
		$menu.stop().animate({ backgroundPosition: (71 * current + 0) + "px" + " bottom" }, 100 * len);
		 ;break;}
		case 1:{ 
		$menu.stop().animate({ backgroundPosition: (71 * current + 0) + "px" + " bottom" }, 100 * len);
		 ;break;}
		case 2:{ 
		$menu.stop().animate({ backgroundPosition: (71 * current + 15) + "px" + " bottom" }, 100 * len);
		 ;break;}
		case 3:{ 
		$menu.stop().animate({ backgroundPosition: (71 * current + 40) + "px" + " bottom" }, 100 * len);
		 ;break;}
		case 4:{ 
		$menu.stop().animate({ backgroundPosition: (71 * current + 70) + "px" + " bottom" }, 100 * len);
		 ;break;}
		case 5:{ 
			$menu.stop().animate({ backgroundPosition: (71 * current + 100) + "px" + " bottom" }, 100 * len);
			 ;break;}
	}
    // first load page end
    $menuLi.hover(function () {
        var $this = $(this), num = $menuLi.index($this), current = $menuLi.index($(".first")), len = current - num;
        $menu.css("background-position", (71 * current) + "px" + " bottom");
        $current.removeClass("lihover");
        $menuLi.removeClass("first");
        $this.addClass("first");
        if (len <= 0) { len = -len; };
         
		switch(num)
{
case 0:{ 
$menu.stop().animate({ backgroundPosition: (71 * num + 0) + "px" + " bottom" }, 100 * len);
 ;break;}
case 1:{ 
$menu.stop().animate({ backgroundPosition: (71 * num + 0) + "px" + " bottom" }, 100 * len);
 ;break;}
case 2:{ 
$menu.stop().animate({ backgroundPosition: (71 * num + 15) + "px" + " bottom" }, 100 * len);
 ;break;}
case 3:{ 
$menu.stop().animate({ backgroundPosition: (71 * num + 40) + "px" + " bottom" }, 100 * len);
 ;break;}
case 4:{ 
$menu.stop().animate({ backgroundPosition: (71 * num + 70) + "px" + " bottom" }, 100 * len);
 ;break;}
case 5:{ 
	$menu.stop().animate({ backgroundPosition: (71 * num + 100) + "px" + " bottom" }, 100 * len);
	 ;break;}}

 
    });
    $li_3.hover(function () {
        $li_3_content.stop(true, true).fadeIn(0);
    }, function () {
        $li_3_content.fadeOut(500, function () {
            $li_3_content.css("display", "none");
        });
    });
    $menu.mouseleave(function () {
        var $this = $(this), num = $menuLi.index($this), current = $menuLi.index($current), len = current - num;
        $menuLi.removeClass("first");
        $current.addClass("first");
        if (len <= 0) { len = -len; };
        // $menu.stop().animate({ backgroundPosition: (100 * current + 1) + "px" + " bottom" }, 100 * len);
		switch(current)
		{
			case 0:{ 
			$menu.stop().animate({ backgroundPosition: (71 * current + 0) + "px" + " bottom" }, 100 * len);
			 ;break;}
			case 1:{ 
			$menu.stop().animate({ backgroundPosition: (71 * current + 0) + "px" + " bottom" }, 100 * len);
			 ;break;}
			case 2:{ 
			$menu.stop().animate({ backgroundPosition: (71 * current + 15) + "px" + " bottom" }, 100 * len);
			 ;break;}
			case 3:{ 
			$menu.stop().animate({ backgroundPosition: (71 * current + 40) + "px" + " bottom" }, 100 * len);
			 ;break;}
			case 4:{ 
			$menu.stop().animate({ backgroundPosition: (71 * current + 70) + "px" + " bottom" }, 100 * len);
			 ;break;}
			case 5:{ 
			$menu.stop().animate({ backgroundPosition: (71 * current + 100) + "px" + " bottom" }, 100 * len);
			 ;break;}
		}
    });
    $("a.noclick").click(function (event) {
        event.preventDefault();
    });
});