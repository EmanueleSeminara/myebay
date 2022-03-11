<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
		<!-- Custom styles per le features di bootstrap 'Columns with icons' -->
	   <link href="./assets/css/features.css" rel="stylesheet">
	   
	   <title>Raccolta Film</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   		
	   		<!-- Fixed navbar -->
	   		<jsp:include page="./navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
			   
			    <div class="p-5 mb-4 bg-light rounded-3">
				      <div class="container-fluid py-5">
				      	 <h1 class="display-5 fw-bold">Cerca annuncio</h1>
					     <form method="post" action="${pageContext.request.contextPath}/ExecuteSearchAnnuncioServlet"
								class="row g-3">
							 
		
		
								<div class="col-md-6">
									<label for="testo" class="form-label">Testo</label> <input
										type="text" name="testo" id="testo" class="form-control"
										placeholder="Inserire testo annuncio">
								</div>
		
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo</label> <input
										type="number" name="prezzo" id="prezzo" class="form-control"
										placeholder="Inserire il prezzo">
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
									<button type="submit" name="submit" value="submit" id="submit"
										class="btn btn-primary">Conferma</button>
									<a class="btn btn-outline-primary ml-2 d-none"
										href="PrepareInsertFilmServlet">Add New</a> <input
										class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
							</form>
					</div>
			    </div>
			    
			  </div>
			  
			 
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="./footer.jsp" />
	  </body>
</html>