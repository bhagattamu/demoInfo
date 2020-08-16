<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="d-flex" id="wrapper">
    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">BG Software</div>
      <c:choose>
      	<c:when test="${sessionScope.roleid == 1 }">
      		<div class="list-group list-group-flush">
		        <a href="<c:url value ="/home"/>" class='list-group-item list-group-item-action bg-light <c:choose><c:when test="${active == 'HOME' }">bg-dark active</c:when><c:otherwise>bg-light </c:otherwise></c:choose>'>Dashboard</a>
		        <a href="<c:url value ="/admin/exam"/>" class='list-group-item list-group-item-action bg-light <c:choose><c:when test="${active == 'EXAM' }">bg-dark active</c:when><c:otherwise>bg-light </c:otherwise></c:choose>'>Exam</a>
		        <a href="<c:url value ="/admin/question"/>" class='list-group-item list-group-item-action bg-light <c:choose><c:when test="${active == 'QUESTION' }">bg-dark active</c:when><c:otherwise>bg-light </c:otherwise></c:choose>'>Question</a>
		        <a href="<c:url value ="/answer"/>" class='list-group-item list-group-item-action bg-light <c:choose><c:when test="${active == 'ANSWER' }">bg-dark active</c:when><c:otherwise>bg-light </c:otherwise></c:choose>'>Answer</a>
		      </div>
      	</c:when>
      	<c:otherwise>
      		<div class="list-group list-group-flush">
		        <a href="<c:url value ="/home"/>" class='list-group-item list-group-item-action bg-light <c:choose><c:when test="${active == 'HOME' }">bg-dark active</c:when><c:otherwise>bg-light </c:otherwise></c:choose>'>Dashboard</a>
		        <a href="<c:url value ="/exam"/>" class='list-group-item list-group-item-action bg-light <c:choose><c:when test="${active == 'EXAM' }">bg-dark active</c:when><c:otherwise>bg-light </c:otherwise></c:choose>'>Exam</a>
		        <a href="<c:url value ="/user/result"/>" class='list-group-item list-group-item-action bg-light <c:choose><c:when test="${active == 'RESULT' }">bg-dark active</c:when><c:otherwise>bg-light </c:otherwise></c:choose>'>Result</a>
		      </div>
      	</c:otherwise>
      </c:choose>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span style="color:red">(${notificationCount })</span>Notifications	
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
              	<c:forEach var="notification" items="${notifications }">
              		<a class="dropdown-item" href="#">${notification.notificationMessage }</a>
              		<div class="dropdown-divider"></div>
              	</c:forEach>
              	<a class="dropdown-item" href="#">See more</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <%
					String userName = (String)session.getAttribute("username");
	                String firstName = (String)session.getAttribute("firstname");
	                String lastName = (String)session.getAttribute("lastname");
                	Long userId = (Long)session.getAttribute("userid");
                	out.print(firstName + " " + lastName );
				%>
                
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Profile</a>
                <c:choose>
      				<c:when test="${sessionScope.roleid == 1 }">
                		<a class="dropdown-item" href="<c:url value='/admin/register'/>">Register Admin</a>
                	</c:when>
                	<c:otherwise>
                	</c:otherwise>
                </c:choose>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="<c:url value ="/logout"/>">LogOut</a>
              </div>
            </li>
          </ul>
        </div>
      </nav>
      <script>
      (function(){
    	  
      })();
      </script>

      
      
  