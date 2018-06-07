
var table_buttons = document.getElementsByClassName("table-button");

table_buttons = [].slice.call(table_buttons);

table_buttons.forEach(element => {
    element.addEventListener("click", interacTable)
});


//Obtenemos y agregamos un evento al boton del toolbar
var aceptar_button = document.getElementById("filtrar");
aceptar_button.addEventListener("click", filtro);

objeto = {};


var r;
function interacTable( e ){
    var tabla = e.target.innerHTML;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          ponerTabla(JSON.parse(this.responseText),tabla )
       }
    };
    xhttp.open("POST", "traertabla", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xhttp.send("tabla="+e.target.innerHTML);
}
//No mostrar los datos de la tabla solamente sus columnas con un checkbox
function ponerTabla( dat,nombreTabla ){
    var tabla = document.createElement("table");
    var tr = document.createElement("tr");
    var tr_checks = document.createElement("tr");
    
    var columnas = dat[0];   
    
    objeto[nombreTabla] = [];
    
    
    columnas.forEach(function(c){
      // Se crean elementos para las columnas de nombres y checkbox
       var th = document.createElement("th");
       var td = document.createElement("td");
       var check = document.createElement("input");
       var field = document.createElement("input");
       
       field.type="text";
       field.id=nombreTabla +"." +c;
       check.type="checkbox";
       check.checked = "true";
       
       objeto[nombreTabla].push(nombreTabla +"." +c);
       
       
       //Añadimos un evento especial para todos los botones check que nos ayudará a gestionar
       //por que campos tenemos que filtrar la información
       check.addEventListener("click", function(){
           getSeleccion(nombreTabla,c,check.checked);
       });
       
       
       //Añadimos al td nuestro check creado
       td.appendChild(check);
       td.appendChild(field);
       
       th.innerHTML = c;
       
       //Añadimos a los tr's los td's y th's
       tr.appendChild(th);
       tr_checks.appendChild(td);
       
    });
    
    
    tabla.appendChild(tr);
    tabla.appendChild(tr_checks);
    trabajo = document.getElementById("trabajo");
    tabla.classList.add("tabla")
    trabajo.appendChild(tabla);
}

function getSeleccion(nombreTabla,columna,check){
   if(!check){
       var index = objeto[nombreTabla].indexOf(nombreTabla+"."+columna);
       if(index != -1){
           objeto[nombreTabla].splice(index,1);
       }
   }
   else{
       objeto[nombreTabla].push(nombreTabla+"."+columna);
   }
}


var nombresColumnas;
function mostrarFiltro(dat){
    
    var contador = 0;
    
    var tabla = document.createElement("table");
  
    dat.forEach((row)=>{
        var tr = document.createElement("tr");
        if(contador==0)
            nombresColumnas = row.toString();
        if(Object.keys(objeto).length  == 1){
            var tdEliminar = document.createElement("td");
            if(contador >= 1){
                //tdEliminar.innerHTML="<button onclick='eliminar(contador)'> <i class='material-icons'>delete</i></button>";
                
                //boton eliminar
                var btnEliminar =document.createElement("button");
                btnEliminar.innerHTML = "<i class='material-icons'>delete</i>";   
                btnEliminar.dataset.values=row.toString();
                btnEliminar.addEventListener("click",eliminarDatos);
                tdEliminar.appendChild(btnEliminar);
                
                //boton editar
                var btnEditar =document.createElement("button");
                btnEditar.innerHTML = "<i class='material-icons'>create</i>";   
                btnEditar.dataset.values=row.toString();
                btnEditar.addEventListener("click",editarDatos);
                tdEliminar.appendChild(btnEditar);
            }
            tr.appendChild(tdEliminar);
        }
        
        row.forEach((dato)=>{
            //<i class="material-icons">delete</i>

            var td = document.createElement("td");
            td.innerText = dato;
            
            tr.appendChild(td);
        });
        
        tabla.appendChild(tr);
        contador++;
    });
    
    results = document.getElementById("results");
    results.innerHTML="";
    tabla.classList.add("tabla-result");
    results.appendChild(tabla);
    
   }


function filtro(){
    
    from = Object.keys(objeto).toString();
    select = Object.values(objeto).toString();
    
    where = crearWhere(Object.values(objeto));
    
    
    
    
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          mostrarFiltro(JSON.parse(this.responseText))
       }
    };
    
    xhttp.open("POST", "traertabla", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xhttp.send("tabla="+from+"&fields="+select+"&where="+where);
    
}

function crearWhere(arreglo){
    
    var where = [];
    arreglo.forEach((campos)=>{
        
        campos.forEach((campo)=>{
            if(document.getElementById(campo).value!="")
            where.push(campo + document.getElementById(campo).value);
        });
        
    });
    
    return where.toString();
}


function limpiarInputs(arreglo){
    arreglo.forEach((campos)=>{
        
        campos.forEach((campo)=>{
            console.log(campo)
            document.getElementById(campo).value = "";
        });
        
    });
    
}

