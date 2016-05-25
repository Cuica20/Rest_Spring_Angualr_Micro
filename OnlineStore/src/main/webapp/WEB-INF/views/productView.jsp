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
  	<div class="span8">
		<form:form commandName="product" class="form-horizontal">
			<fieldset>
				<legend>Producto</legend>
				<div class="control-group">
					<label class="control-label" for="input01">Nombre:</label>
					<div class="controls">
						<form:input path="name" maxlength="50" class="input-xlarge" id="input01"/>
						<form:errors path="name"/>						  
					</div>			
				</div>
				<div class="control-group">
					<label class="control-label" for="select01">Tipo:</label>
					<div class="controls">
						<form:select path="category.idCategory" id="select01">
							<form:options items="${categoriesTypes}" itemLabel="name" itemValue="idCategory"/>										
						</form:select>			
					</div>			
				</div>
				<div class="control-group">
					<label class="control-label" for="input03">Codigo:</label>
					<div class="controls">
						<form:input path="code" maxlength="20" lass="input-xlarge" id="input03"/>
						<form:errors path="code"/>		
					</div>			
				</div>
				<div class="control-group">
					<label class="control-label" for="input04">Descripcion:</label>
					<div class="controls">
						<form:textarea path="description" cols="30" rows="5" lass="input-xlarge" id="input04"></form:textarea>
					</div>			
				</div>		
				<div class="control-group">
					<label class="control-label" for="input05">Precio:</label>
					<div class="controls">
						<form:input path="price" maxlength="8" lass="input-xlarge" id="input03"/><form:errors path="price"/>
					</div>			
				</div>				
				<div class="form-actions">          
					<input type="submit" id="Guardar" name="Guardar" value="Guardar" class="btn btn-primary"/>
				
					<c:if test="${not empty product.idProduct }">
							<input type="button" id="Eliminar" name="Eliminar" 
									onclick="location.href='<c:url value="delete.htm?idProduct=${product.idProduct} "/>'"
									value="Eliminar" class="btn btn-danger"/>									
					</c:if>		
					<input type="button" id="Cancelar" name="Cancelar" 
							onclick="location.href='<c:url value="/productList.htm"/>'"
							value="Cancelar" class="btn"/>				
				</div>
			</fieldset>						
		</form:form>			
		</div>
	</div>
  </body>
</html>	