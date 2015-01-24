<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <html>
<body>

	<c:if test="${not empty gyms}">
	<h3>Gyms Found : </h3>

		<table>
			<tr>
  				<td>Name</td>
  				<td>Contact</td>
  				<td>Address</td>
  				<td>Website</td>
  				<td>HoursOfOperation</td>
 			</tr>
			<c:forEach var="i" items="${gyms}">
   				<tr>
   					<td> <c:out value="${i.name}"></c:out></td>
   					<td> <c:out value="${i.contact}"></c:out></td>
   					<td> <c:out value="${i.address}"></c:out></td>
   					<td> <c:out value="${i.website}"></c:out></td>
   					<td> <c:out value="${i.hoursOfOperation}"></c:out></td>
   				</tr>
   				
			</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${empty gyms}">
	No records found.
	</c:if>
</body>
</html>
