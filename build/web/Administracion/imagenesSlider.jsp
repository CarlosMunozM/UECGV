<%-- 
    Document   : imagenesSlider
    Created on : 11/02/2021, 13:01:47
    Author     : ASUS
--%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="../../favicon.ico">
  <link rel="canonical" href="https://getbootstrap.com/docs/3.4/examples/starter-template/">

  <title>Carmelina | UE</title>

  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/estilos.css">
  <script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>




</head>

<body>
   <!--<nav class="navbar navbar-default ">
   <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Carmelita</a>
      </div>
      <div id="navbar" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
          <li ><a href="panelimagenes.jsp">Panel de control</a></li>
          <li class="active"><a href="imagenesSlider.jsp">ImagenesSlider</a></li>
        </ul>
      </div>/.nav-collapse 
    </div>
  </nav>-->
  <header id="header">
    <div class="container">
      <div class="row">
        <div class="col-md-10">
          <h3><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Panel de control:<small> Editar Imagenes</small></h3>
        </div>
        <div class="col-md-2">
          <div class="dropdown crear">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
              Añadir Contenido Multimedia
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
              <li><a type="button" data-toggle="modal" data-target="#addImagenSlider">Agregar Imagen de Slider</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </header>
  <section id="breadcrumb">
    <div class="contai">
    </div>
  </section>
  <section id="main">
    <div class="container">
      <div class="row">
        <div class="col-md-3">
          <div class="list-group">
            <a href="#" class="list-group-item active color-principal"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Panel de Control
            </a>
            <a href="srvImagenPresentacion?accion=Listar" class="list-group-item"><span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Imagenes en el Slider <span class="badge">4</span></a>
            </div>
          </div>
          <div class="col-md-9">

            <div class="panel panel-default">
              <div class="panel-heading">
                <h3 class="panel-title"> Multimedia agregada</h3>
              </div>
              <div class="panel-body">
                <div class="row">
                  <div class="col-md-12">
                    <input type="text" class="form-control" placeholder="Filtar Imagenes">
                  </div>
                </div>
                <br>
                <table class="table table-striped table-hover">
                  <tr>
                    <th>Persona que la subio </th>                   
                    <th>Ruta </th>
                    <th>Acción</th>
                  </tr>
                   <c:forEach var="p" items="${imagenes}">
                    <tr>
                        <td>${p.nombre}</td>
                        <td>${p.ruta}</td>
                        <td><a class="btn btn-danger" href="#">Borrar</a></td>
                    </tr>
                </c:forEach>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>



    <!-- Bootstrap core JavaScript
      ================================================== -->
      <!-- Placed at the end of the document so the pages load faster -->
      <!-- Modal agragar imagen -->

      <div class="modal fade" id="addImagenSlider" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <form method="post" action="srvImagenPresentacion?accion=Guardar"enctype="multipart/form-data">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Agregar imagen</h4>
              </div>
              <div class="modal-body">

                <div class="form-group">
                    <label>Imagen</label>
                    <input type="file" id="files" name="fileImagen" class="file"  multiple=true placeholder="Seleccione su archivo" required="" pattern="[a-zA-z0-9áéíóúÁÉÍÓÚñÑ ]{1,50}" maxlength="50" data-placement="top" title="Seleccione su archivo">
                </div>
                  <div class="from">
                    <label>Estado de imagen</label>          
                  </div>
                  <div class="checkbox">
                    <label>
                        <input type="checkbox" name="estado">Publicada
                    </label>
                  </div>
              </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                  <button class="btn btn-primary" name="accion" value="Guardar">Guardar</button>
                </div>
            </form>
          </div>
        </div>
      </div>



      <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
      <script src="js/bootstrap.min.js"></script>
    </body>
</html>
