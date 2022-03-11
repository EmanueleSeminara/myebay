<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Utente</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza utente</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
								<dt class="col-sm-3 text-right">Nome:</dt>
								 <dd class="col-sm-9">${show_utente_attr.nome}</dd>
							</dl>
							<dl class="row">
								<dt class="col-sm-3 text-right">Cognome:</dt>
								<dd class="col-sm-9">${show_utente_attr.cognome}</dd>
							</dl>
							<dl class="row">
								<dt class="col-sm-3 text-right">Username:</dt>
								<dd class="col-sm-9">${show_utente_attr.username}</dd>
							</dl>
							<dl class="row">
								<dt class="col-sm-3 text-right">Stato:</dt>
								<dd class="col-sm-9">${show_utente_attr.stato}</dd>
							</dl>
							<dl class="row">
								<dt class="col-sm-3 text-right">Data creazione:</dt>
								<dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_utente_attr.dateCreated}" /></dd>
							</dl>
					    	
					    	<!-- info Ruoli -->
			                <p>
			                <a class="btn btn-outline-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
			                  Info Ruoli
			                </a>
			              </p>
			              <div class="collapse" id="collapseExample">
			                <div class="card card-body">
			                  <dl class= "row">
			                  <dt class = "col-sm-3 text-right"> Ruoli:</dt>
			                  <c:forEach items="${show_utente_attr.ruoli}" var="ruolo">
			                    <dd class="row-sm-9">${ ruolo.codice }-${ruolo.descrizione }</dd>
			                    <br>
			                  </c:forEach>
			                </dl>
			                  
			                </div>
			              <!-- end info Ruoli -->
			              </div>
					    	
					    <!-- end card body -->
					    </div>
					    
					    <div class='card-footer'>
					        <a href="ExecuteListRegistaServlet" class='btn btn-outline-secondary' style='width:85px'>
					            <i class='fa fa-chevron-left'></i> Indietro
					        </a>
					        <a href="ExecuteDisableUtenteServlet?idUtente=${show_utente_attr.id }" class='btn btn-outline-danger' style='width:100px'>
					            <i class='fa fa-chevron-left'></i> Conferma
					        </a>
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>