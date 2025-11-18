<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách danh mục</title> </head>
<body>

    <table border="1" style="width: 100%; border-collapse: collapse;">
        <thead>
            <tr style="background-color: #f2f2f2;">
                <th style="padding: 8px;">STT</th>
                <th style="padding: 8px;">Hình ảnh</th>
                <th style="padding: 8px;">Tên danh mục</th>
                <th style="padding: 8px;">Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cateList}" var="cate" varStatus="STT">
                <tr class="odd gradeX">
                    <td>${STT.index + 1}</td>
                    
                    <c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
                    <td><img height="150" width="200" src="${imgUrl}" alt="${cate.catename}"/></td>
                    
                    <td>${cate.catename}</td>
                    
                    <td>
                        <a href="<c:url value='/admin/category/edit?id=${cate.cateid}'/>" class="center">Sửa</a>
                        | 
                        <a href="<c:url value='/admin/category/delete?id=${cate.cateid}'/>" class="center">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>