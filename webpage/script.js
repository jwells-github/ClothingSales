var men = document.getElementsByClassName("mens");
var women = document.getElementsByClassName("women");
var menbtn = document.getElementById("menbtn");
var womenbtn = document.getElementById("womenbtn");

function viewMen(){
  hide(women);
  show(men);
  menbtn.style.color = "gold";
  womenbtn.style.color = "white";
}

function viewWomen(){
  hide(men);
  show(women);
  womenbtn.style.color = "gold";
  menbtn.style.color = "white";
}

function hide(array){
  for (i = 0; i < array.length; i++) {
    array[i].style.display = "none";
  } 
}

function show(array){
    for (i = 0; i < array.length; i++) {
    array[i].style.display = "block";
  } 
}

viewMen();