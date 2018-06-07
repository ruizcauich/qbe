/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function editarDatos(event){
    var tabla = Object.keys(objeto)[0];
    var valores;
    if( event.target.tagName == "I" ){
        // el padre -> button
        valores = event.target.parentElement.dataset.values
    }else{
        valores = event.target.dataset.values
    }
    
    console.log(tabla + " "+ valores + " "+nombresColumnas);
}
