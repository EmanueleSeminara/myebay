<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />

<title>Ricerca Acquisti</title>
</head>
<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div
				class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }"
				role="alert">
				${errorMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>

			<div class="alert alert-warning alert-dismissible fade show d-none"
				role="alert">
				Attenzione!!! Funzionalitą ancora non implementata. Sulla 'Conferma'
				per il momento parte il 'listAll'
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>

			<div class='card'>
				<div class='card-header'>
					<h5>Ricerca acquisti</h5>
				</div>
				<div class='card-body'>

					<form method="post" action="${pageContext.request.contextPath}/acquisto/ExecuteSearchAcquistoServlet"
						class="row g-3">
						<input type="hidden" name="idUtente" value="${userInfo.id}">


						<div class="col-md-6">
							<label for="descrizione" class="form-label">Descrizione</label> <input
								type="text" name="descrizione" id="descrizione" class="form-control"
								placeholder="Inserire la descrizione">
						</div>

						<div class="col-md-6">
							<label for="prezzo" class="form-label">Prezzo</label> <input
								type="text" name="prezzo" id="prezzo" class="form-control"
								placeholder="Inserire il prezzo">
						</div>

						<div class="col-md-6">
							<label for="data" class="form-label">Data
								</label> <input class="form-control" id="data"
								type="date" placeholder="dd/MM/yy" title="formato : gg/mm/aaaa"
								name="data">
						</div>


						<div class="col-12">
							<button type="submit" name="submit" value="submit" id="submit"
								class="btn btn-primary">Conferma</button>
							 <input class="btn btn-outline-warning" type="reset" value="Ripulisci">
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