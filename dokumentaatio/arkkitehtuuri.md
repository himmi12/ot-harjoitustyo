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

### Toiminnallisuudet

#### Käyttäjätunnuksen luominen

![kuva](/dokumentaatio/kuvat/Screenshot%20(24).png)

#### Sisäänkirjautuminen

![kuva2](/dokumentaatio/kuvat/kirjaudu.png)

#### Lajitietojen lisääminen

![kuva3](/dokumentaatio/kuvat/add.png)

#### Lajin nimen hakeminen

![kuva4](/dokumentaatio/kuvat/search.png)

## Ohjelman rakenteelliset heikkoudet

Ohjelmassa on joitain rakenteellisia heikkouksia, joista on mainittu muun muassa testausdokumentissa. Heikkouksista huomionarvoisin on tiedon tallennus, sillä vaikka ohjelman nimi on Bio**tietokanta**, se ei tallenna tietoa tietokantaan vaan tekstitiedostoihin. Tämän vuoksi yhtään suurempien datamäärien pyöritys ohjelmalla toimisi huonosti. Koska tiedot tallentuvat tekstitiedostoihin, on ohjelma vaarassa kaatua, jos minkäänlainen väärä syöte pääsisi tallentumaan tiedostoon. Muun muassa tekstitiedoston tyhjentäminen manuaalisesti voi johtaa sinne, että tyhjältä näyttävässä tiedostossa on rivinvaihtomerkki, joka johtaa ohjelman kaatumiseen. Koska ohjelmassa ei ole mahdollisuutta poistaa tai muokata tekstitiedostoihin tallennettua tietoa, muokkaukset tuleekin tehdä manuaalisesti. Tämä on suuri puute.

Ohjelman muuttujat on nimetty erittäin epämääräisesti. Koska tajusin vasta liian myöhään, että ohjelmointi olisi pitänyt suorittaa englanniksi, vain osa muuttujista on englanniksi. Jälkeenpäin ajateltuna olisi ollut parempi, jos kaikki olisivat olleet suomeksi. Lisäksi muuttujien nimeäminen kielestä riippumatta olisi kaivannut johdonmukaisuutta.
