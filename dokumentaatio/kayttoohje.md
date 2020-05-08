# Käyttöohje

## Konfigurointi

Olettaa, että käynnistyshakemistossa on tiedostot kayttajat.txt, sekvenssit.txt ja fluorisoiva.png.

## Ohjelman käynnistäminen

Komennolla: java -jar todoapp.jar

## Hakutoiminto
![kuva1](/dokumentaatio/kuvat/PublicView.png)

Liitetään textikenttään "Enter a DNA-sequence" -otsikon alle tuntemattoman lajin DNA-sekvenssiä. Painetaan search-nappia hakemisen aloittamiseksi. Hakutoiminto löytyy sekä kirjautumatta että kirjautuneena.

## Laji- ja DNA-sekvenssitiedon lisääminen
![kuva2](/dokumentaatio/kuvat/PrivateView.png)

Liitetään tekstikenttään "Add a DNA-sequence" -otsikon alle lajin tunnettu genomi (tässä sovelluksessa ei käytetä kokonaisia genomeja). Lisätään tekstikenttään "Add the scientific name of the species" -otsikon alle kyseisen lajin tieteellinen nimi. Tämän toiminnon tekeminen on mahdollista vain sisäänkirjautuneena. Samassa näkymässä voi suorittaa myös hakutoimintoa.

## Tunnuksen luominen
![kuva3](/dokumentaatio/kuvat/SignUp.png)

Painetaan nappia "Sign up", jolloin päästään luomaan tunnusta. Lisätään käyttäjätunnus kohtaan Username ja salasana kohtaan Password. Jos tunnuksen luominen onnistuu, päästään "Sign up"-napin painamisen jälkeen sisäänkirjautuneeseen näkymään. Jos sovellus kertoo, että käyttäjätunnus on jo olemassa, voidaan siirtyä sisäänkirjautumisnäkymään napista "Sign In!".

## Sisään kirjautuminen
![kuva4](/dokumentaatio/kuvat/SingIn.png)

Jos käyttäjätunnus on olemassa, sisäänkirjautuminen tapahtuu napista "Sign in". Lisätään käyttäjätunnus kohtaan Username ja oikea salasana kohtaan Password, minkä jälkeen sisään kirjautuminen on mahdollista.
