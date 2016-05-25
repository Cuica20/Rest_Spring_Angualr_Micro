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
	<div class="hero-unit">
	  <header>
	  	<h1>OnLineStoreTest</h1>
	  </header>
	  <section>
		  <p>
			Mantenimiento de Productos usando Spring MVC, Hibernate, Spring jdbc
		  </p>
			<p><a href="<c:url value="/productList.htm"/>" class="btn btn-primary btn-large">Lista de Productos</a></p>		  
	  </section>
	  <footer>
	  	Desarrollado por Jonathan Roa
	  </footer>
	</div>    
  </body>
</html>