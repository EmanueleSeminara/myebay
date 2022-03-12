<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Inserisci Nuovo Film</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Inserisci nuovo film</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="${pageContext.request.contextPath}/annuncio/ExecuteEditAnnuncioUtenteServlet" class="row g-3" novalidate="novalidate">
								<input type="hidden" name="idAnnuncio" value="${edit_annuncio_attr.id}">
							
								<div class="col-md-6">
									<label for="testoAnnuncio" class="form-label">Testo annuncio</label>
									<input type="text" name="testoAnnuncio" id="testoAnnuncio" class="form-control" placeholder="Inserire il testo annuncio" value="${edit_annuncio_attr.testoAnnuncio }">
								</div>
								
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo</label>
									<input type="text" name="prezzo" id="prezzo" class="form-control" placeholder="Inserire il prezzo" value="${edit_annuncio_attr.prezzo }">
								</div>
								<div class="col-md-6">
									<label for="categoria" class="form-label">Categorie</label>
									<c:forEach items="${categorie_list}" var="categoriaEntry">
										<div class="form-check">
											<input class="form-check-input" name="categoriaInput" type="checkbox" value="${categoriaEntry.id}" id="categoriaInput-${categoriaEntry.id}" ${categoriaEntry.descrizione?'checked':'' }>
											<label class="form-check-label" for="categoriaInput-${categoriaEntry.id}" >
												${categoriaEntry.codice}
											</label>
										</div>
									</c:forEach>
								</div>
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
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