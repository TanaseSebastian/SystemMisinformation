var bar = document.getElementById("indexBar");
var index = document.getElementById("indexBar").innerHTML;
console.log(bar);

if(index <= 40) {
    bar.setAttribute("class","w3-container w3-red w3-center");
}
else if( index > 40 && index < 70){
    bar.setAttribute("class","w3-container w3-blue w3-center");
}
else if( index >= 70){
    bar.setAttribute("class","w3-container w3-green w3-center");
}