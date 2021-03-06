package main.controleur;

import main.tournoi.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import main.vue.FenetrePrincipale;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;



/** La classe ExporterTournoiControlleur permet d'exporter un tournoi
 * @author DROUARD Antoine, DERNONCOURT Cyril, LE BERT Lea, MARTINEAU Lucas
 */
public class ExporterTournoiControlleur implements ActionListener {

    private Tournoi tournoi;
    private FenetrePrincipale fenetre;

    /** Constructeur de la classe ExporterTournoiControlleur
     *
     * @param fen la fenêtre principale où trouver les tours joués
     */
    public ExporterTournoiControlleur(FenetrePrincipale fen) {
        this.fenetre = fen; this.tournoi = fen.getTournoi();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Ouverture de la fenetre "enregistrer sous"
        Frame fr = new Frame("Choississez un répertoire");
        FileDialog dial = new FileDialog(fr, "Enregistrer sous", FileDialog.SAVE);
        String titre = fenetre.getTitle();
        titre = titre.replaceAll(" ","_").replaceAll("/","-");
        dial.setFile(titre+".xml"); //Pré-écrit l'extension 'nom du tournoi'.xml dans la fenêtre de dialogue
        dial.setVisible(true);
        fr.setVisible(false);

        Element joueur, prenom, nom, sexe, anciennete, age, niveau, tour, terrain, nomJoueur1, prenomJoueur1, nomJoueur2, prenomJoueur2, nomJoueur3, prenomJoueur3, nomJoueur4, prenomJoueur4, elScore1, elScore2;
        Match match;
        Paire paire1, paire2;
        Joueur joueur1, joueur2, joueur3, joueur4;
        int score1, score2;
        String dateDeNaissance;

        try
        {
            if (dial.getDirectory() != null && dial.getFile() != null)
            {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.newDocument();
                Element racine = document.createElement("tournoi");
                document.appendChild(racine);
                Element joueurs = document.createElement("listeJoueurs");
                racine.appendChild(joueurs);

                for (Joueur j : tournoi.getAllJoueurs())
                {
                    joueur = document.createElement("joueur");
                    joueurs.appendChild(joueur);
                    prenom = document.createElement("prenom");
                    nom = document.createElement("nom");
                    sexe = document.createElement("sexe");
                    anciennete = document.createElement("anciennete");
                    age = document.createElement("dateDeNaissance");
                    niveau = document.createElement("niveau");
                    prenom.appendChild(document.createTextNode(j.getPrenom()));
                    nom.appendChild(document.createTextNode(j.getNom()));

                    if(j.getSexe())
                    {
                        sexe.appendChild(document.createTextNode("Homme"));
                    }
                    else
                    {
                        sexe.appendChild(document.createTextNode("Femme"));
                    }

                    if (j.getNouveau())
                    {
                        anciennete.appendChild(document.createTextNode("Nouveau"));
                    }
                    else
                    {
                        anciennete.appendChild(document.createTextNode("Ancien"));
                    }
/*
                    switch (j.getAge()) {
                        case 1:  age.appendChild(document.createTextNode("-18 ans"));
                        case 2:  age.appendChild(document.createTextNode("18-35 ans"));
                        case 3:  age.appendChild(document.createTextNode("35+ ans"));
                            break;
                        default: age.appendChild(document.createTextNode(""));
                            break;
                    }*/
                    if((dateDeNaissance = j.getNaissance()) == null)
                        age.appendChild(document.createTextNode(""));
                    else
                        age.appendChild(document.createTextNode(dateDeNaissance));
                    switch (j.getNiveau()) {
                        case 1:  niveau.appendChild(document.createTextNode("Débutant"));
                        case 2:  niveau.appendChild(document.createTextNode("Intermédiaire"));
                        case 3:  niveau.appendChild(document.createTextNode("Confirmé"));
                            break;
                        default: niveau.appendChild(document.createTextNode(""));
                            break;
                    }

                    joueur.appendChild(prenom);
                    joueur.appendChild(nom);
                    joueur.appendChild(sexe);
                    joueur.appendChild(anciennete);
                    joueur.appendChild(age);
                    joueur.appendChild(niveau);
                }
                Element matches = document.createElement("listeTours");
                racine.appendChild(matches);
                ArrayList<Tour> lesTours = tournoi.getTours();
                for (Tour t : lesTours)
                {
                    tour = document.createElement("tour");
                    matches.appendChild(tour);
                    ArrayList<Terrain> lesTerrains = t.getMatches();
                    for (Terrain te : lesTerrains)
                    {
                        match = te.getMatch();
                        paire1 = match.getPaire1();
                        joueur1 = paire1.getJoueur1();
                        joueur2 = paire1.getJoueur2();
                        score1 = match.getScore1();
                        paire2 = match.getPaire2();
                        joueur3 = paire2.getJoueur1();
                        joueur4 = paire2.getJoueur2();
                        score2 = match.getScore2();
                        terrain = document.createElement("terrain");
                        tour.appendChild(terrain);
                        nomJoueur1 = document.createElement("nomJoueur1");
                        nomJoueur1.appendChild(document.createTextNode(joueur1.getNom()));
                        terrain.appendChild(nomJoueur1);

                        prenomJoueur1 = document.createElement("prenomJoueur1");
                        prenomJoueur1.appendChild(document.createTextNode(joueur1.getPrenom()));
                        terrain.appendChild(prenomJoueur1);

                        nomJoueur2 = document.createElement("nomJoueur2");
                        nomJoueur2.appendChild(document.createTextNode(joueur2.getNom()));
                        terrain.appendChild(nomJoueur2);

                        prenomJoueur2 = document.createElement("prenomJoueur2");
                        prenomJoueur2.appendChild(document.createTextNode(joueur2.getPrenom()));
                        terrain.appendChild(prenomJoueur2);

                        elScore1 = document.createElement("score1");
                        elScore1.appendChild(document.createTextNode(String.valueOf(score1)));
                        terrain.appendChild(elScore1);

                        nomJoueur3 = document.createElement("nomJoueur3");
                        nomJoueur3.appendChild(document.createTextNode(joueur3.getNom()));
                        terrain.appendChild(nomJoueur3);

                        prenomJoueur3 = document.createElement("prenomJoueur3");
                        prenomJoueur3.appendChild(document.createTextNode(joueur3.getPrenom()));
                        terrain.appendChild(prenomJoueur3);

                        nomJoueur4 = document.createElement("nomJoueur4");
                        nomJoueur4.appendChild(document.createTextNode(joueur4.getNom()));
                        terrain.appendChild(nomJoueur4);

                        prenomJoueur4 = document.createElement("prenomJoueur4");
                        prenomJoueur4.appendChild(document.createTextNode(joueur4.getPrenom()));
                        terrain.appendChild(prenomJoueur4);

                        elScore2 = document.createElement("score2");
                        elScore2.appendChild(document.createTextNode(String.valueOf(score2)));
                        terrain.appendChild(elScore2);

                    }

                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult sortie = new StreamResult(new File(dial.getDirectory().concat(dial.getFile())));

                //prologue
                transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

                //formatage
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                //sortie
                transformer.transform(source, sortie);
            }
        }
        catch (final Exception exp)
        {
            exp.printStackTrace();
        }

    }
}