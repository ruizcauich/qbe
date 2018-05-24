
var table_buttons = document.getElementsByClassName("table-button");

table_buttons = [].slice.call(table_buttons);

table_buttons.forEach(element => {
    element.addEventListener("click", interacTable)
});

var r;
function interacTable( e ){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          ponerTabla(JSON.parse(this.responseText) )
       }
    };
    xhttp.open("POST", "traertabla", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xhttp.send("tabla="+e.target.innerHTML);
}

function ponerTabla( dat ){
    var tabla = document.createElement("table");
    dat.forEach((row)=>{
        var tr = document.createElement("tr");
        row.forEach((dato)=>{
            var td = document.createElement("td");
            td.innerText = dato;
            tr.appendChild(td);
        });
        tabla.appendChild(tr);
    });

    trabajo = document.getElementById("trabajo");
    tabla.classList.add("tabla")
    trabajo.appendChild(tabla);
}