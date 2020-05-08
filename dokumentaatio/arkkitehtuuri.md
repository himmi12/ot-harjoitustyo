# Arkkitehtuuri

## Rakenne

Ohjelmassa on kaksi pakkausta: fi.himmi.gui ja fi.himmi.logics. Gui-pakkaus pitää sisällään käyttöliittymän ja logics-pakkaus sovelluslogiikkaan liittyvät luokat.

### Käyttöliittymä

Käyttöliittymä on luokassa fi.himmi.gui.BiotietokantaSovellus. Neljän erilaisen scene-olion vaihtaminen stagessa näyttää käyttäjälle sovelluksen erilaiset näkymät. Peruskäyttäjän näkymästä voi siirtyä joko sisäänkirjautumis- tai tunnuksenluomisnäkymään. Näistä molemmista pääsee erikoiskäyttäjän näkymään. Tunnuksen luomisnäkymästä pääsee myös sisäänkirjautumisnäkymään, jos tunnus on jo olemassa. Erikoiskäyttäjän näkymästä voi uloskirjautumalla palata perusnäkymään, jonne voi palata myös sisäänkirjautumisnäkymästä.

Koska käyttöliittymä on eriytetty sovelluslogiikasta, fi.himmi.logics.Biotietokanta -luokan metodeja kutsutaan käyttöliittymässä. 

### Sovelluslogiikka

Luokassa fi.himmi.logics.Biotietokanta on kaikki sovelluksen toiminnallisuudesta vastaavat metodit, joita käyttöliittymässä kutsutaan. Tässä luokassa hallinnoidaan myös tiedon pysyväistallennusta ja tiedon siirtoa pysyväistallennuksesta käyttömuistiin.

Biotietokanta-luokan metodit:

* luoTunnus(String tunnus, String salasana)
* kirjauduSisaan(String tunnus, String salasana)
* add(String sekvenssi, String nimi)
* search(String sekvenssi)
* fromDbToSeq(String tiedosto);
* fromDbToUser(String tiedosto);
* addToDb(String tiedosto, String dataKayttajatunnus, String lajiSalasana)

Kaikki sovelluksen pysyväismuistissa eli tiedostoissa sekvenssit.txt ja kayttajat.txt siirretään kutsumalla metodeja fromDbToUser ja fromDbToSeq heti sovelluksen käynnistyttyä. Tämän jälkeen sovellusta käytettäessä tiedot haetaan vain käyttömuistista eli listoista mutta lisätään myös tekstitiedostoihin. Näin ollen, kun sovellus käynnistyy uudestaan, oikeat tiedot ovat tallessa.

### Luokkakaavio

![kuva](/dokumentaatio/kuvat/Screenshot%20(23).png)

### Sekvenssikaaviot

#### Käyttäjätunnuksen luominen

![kuva](/dokumentaatio/kuvat/Screenshot%20(24).png)
