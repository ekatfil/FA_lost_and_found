<%--
  Created by IntelliJ IDEA.
  User: Эля
  Date: 25.04.2023
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link href="/static/default.css" rel="stylesheet" type="text/css">



    <!-- Скрипт для трех кнопок    -->

    <script>
        document.addEventListener("DOMContentLoaded", function(event) {
            const sortForm = document.getElementById('sortForm');
            const addCargo = document.getElementById('addCargo')
            const clear = document.getElementById('clear')
            const statictic = document.getElementById('statictic')



            clear.addEventListener("click", () =>{
                window.location.href = "/";
            });

            statictic.addEventListener("click", () =>{
                window.location.href = "/statistic";
            });

            addCargo.addEventListener('click', () => {
                window.location.href = "/new";
            });

            sortForm.addEventListener('submit', (event) => {
                event.preventDefault();
                const sortField = document.getElementById('sortSelect').value;
                window.location.href = `/?sortField=${sortField}`;
            });
        });
    </script>


</head>


<body>

<header>
    <div class="top-site">
        <a th:href="@{'/registration'}" type="button" class="btn btn-dark" aria-pressed="false" autocomplete="off" style="margin-left: -50px">Регистрация</a>





        <div>
            <form th:action="@{/search}">
                <input type="text" placeholder="Введите критерий для поиска" name="keyword"  id="keyword" size=80 th:value="${keyword}" required  style="color:black"/>
                <button type="submit" class="btn btn-outline-success">Поиск</button>
            </form>
        </div>



    </div>

</header>

<main>



    <div class="schedule-buttons">
        <form id="sortForm" method="GET">
            <button type="submit" class="btn btn-dark">Сортировать</button>
            <button id="addCargo" type="button" class="btn btn-dark" aria-pressed="false" autocomplete="off">Добавление</button>
            <button id="clear" type="button" class="btn btn-dark" aria-pressed="false" autocomplete="off">Очистка</button>
            <button id="statictic" type="button"class="btn btn-dark" aria-pressed="false" autocomplete="off">Статистика</button>
            <div>
                <label for="sortSelect">Сортировать по:</label>
                <select id="sortSelect" name="sortField" style="color:black">
                    <option value="cargoID" style="color:black">ID груза</option>
                    <option value="cargoName" style="color:black">Организация</option>
                    <option value="cargoContents" style="color:black">Содержимое груза</option>
                    <option value="dispatchCity" style="color:black">Город отправки</option>
                    <option value="cargoArrivalCity" style="color:black">Город прибытия</option>
                    <option value="shipmentDate" style="color:black">Дата отправки</option>
                    <option value="dateOfArrivalOfTheCargo" style="color:black">Дата прибытия</option>
                </select>
            </div>
        </form>
    </div>





    <table id="schedule" class="table table-striped table-hover" >
        <thead>
        <tr>
            <th scope="col">ID груза</th>
            <th scope="col">Организация</th>
            <th scope="col">Содержимое груза</th>
            <th scope="col">Город отправки</th>
            <th scope="col">Город прибытия</th>
            <th scope="col">Дата отправки</th>
            <th scope="col">Дата прибытия</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="cargo: ${cargos}" >
            <th scope="row" class="text-white" th:text="${cargo.cargoID}" >ID груза отсутствует</th>
            <th scope="row" class="text-white" th:text="${cargo.cargoName}">Организация отсутствует</th>
            <th scope="row" class="text-white" th:text="${cargo.cargoContents}">Содержимое груза отсутствует</th>
            <th scope="row" class="text-white" th:text="${cargo.dispatchCity}">Город отправки отсутствует</th>
            <th scope="row" class="text-white" th:text="${cargo.cargoArrivalCity}">Город прибытия отсутствует</th>
            <th scope="row" class="text-white" th:text="${cargo.shipmentDate}">Дата отправки отсутствует</th>
            <th scope="row" class="text-white" th:text="${cargo.dateOfArrivalOfTheCargo}">Дата прибытия отсутствует</th>
            <td>
                <a th:href="@{'/edit/'+${cargo.cargoID}}"><button type="button" class="btn btn-warning">Редактировать</button></a>
                <a th:href="@{'/delete/'+${cargo.cargoID}}"><button type="button" class="btn btn-danger">Удалить</button></a>
            </td>
        </tr>
        </tbody>

    </table>

    <h1>
        <script type="text/javascript">
            function getRowsColumn() {
                let table = document.getElementById('schedule')
                let tBody = table.querySelector('tbody')
                const count = tBody.querySelectorAll('tr').length;
                document.write('Количество строк в таблице: ' + count)
            }
            getRowsColumn()
        </script>
    </h1>


</main>


</body>
</html>
