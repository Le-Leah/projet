<?php

class vueFormulaire {

//@param joueur = Si le formulaire n'est pas bien renseigné
function afficher($erreur) {

?>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Formulaire d'inscription</title>
    <link rel="stylesheet" type="text/css" href="vue/css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  </head>

  <body>
    <div id="loginWrapper">
        <form method="post">
            <input type="submit" name="soumettreLogin" value="login">
        </form>
    </div>
    <div id="formContainer">
     <form id="formulaireJoueur" method="post">
        <ul class="form-style-1">
            <li><label>Nom complet<span class="required" id="texteNom">*</span></label><input type="text" name="prenom" class="field-divided" placeholder="Prénom" />&nbsp;<input type="text" name="nom" class="field-divided" placeholder="Nom" /></li>
            <li>
                <label>Date de naissance<span class="required" id="texteDate">*</span></label>
                <input type="text" name="date" class="field-long" placeholder="Date de naissance (JJ/MM/AAAA)" />
            </li>
            <li>
                <label>Êtes vous un homme ou une femme? <span class="required">*</span></label>
                <label><input id="homme" type="radio" name="sexe" value="Homme" checked="checked">Homme</label>
                <label><input id="femme" type="radio" name="sexe" value="Femme">Femme</label>
            </li>
            <li>
                <label>Êtes-vous un nouvel adhérent? <span class="required">*</span></label>
                <label><input id="nouvAdherent" type="radio" name="anciennete" value="nouvAdherent" checked="checked">Nouvel adhérent</label>
                <label><input id="ancAdherent" type="radio" name="anciennete" value="ancAdherent">Ancien adhérent</label>
            </li>
            <li>
                <label>Quel niveau de jeu pensez-vous avoir?</label>
                <select name="niveau" class="field-select">
                <option value="indefini">Indéfini</option>
                <option value="debutant">Débutant</option>
                <option value="intermediaire">Intermédiaire</option>
                <option value="confirme">Confirmé</option>
                </select>
            </li>
            <li>
                <input type="submit" name="soumettreFormulaire" value="Envoyer" />
            </li>
        </ul>
    </form>
    </div>
    <script src="vue/script/script.js"></script>
  </body>
</html>
<?php
 }
}
?>