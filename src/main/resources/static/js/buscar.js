	
	function load(){
		var id = $('#id').val()
		$('#vehiculo').load('/vehiculo/buscarVehi', "nombre="+ id);
		
}