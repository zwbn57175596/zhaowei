
var arrText2=[];//定义数组；
var this_classname;
function elec(namei){
	if(!document.getElementById("tele_name1")) return false;
	if(!document.getElementById("tele_name0")) return false;
	var div_box=document.getElementById(namei);//获取id
	var this_id=document.getElementById("tele_name1");
	var div2_h1=this_id.getElementsByTagName("h1")[0];
	var box_h1=div_box.getElementsByTagName("h1");
	var box_ul=div_box.getElementsByTagName("ul");
	var box_li=div_box.getElementsByTagName("li");
	var html_ele=document.getElementsByTagName("html")[0];//获取html;
	html_ele.onclick=function(){
		box_ul[0].style.display="none";
		}	//单击任何地方，下拉框隐藏；
	box_h1[0].onclick=function(event){
		if(!this_id.getElementsByTagName("ul"))return false;
		if(!event){event=window.event;}
			event.cancelBubble=true;//阻止冒泡事件，费了半天劲呢！
			box_ul[0].style.display=(box_ul[0].style.display=="none") ? "block":"none";//单击h1如果下拉框显示则隐藏，如果隐藏则显示;
		}
	for(var i=0; i<box_li.length;i++){
		box_li[i].onmouseover=function(){
			this.className="bg_color";
		}
		box_li[i].onmouseout=function(){
			this.className=" ";
		}//鼠标移到li上变色；
		box_li[i].onclick=function(){
			box_h1[0].lastChild.nodeValue=this.lastChild.nodeValue;//单击li; h1获取当前li的文本内容；
			var box_text=box_h1[0].lastChild.nodeValue;
			div2_h1.lastChild.nodeValue="请选择型号";//第二个下拉框h1变成默认的请选择型号；
			if(namei=="tele_name1"){
				div2_h1.lastChild.nodeValue=this.lastChild.nodeValue;//如果是在第二下拉框里进行单击则获取当前的文本内容；
				var box_text2=div2_h1.lastChild.nodeValue;
				//newcele(box_text,box_text2);
							}
			var getId=this.getAttribute("id");//获取当前被点击的li的id;
			if(getId=="heimei"){
			var arrText=["A3500","btwee","C234","dsese"];
			var ul_classname="heimei"
			arrText2=arrText.valueOf();
			this_classname=ul_classname;
				newadd();
			}//如果当前li的ID=heimei则显示黑莓的型号；
			else if(getId=="xiaxin"){
				var arrText=["B123","B456","B789","B902"];
				var ul_classname="xiaxin"
				arrText2=arrText.valueOf();
				this_classname=ul_classname;
				newadd();
			}//如果当前li的ID=xiaxin则显示xiaxin的型号；
			else if(getId=="pingguo"){
				var arrText=["IPAD","apple001","apple02","apple004","apple05"];
				var ul_classname="pingguo"
				arrText2=arrText.valueOf();
				this_classname=ul_classname;
				newadd();
			}//如果当前li的ID=pingguo则显示pingguo的型号；
			
			box_ul[0].style.display="none";
		}
	}
}

function newadd(){                                       
	var div_box=document.getElementById("tele_name0");//查找第一个div
	var this_id=document.getElementById("tele_name1");//查找第二个div
	var box_li=div_box.getElementsByTagName("li");//查找第一个div里的li;
	var newul=document.createElement("ul");//创建ul;
	newul.setAttribute("style","display:none");
	newul.setAttribute("class","this_classname");//为ul设置属性；
	var oFramnet=document.createDocumentFragment();//创建片段，确保加载更快；
			var div_ul=this_id.getElementsByTagName("ul");
			 for(var i=0;i<div_ul.length;i++){
				 if(div_ul[i].getAttribute("class")!=this_classname){
					 this_id.removeChild(div_ul[i]);
				 }
			 }//判断当前的ul是不是需要的，如果不是删除ul节点；
			for(var i=0;i<arrText2.length;i++){
				var newli=document.createElement("li");
				var newtext=document.createTextNode(arrText2[i]);
				newli.appendChild(newtext);
				newul.appendChild(newli);
				oFramnet.appendChild(newul);
			}
			this_id.appendChild(oFramnet);//创建
			
}


//function newcele(text_one,text_tow){
//		var sele=document.getElementById("select");
//		var new_p=document.createElement("p");
//		new_p.setAttribute("class","show_phone");
//		var get_text1=document.createTextNode(text_one);
//		var get_text2=document.createTextNode(text_tow);
//		var one_text=document.createTextNode("您选的手机是:");
//		var three_text=document.createTextNode("  ");
//		var tow_text=document.createTextNode("您选的型号是：");
//		new_p.appendChild(one_text);
//		new_p.appendChild(get_text1);
//		new_p.appendChild(three_text);
//		new_p.appendChild(tow_text);
//		new_p.appendChild(get_text2);
//		sele.appendChild(new_p);
//		
//}









