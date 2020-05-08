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

Biotietokannan suhde ohjelman muihin luokkiin ja tiedon pysyväistallennukseen nähden.

### Tiedon pysyväistallennus

Tiedon pysyväistallennus tapahtuu Biotietokanta-luokan metodin addToDb avulla. Metodia kutsutaan Biotietokanta luokan metodeissa luoTunnus, kun soveltuva käyttäjätunnus luodaan, sekä metodissa add, jossa käyttäjä lisää uuden lajin ja lajin nimeä vastaavan DNA-sekvenssin. Kaikki sovelluksen pysyväismuistissa eli tiedostoissa sekvenssit.txt ja kayttajat.txt siirretään kutsumalla metodeja fromDbToUser ja fromDbToSeq heti sovelluksen käynnistyttyä. Tämän jälkeen sovellusta käytettäessä tiedot haetaan vain käyttömuistista eli listoista mutta lisätään myös tekstitiedostoihin. Näin ollen, kun sovellus käynnistyy uudestaan, oikeat tiedot ovat tallessa.

### Tiedostot

* sekvenssit.txt
* kayttajat.txt
* testausta varten seqFake.txt (vastaa lajitietojen tallennuksesta testeissä)
* testausta varten usersFake.txt (vastaa käyttäjätietojen tallennuksesta testeissä)

Tiedot tallennetaan muodossa:

atcgatcg:Lajin nimi:
* Siis validi sekvenssidata pieninä kirjaimina erotettuna kaksoispisteellä lajin nimestä, minkä jälkeen on uudelleen kaksoispiste
kayttajatunnus:salasana:
* Siis käyttäjätunnus erotettuna kaksoispisteellä salasanasta, minkä jälkeen uudelleen kaksoispiste

### Päätoiminnallisuudet

#### Käyttäjätunnuksen luominen

![kuva](/dokumentaatio/kuvat/Screenshot%20(24).png)

#### Sisäänkirjautuminen

#### Lajitietojen lisääminen

#### Lajin nimen hakeminen

### Muut toiminnallisuudet

## Ohjelman rakenteelliset heikkoudet

Tiedon tallennus.

Muuttujat.
