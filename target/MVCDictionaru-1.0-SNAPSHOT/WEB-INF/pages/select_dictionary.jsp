<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select Dictionary</title>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        var requestVar = "";
        function selectPhrase() {
            $("ul#list-phrase li").click(function (e) {
                if(!$("input").is("#btnSel")) {
                    var input = document.createElement("input");
                    input.type = "button";
                    input.id = "btnSel";
                    input.value = "Удалить";
                    input.setAttribute("onclick", "getDelete()");
                    var div = document.getElementById("space");
                    div.appendChild(input);
                }
                e.preventDefault();
                var indexQ = $(this).index();
                $('ul#list-phrase').children().each(function (index) {
                    if (index === indexQ) {
                    requestVar = $(this).text().split(" -------- ");
                    $(this).css('background', '#0f0');
                    console.log(requestVar);
                     }
                });
            });


        }
        function getDict() {
            var myDate;
            $.getJSON('/MVCDictionaru_war_exploded/dictionary/view',
                {'Path': $('#selectDict').val()},
                function (data) {
                    myDate = data;
                    console.log("data");
                })
                .success(function (myDate) {
                    console.log(myDate);
                    renderDict(myDate);
                    selectPhrase();



                })
                .error(function (jqxhr) {
                    if (jqxhr.status == 404) {
                        alert("error occurred ");
                    }
                });

            var tmp1 = $('#selectDict').val();
            console.log(typeof (tmp1));
            var Container = document.getElementById("explan");
            var explanHtml = "";
            if (tmp1 == 'C:\\\\temp\\\\dictionary.txt') {
                explanHtml += "<p>" + 'Введите 2 слова, первое на гипотетическом языке, второе перевод на русский' + '<br/>'
                    + "Слово на гипотетическом языке имеет следующие ограничения:" + '<br/>' + "длинна слов может быть только 4 символа и эти символы только буквы латинской раскладки" + "</p>" + "<br/>";

            } else if (tmp1 == 'C:\\\\temp\\\\dictionary1.txt') {
                explanHtml += "<p>" + 'Введите 2 слова, первое на гипотетическом языке, второе перевод на русский' + '<br/>'
                    + "Слово на гипотетическом языке имеет следующие ограничения:" + '<br/>' + "длина слов может быть только 5 символа и эти символы только цифры" + "</p>" + "<br/>";
            }
            Container.innerHTML = explanHtml;
        };
        function addDict() {

            if ($('#incomeWord').val() != "" && $('#clearWord').val() != "") {
                if ($('#selectDict').val() == 'C:\\\\temp\\\\dictionary.txt') {
                    var myReg = new RegExp("^[a-zA-Z0-9]+$");
                    if (myReg.exec($('#incomeWord').val()) && $('#incomeWord').val().length === 4) {
                        addRequest();
                    } else {
                        alert("Ключ не соответствует правилу");
                    }
                } else if ($('#selectDict').val() == 'C:\\\\temp\\\\dictionary1.txt') {
                    var myReg = new RegExp("[1-9]+");
                    if (myReg.exec($('#incomeWord').val()) && $('#incomeWord').val().length === 5) {
                        addRequest();
                    } else {
                        alert("Ключ не соответствует правилу");
                    }
                }
            } else {
                alert("Пустые значения");
            }


            function addRequest() {
                $.getJSON('/MVCDictionaru_war_exploded/dictionary/add',
                    {
                        'incomeWord': $('#incomeWord').val(),
                        'clearWord': $('#clearWord').val()
                    },
                    function (data) {
                        myDate = data;
                        console.log("data");
                    })
                    .success(function (myDate) {
                        console.log(myDate);
                        if(myDate === null){
                            alert("Данная фраза уже существует в словаре");
                            return;
                        }
                        renderDict(myDate);
                        selectPhrase();

                    })
                    .error(function (jqxhr) {
                        if (jqxhr.status == 404) {
                            alert("error occurred ");
                        }
                    });
            }
        }
        function getSearch() {
            $.getJSON('/MVCDictionaru_war_exploded/dictionary/search',
                {
                    'Word': $('#firstWord').val(),
                    'subjectOfSearch': $('#selectWord').val()
                },
                function (data) {
                    myDate = data;
                    console.log("data");
                })
                .success(function (myDate) {
                    console.log(myDate);
                    var strHtml = "";
                    if(myDate === null){
                        strHtml = "<li>" + "В словаре нет данной пары" + "</li>";
                        $('#list-phrase').html(strHtml);
                        return;
                    }
                    strHtml += "<li>" + myDate.incomeWord + " -------- " + myDate.clearWord + "</li>";
                    $('#list-phrase').html(strHtml);
                    selectPhrase();

                })
                .error(function (jqxhr) {
                    if (jqxhr.status == 404) {
                        alert("error occurred ");
                    }
                });

        }
        function getDelete() {
            $.getJSON('/MVCDictionaru_war_exploded/dictionary/delete',
                {
                    'incomeWord': requestVar[0],
                    'clearWord': requestVar[1]
                },
                function (data) {
                    myDate = data;
                    console.log("data");
                })
                .success(function (myDate) {
                    console.log(myDate);
                    renderDict(myDate);
                    selectPhrase();

                })
                .error(function (jqxhr) {
                    if (jqxhr.status == 404) {
                        alert("error occurred ");
                    }
                });

        }
        function renderDict(data) {
            var strHtml = "";
            for (i = 0; i < data.dictionary.length; i++) {
                strHtml += "<li>" + data.dictionary[i].incomeWord + " -------- " + data.dictionary[i].clearWord + "</li>";
            }
            $('#list-phrase').html(strHtml);
        }

    </script>

    <style>
        .selected {
            background: #0f0;
        }

        li {
            cursor: pointer;
        }
    </style>

</head>
<body>
<h1>Выберите словарь</h1>
<select id="selectDict">
    <option value="C:\\temp\\dictionary.txt">Dictionary_1</option>
    <option value="C:\\temp\\dictionary1.txt">Dictionary_2</option>
</select>
<input type="button" onclick="getDict()" value="input">
<div id = "space">
    <details>
        <summary>Поиск</summary>
        <div id="search">
        <input id="firstWord" type="text" placeholder="Поиск">
            <select id="selectWord">
                <option value="1">Поиск по ключу</option>
                <option value="2">Поиск по значени</option>
            </select>
        <input type="button" onclick="getSearch()" value="Поиск">
        </div>
    </details>
    <ul id="list-phrase"></ul>
</div>
<details>
    <summary>Добавление фразы</summary>
    <div id="explan"></div>
    <input id="incomeWord" type="text" placeholder="incomeWord">
    <input id="clearWord" type="text" placeholder="clearWord">
    <input type="button" onclick="addDict()" value="Add">
</details>
</body>
</html>