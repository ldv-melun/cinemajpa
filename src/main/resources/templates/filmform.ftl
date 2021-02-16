<html xmlns="http://www.w3.org/1999/html">
<body>
<form method="post" name="film" action="/film" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${film.id}">
    <input type="hidden" name="afficheNom" value="${film.afficheNom}">
    Titre : <input type="text" name="titre" value="${film.titre}"><br/>
    Résumé : <textarea name="resume" rows="10" cols="80">${film.resume}</textarea><br/>
    Affiche : <img src="/images/affiches/${film.afficheNom}"><br/>
    Fichier Affiche : <input type="file" name="affiche"><br/>
    Note : <input type="number" name="note" value="${film.note}"><br/>
    Réalisateur : <select name="realisateurId">
        <option value="${film.realisateur.id}">${film.realisateur.prenom} ${film.realisateur.nom}</option>
        <#list persons as person>
        <option value="${person.id}">${person.prenom} ${person.nom}</option>
        </#list>
    </select><br/>
    <input type="submit"value="Valider">
</form>
<a href="/">retour</a>
</body>
</html>
