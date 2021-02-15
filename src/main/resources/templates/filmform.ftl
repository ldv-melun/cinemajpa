<html>
<body>
<form method="post" name="film" action="/film">
    <input type="hidden" name="id" value="${film.id}"><br/>
    Titre : <input type="text" name="titre" value="${film.titre}"><br/>
    Résumé : <input type="text" name="resume" value="${film.resume}"><br/>
    <input type="submit"value="Valider">
</form>
<a href="/">retour</a>
</body>
</html>
