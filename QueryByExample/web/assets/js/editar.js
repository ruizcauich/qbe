/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var editar_button = document.getElementById("editar");
editar_button.addEventListener("click", editar);

var tabla;
var valores;
var valores_originales;

function editarDatos(event){
    tabla = Object.keys(objeto)[0];
    
    if( event.target.tagName == "I" ){
        // el padre -> button
        valores = event.target.parentElement.dataset.values
    }else{
        valores = event.target.dataset.values
    }
    
    valores_originales = valores;
    
    console.log(event.target.parentElement.dataset.values);
    asignarInputs(Object.values(objeto),event.target.parentElement.dataset.values.split(","))
    
}

function asignarInputs(arreglo,valores){
    var contador = 0;
    arreglo.forEach((campos)=>{
        
        campos.forEach((campo)=>{
            document.getElementById(campo).value = valores[contador];
            contador++;
        });
        
    });
    
}


function recolectarValores(arreglo){
    
    var where = [];
    arreglo.forEach((campos)=>{
        
        campos.forEach((campo)=>{
            if(document.getElementById(campo).value!="")
            where.push(document.getElementById(campo).value);
        });
        
    });
    
    return where.toString();
}


function editar(){

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          resultadoEditar(this.responseText)
       }
    };
    
    valores = recolectarValores(Object.values(objeto));
    xhttp.open("POST", "editar", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xhttp.send("tabla="+tabla+"&valores="+valores+"&valoresoriginales="+valores_originales+"&columnas="+nombresColumnas);
   
}

function resultadoEditar(mensaje){
    
    if(mensaje>0){
        alert("Registro actualizado exitosamente");
        limpiarInputs(Object.values(objeto));
        filtro();
    }
    else{
        alert("Error al Actualizar");
    }
}
