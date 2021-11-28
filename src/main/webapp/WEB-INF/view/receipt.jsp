<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
      <link rel="stylesheet" type="text/css" href="/static/receipt.css?<?php echo time(); ?>" />

  </head>
  <body class="body">
  <div>
      <p class="header">
          <jsp:text>Cash Receipt</jsp:text>
      </p>
      <jsp:useBean id="date" class="java.util.Date"/>
      <p class="text">
          <jsp:text>Today's date: </jsp:text>
          <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
          <br>
          <jsp:text>Time: </jsp:text>
          <fmt:formatDate value="${date}" pattern="HH:mm:ss" />
      </p>

      <c:set var="finalsum" >
          <fmt:formatNumber type="number"  maxFractionDigits="3" value="${0}"/>
      </c:set>

      <c:set var="discountsum" >
          <fmt:formatNumber type="number" maxFractionDigits="3" value="${0}"/>
      </c:set>
      <c:set var="totaldiscount" >
          <fmt:formatNumber type="number"  maxFractionDigits="3" value="${0}"/>
      </c:set>

      <table border="1" class="tabb1">
          <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Price</th>
              <th>Count</th>
              <th>TotalPrice</th>
              <th>Discount</th>
          </tr>
          <c:forEach var="prod" items="${products}">
              <c:if test="${prod.value >= 5}">
                  <c:set var = "discountsum" value = "${discountsum+prod.key.price*prod.value*0.1}"/>
                  <c:set var = "finalsum" value = "${finalsum+prod.key.price*prod.value}"/>
                  <tr>
                      <td>${prod.key.id}</td>
                      <td>${prod.key.name}</td>
                      <td>${prod.key.price}</td>
                      <td>${prod.value}</td>
                      <td>${prod.value*prod.key.price-discountsum}</td>
                      <td>${discountsum}</td>
                  </tr>
              </c:if>

              <c:set var="totaldiscount" value="${totaldiscount+discountsum}"/>

              <c:if test="${discountsum > 0}">
                  <c:set var="discountsum" value="${0}"/>
              </c:if>

              <c:if test="${prod.value < 5}">
                  <c:set var = "finalsum" value = "${finalsum+prod.key.price*prod.value}"/>
                  <tr>
                      <td>${prod.key.id}</td>
                      <td>${prod.key.name}</td>
                      <td>${prod.key.price}</td>
                      <td>${prod.value}</td>
                      <td>${prod.value*prod.key.price}</td>
                      <td>${0}</td>
                  </tr>
              </c:if>
          </c:forEach>
      </table
          <br>
          <br>
       <p class="text1">
           <c:out value = "Final sum: ${finalsum-totaldiscount}"/>
           <br>
           <c:out value = "Discount sum: ${totaldiscount}"/>
       </p>

  </div>
  </body>
</html>
