<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <title>Aplicacion desarrollada con Spring 3</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <script src="js/libs/modernizr-2.5.3.js"></script>
  </head>
  <body>
  	<div class="row">
  	<div class="span4">&nbsp; </div>
  	<div class="span4">
		<form:form commandName="product" method="POST" class="well form-search">
			<label>Lista de Productos</label>
			<legend>Filtros de Busqueda:</legend>
			<label>Nombre: </label>
			<form:input path="name" maxlength="30" class="input-medium search-query"/>
			
			<input type="submit" id="Buscar" name="Buscar"  value="Buscar" class="btn"/>
			<br/> <br/>
		    <input type="button" id="Agregar" name="Agregar" 
		    	onclick="location.href='<c:url value="/productView.htm"/>'"
		        value="Agregar Producto" class="btn btn-primary"/>	
		</form:form>
			
		<table class="table table-condensed">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Precio</th>													
				</tr>								
			</thead>
			<tbody>
				<c:forEach var="product" items="${products}">
					<tr>
						<td><a href="productView.htm?idProduct=${product.idProduct}"><c:out value="${product.name}"/></a></td>
						<td>$ <c:out value="${product.price}"/></td>																
					</tr>								
				</c:forEach>																																																					
			</tbody>
		</table>
		</div>
		<div class="span4">&nbsp; </div>			
	</div>
  </body>
</html>