window.onload=function(){
 friend();
 lsit_show();
 xuan();
 service();
resetFields();
li_onmouseover();
stripeTables();


};


function no_overflow(){
document.body.style.overflowY= 'hidden';
document.body.style.overflowX= 'hidden';
}

function show_overflow(){
document.body.style.overflowY= 'visible';
document.body.style.overflowX= 'visible';
}

function friend(){
	if(!document.getElementById) return false;
	if(!document.getElementsByTagName) return false;
	if(!document.getElementById("box_list")) return false;
	var footer=document.getElementById("box_list");
	var img_list=footer.getElementsByTagName("img");
	 for(var i=0; i<9;i++){
		 var img_w=img_list[i].clientWidth;
	 img_list[i].parentNode.style.width=img_w+"px";
		 img_list[i].parentNode.style.position="relative";
		 img_list[i].style.position="absolute";
		 img_list[i].style.top="0px";
		 img_list[i].style.left="0px";
		 img_list[i].onmouseover=function(){
			 this.style.top="-35px";
		 };
		 img_list[i].onmouseout=function(){
			 this.style.top="0px";
		 };
		
	}
}


function lsit_show(){
	if(!document.getElementById) return false;
	if(!document.getElementsByTagName) return false;
	if(!document.getElementById("phone_list")) return false;
	var infor=document.getElementById("phone_list");
	var infor_div=infor.getElementsByTagName("div");
	for(var i=0; i<infor_div.length;i++){
		infor_div[i].onmouseover=function(){
		
			if(this.className=="one_lsit"){
			var tt=this.getElementsByTagName("div");
				for(var i=0; i<tt.length;i++){
					if(tt[i].className=="version_infor"){
						tt[i].style.display="block";
					}
				}
			}
		};
		infor_div[i].onmouseout=function(){
		
			if(this.className=="one_lsit"){
			var tt=this.getElementsByTagName("div");
				for(var i=0; i<tt.length;i++){
					if(tt[i].className=="version_infor"){
						tt[i].style.display="none";
					}
				}
			}
			
		};
		
	
	}
}


function xuan(){
	if(!document.getElementById) return false;
	if(!document.getElementsByTagName) return false;
	if(!document.getElementById("three_phase")) return false;
	var xuanId=document.getElementById("three_phase");
	var xuan_li=xuanId.getElementsByTagName("li");
	var xuan_div=xuanId.getElementsByTagName("div");
		for(var i=0;i<xuan_li.length;i++){
			xuan_li[i].onmouseout=function(){
			for(var j=0;j<3;j++){
				xuan_li[j].className="";
			}
			this.className="phase_on";
			
		};
	}

	xuan_li[0].onmouseover=function(){
		for(var j=0;j<3;j++){
					xuan_li[j].className="";
				}
		this.className="phase_on";
		xuan_div[0].style.display="block";
		xuan_div[1].style.display="none";
		xuan_div[2].style.display="none";
	};
	xuan_li[1].onmouseover=function(){
		for(var j=0;j<3;j++){
					xuan_li[j].className="";
				}
		this.className="phase_on";
		xuan_div[1].style.display="block";
		xuan_div[0].style.display="none";
		xuan_div[2].style.display="none";
	};
	xuan_li[2].onmouseover=function(){
		for(var j=0;j<3;j++){
					xuan_li[j].className="";
				}
		this.className="phase_on";
		xuan_div[2].style.display="block";
		xuan_div[1].style.display="none";
		xuan_div[0].style.display="none";
	};
}



function service(){
	if(!document.getElementById) return false;
	if(!document.getElementsByTagName) return false;
	if(!document.getElementById("drop_down")) return false;
	if(!document.getElementsByTagName("dl")) return false;
	var ser_id=document.getElementById("drop_down");
	var ser_dl=ser_id.getElementsByTagName("dl")[0];
	 ser_id.onmousemove=function(){
		 ser_dl.style.display="block";
	 };
	  ser_id.onmouseout=function(){
		 ser_dl.style.display="none";
	 };
	
	}

function showDiv(i) { 
		if(!document.getElementById) return false;
		if(!document.getElementsByTagName) return false;
		if(!document.getElementById('div'+i)) return false;
		var tt=document.getElementById('tt'+i);
		if(document.getElementById('div'+i).style.display == 'none'){
		 	document.getElementById('div'+i).style.display='block'; 
			tt.className="search_icon2";
		}
		 else
		 {
		 document.getElementById('div'+i).style.display='none';
		 tt.className="search_icon";
		
		 }
} 


function resetFields() {
	if(!document.getElementById) return false;
	
		if(!document.getElementById("name")) return false;
	
	var getid=document.getElementById("name");
	 getid.onfocus = function() {
    if (this.value == this.defaultValue) {
      this.value = "";
     }
    };
   getid.onblur = function() {
      if (this.value == "") {
        this.value = this.defaultValue;
   
    }
  };
}

function li_onmouseover(){
	if(!document.getElementById) return false;
		if(!document.getElementsByTagName) return false;
		if(!document.getElementById("show_nav")) return false;
	var divelement=document.getElementById("show_nav");
	var liele=divelement.getElementsByTagName("a");
	for(var i=0;i<liele.length;i++){
	
		liele[i].onmouseover=function(){
			this.style.color="#00a0e9";
			
		};
		liele[i].onclick=function(){
			this.style.color="#ffffff";
			
		};
		liele[i].onmouseout=function(){
			this.style.color="#ffffff";
			
		};
	}
	
}

function stripeTables() {
  if (!document.getElementsByTagName) return false;
 if(!document.getElementById("model_list")) return false;
  var mod=document.getElementById("model_list");
  var tables = mod.getElementsByTagName("dl");
  var odd = false;
    var rows = tables[0].getElementsByTagName("dd");
    for (var j=0; j<rows.length; j++) {
	      if (odd == true) {
       rows[j].style.background="#e7e7e7";
        odd = false;
      } else {
        odd = true;
      }
    }
  }

function border_onmouseover(class_div){
	var c_name=document.getElementsByTagName("div");
		for(var i=0;i<c_name.length;i++){
			if(c_name[i].className==class_div){
		c_name[i].style.borderColor="#77c7ec";
		}
	}
}
function border_onmouseout(class_div){
	var c_name=document.getElementsByTagName("div");
		for(var i=0;i<c_name.length;i++){
			if(c_name[i].className==class_div){
		c_name[i].style.borderColor="#b6b6b6";
		}
	}
}

