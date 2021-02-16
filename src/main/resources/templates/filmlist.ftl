<html>
<body>
<h1>Liste des films</h1>
<ul>
    <#list films as film>
      <li>${film.titre} <a href="/film/${film.id}">Edit</a></li>
    </#list>
</ul>
</body>
</html>
