$(document).ready(function() {
	var vehiculo = $('#vehiculo').val()
	console.log(vehiculo)
	$('#servicio').autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/cobro/cargar-producto/" + request.term,
				dataType : "json",
				data : {
					term : request.term,
				},

				success : function(data) {
					response($.map(data, function(item) {
						return {
							value : item.id,
							label : item.nombre,
							precio : item.precio,
						}
					}))
				}

			})
		},
		select : function(event, ui) {
			var linea = $("#plantillaItemsFactura").html();
			linea = linea.replace(/{ID}/g, ui.item.value)
			linea = linea.replace(/{NOMBRE}/g, ui.item.label)
			linea = linea.replace(/{PRECIO}/g, ui.item.precio)
			precio = ui.item.precio
			$('#cargarItemProductos tbody').append(linea)
			itemsHelper.calcularImporte(ui.item.value, ui.item.precio)
			itemsHelper.limpiarCampo();
			return false;
		}
	})
	$("form").submit(function() {
		$("#plantillaItemsFactura").remove()
		return;
	})
})

var itemsHelper = {
	eliminarLineaFactura : function(id) {
		$('#ROW_' + id).remove()
		this.calcularTotal()
	},
	calcularImporte: function(id,precio){
		$("#precio" + id).html(parseInt(precio))
		this.calcularTotal()
	},
	calcularTotal : function() {
		total=0
		$('span[id^="precio_"]').each(function(){
			if(!isNaN(parseInt($(this).html()))){	
				total += parseInt($(this).html())
			}	
		})
		$("#granTotal").html(total)
	},
	limpiarCampo: function(){
		$('#servicio').val('')
	}
	
}