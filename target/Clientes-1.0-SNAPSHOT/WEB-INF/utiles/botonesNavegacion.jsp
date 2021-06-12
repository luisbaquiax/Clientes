<%-- 
    Document   : botonesNavegacion
    Created on : 10 jun. 2021, 0:08:54
    Author     : Luis
--%>

<!-- Botones de navegación para agregar cliente-->
<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col col-md-3">
                <a href="#" class="btn btn-primary btn-block"
                   data-toggle="modal" data-target="#agregarClienteModal">
                    <i class="fas fa-plus"></i> Agregar Cliente
                </a>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/salir" class="btn btn-ligth btn-block">
                    <i class="fas fa-arrow-left"></i> Salir
                </a>
            </div>
        </div>
    </div>
</section>
